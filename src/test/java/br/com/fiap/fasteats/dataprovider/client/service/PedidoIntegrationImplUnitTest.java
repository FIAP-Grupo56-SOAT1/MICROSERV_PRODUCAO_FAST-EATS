package br.com.fiap.fasteats.dataprovider.client.service;

import br.com.fiap.fasteats.dataprovider.client.exception.MicroservicoPedidoException;
import br.com.fiap.fasteats.dataprovider.client.response.PedidoResponse;
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
import static org.mockito.Mockito.*;

class PedidoIntegrationImplUnitTest {
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private Logger logger;
    @InjectMocks
    private PedidoIntegrationImpl pedidoIntegration;
    @Value("${URL_PEDIDO_SERVICE}")
    private String URL_BASE;
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
    void consultar_DeveRetornarPedidoResponse_QuandoComunicacaoBemSucedida() {
        // Arrange
        Long id = 1L;
        PedidoResponse expectedResponse = new PedidoResponse();
        String url = String.format("%s/pedidos/%d", URL_BASE, id);

        when(restTemplate.getForObject(url, PedidoResponse.class, id)).thenReturn(expectedResponse);

        // Act
        Optional<PedidoResponse> result = pedidoIntegration.consultar(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedResponse, result.get());
    }

    @Test
    void consultar_DeveRetornarVazio_QuandoPedidoNaoEncontrado() {
        // Arrange
        Long id = 1L;
        String url = String.format("%s/pedidos/%d", URL_BASE, id);

        when(restTemplate.getForObject(url, PedidoResponse.class, id)).thenReturn(null);

        // Act
        Optional<PedidoResponse> result = pedidoIntegration.consultar(id);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void consultar_DeveLancarMicroservicoPedidoException_QuandoErroNaComunicacao() {
        // Arrange
        Long id = 1L;
        String url = String.format("%s/pedidos/%d", URL_BASE, id);
        String mensagemErro = "Serviço indisponível";
        String resposta = String.format("Erro na comunicação com o microserviço Pedido: %s", mensagemErro);
        when(restTemplate.getForObject(url, PedidoResponse.class, id)).thenThrow(new RuntimeException(mensagemErro));
        when(logger.isErrorEnabled()).thenReturn(true);

        // Act & Assert
        MicroservicoPedidoException exception = assertThrows(MicroservicoPedidoException.class, () -> pedidoIntegration.consultar(id));
        assertEquals(resposta, exception.getMessage());
    }

    /*@Test
    void atualizarStatus_DeveAtualizarStatus_QuandoComunicacaoBemSucedida() {
        // Arrange
        Long id = 1L;
        Long idStatus = 2L;
        String url = String.format("%s/pedidos/%d/status/%d", URL_BASE, id, idStatus);

        doNothing().when(restTemplate).put(url, null, id, idStatus);

        // Act
        assertDoesNotThrow(() -> pedidoIntegration.atualizarStatus(id, idStatus));

        // Assert
        verify(restTemplate, times(1)).put(url, null, id, idStatus);
    }

    @Test
    void atualizarStatus_DeveLancarMicroservicoPedidoException_QuandoErroNaComunicacao() {
        // Arrange
        Long id = 1L;
        Long idStatus = 2L;
        String url = String.format("%s/pedidos/%d/status/%d", URL_BASE, id, idStatus);
        String mensagemErro = "Serviço indisponível";
        String resposta = String.format("Erro na comunicação com o microserviço Pedido: %s", mensagemErro);
        doThrow(new RuntimeException(mensagemErro)).when(restTemplate).put(url, null, id, idStatus);
        when(logger.isErrorEnabled()).thenReturn(true);

        // Act & Assert
        MicroservicoPedidoException exception = assertThrows(MicroservicoPedidoException.class, () -> pedidoIntegration.atualizarStatus(id, idStatus));
        assertEquals(resposta, exception.getMessage());
    }*/
}
