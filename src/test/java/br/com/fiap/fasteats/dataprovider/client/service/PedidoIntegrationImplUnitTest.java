package br.com.fiap.fasteats.dataprovider.client.service;

import br.com.fiap.fasteats.dataprovider.client.exception.AwsSQSException;
import br.com.fiap.fasteats.dataprovider.client.exception.MicroservicoPedidoException;
import br.com.fiap.fasteats.dataprovider.client.request.AtualizarStatusPedidoRequest;
import br.com.fiap.fasteats.dataprovider.client.response.PedidoResponse;
import com.google.gson.Gson;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidoIntegrationImplUnitTest {
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private SqsTemplate sqsTemplate;
    @InjectMocks
    private PedidoIntegrationImpl pedidoIntegration;
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
    @DisplayName("Consultar Pedido")
    void deveRetornarPedido_QuandoConsultar() {
        // Arrange
        Long pedidoId = 1L;
        PedidoResponse pedidoResponse = new PedidoResponse();
        when(restTemplate.getForObject(anyString(), eq(PedidoResponse.class), eq(pedidoId))).thenReturn(pedidoResponse);

        // Act
        Optional<PedidoResponse> result = pedidoIntegration.consultar(pedidoId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(pedidoResponse, result.get());
    }

    @Test
    @DisplayName("Erro ao Consultar Pedido")
    void deveLancarExcecao_QuandoConsultarComErro() {
        // Arrange
        Long pedidoId = 1L;
        when(restTemplate.getForObject(anyString(), eq(PedidoResponse.class), eq(pedidoId))).thenThrow(RuntimeException.class);

        // Act & Assert
        assertThrows(MicroservicoPedidoException.class, () -> pedidoIntegration.consultar(pedidoId));
    }

    @Test
    @DisplayName("Pedido Recebido")
    void deveChamarPedidoRecebido_QuandoRecebido() {
        // Arrange
        Long pedidoId = 1L;
        String jsonAtualizarStatusPedido = getJsonAtualizarStatusPedido(pedidoId);

        // Act
        pedidoIntegration.pedidoRecebido(pedidoId);

        // Assert
        verify(sqsTemplate, times(1)).send(any(), eq(jsonAtualizarStatusPedido));
    }

    @Test
    @DisplayName("Pedido Em Preparo")
    void deveChamarPedidoEmPreparo_QuandoEmPreparo() {
        // Arrange
        Long pedidoId = 1L;
        String jsonAtualizarStatusPedido = getJsonAtualizarStatusPedido(pedidoId);

        // Act
        pedidoIntegration.pedidoEmPreparo(pedidoId);

        // Assert
        verify(sqsTemplate, times(1)).send(any(), eq(jsonAtualizarStatusPedido));
    }

    @Test
    @DisplayName("Pedido Pronto")
    void deveChamarPedidoPronto_QuandoPronto() {
        // Arrange
        Long pedidoId = 1L;
        String jsonAtualizarStatusPedido = getJsonAtualizarStatusPedido(pedidoId);

        // Act
        pedidoIntegration.pedidoPronto(pedidoId);

        // Assert
        verify(sqsTemplate, times(1)).send(any(), eq(jsonAtualizarStatusPedido));
    }

    @Test
    @DisplayName("Pedido Finalizado")
    void deveChamarPedidoFinalizado_QuandoFinalizado() {
        // Arrange
        Long pedidoId = 1L;
        String jsonAtualizarStatusPedido = getJsonAtualizarStatusPedido(pedidoId);

        // Act
        pedidoIntegration.pedidoFinalizado(pedidoId);

        // Assert
        verify(sqsTemplate, times(1)).send(any(), eq(jsonAtualizarStatusPedido));
    }

    @Test
    @DisplayName("Erro Enviar Para Fila Pedido Status")
    void deveLancarExcecao_QuandoEnviarParaFilaPedidoStatusComErro() {
        // Arrange
        Long pedidoId = 1L;
        doThrow(RuntimeException.class).when(sqsTemplate).send(any(), anyString());

        // Act & Assert
        assertThrows(AwsSQSException.class, () -> pedidoIntegration.pedidoRecebido(pedidoId));
    }

    private String getJsonAtualizarStatusPedido(Long pedidoId) {
        AtualizarStatusPedidoRequest request = new AtualizarStatusPedidoRequest(pedidoId);
        return new Gson().toJson(request);
    }
}
