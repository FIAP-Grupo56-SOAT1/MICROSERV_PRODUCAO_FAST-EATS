package br.com.fiap.fasteats.dataprovider.client.service;

import br.com.fiap.fasteats.core.domain.exception.StatusPedidoNotFound;
import br.com.fiap.fasteats.dataprovider.client.response.StatusPedidoResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class StatusPedidoIntegrationImplUnitTest {
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private Logger logger;
    @InjectMocks
    private StatusPedidoIntegrationImpl statusPedidoIntegration;
    @Value("${URL_PEDIDO_SERVICE}")
    private String URL_BASE;
    private final String PATH = "/status-pedidos";
    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void consultarPorNome_DeveRetornarStatusPedidoResponse_QuandoComunicacaoBemSucedida() {
        // Arrange
        String nomeStatus = "Recebido";
        StatusPedidoResponse expectedResponse = new StatusPedidoResponse();
        String url = String.format("%s%s/consultar-por-nome/{nome}", URL_BASE, PATH);

        when(restTemplate.getForObject(url, StatusPedidoResponse.class, nomeStatus)).thenReturn(expectedResponse);

        // Act
        Optional<StatusPedidoResponse> result = statusPedidoIntegration.consultarPorNome(nomeStatus);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedResponse, result.get());
    }

    @Test
    void consultarPorNome_DeveRetornarVazio_QuandoStatusNaoEncontrado() {
        // Arrange
        String nomeStatus = "StatusInexistente";
        String url = String.format("%s%s/consultar-por-nome/{nome}", URL_BASE, PATH);

        when(restTemplate.getForObject(url, StatusPedidoResponse.class, nomeStatus)).thenReturn(null);

        // Act
        Optional<StatusPedidoResponse> result = statusPedidoIntegration.consultarPorNome(nomeStatus);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void consultarPorNome_DeveLancarStatusPedidoNotFound_QuandoErroNaComunicacao() {
        // Arrange
        String nomeStatus = "Recebido";
        String url = String.format("%s%s/consultar-por-nome/{nome}", URL_BASE, PATH);
        String mensagemErro = "Erro na comunicação com o microserviço Status Pedido";
        String resposta = String.format("Erro retorno microservice status pedido %s", mensagemErro);
        when(restTemplate.getForObject(url, StatusPedidoResponse.class, nomeStatus)).thenThrow(new RuntimeException(mensagemErro));
        when(logger.isErrorEnabled()).thenReturn(true);

        // Act & Assert
        StatusPedidoNotFound exception = assertThrows(StatusPedidoNotFound.class, () -> statusPedidoIntegration.consultarPorNome(nomeStatus));
        assertEquals(resposta, exception.getMessage());
    }
}
