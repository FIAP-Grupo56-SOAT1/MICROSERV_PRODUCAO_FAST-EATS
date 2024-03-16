package br.com.fiap.fasteats.dataprovider;

import br.com.fiap.fasteats.dataprovider.client.PedidoIntegration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AlterarPedidoStatusAdapterUnitTest {
    @Mock
    private PedidoIntegration pedidoIntegration;

    @InjectMocks
    private AlterarPedidoStatusAdapter alterarPedidoStatusAdapter;

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
    @DisplayName("Pedido Recebido")
    void deveChamarPedidoRecebido_QuandoRecebido() {
        // Arrange
        Long pedidoId = 1L;

        // Act
        alterarPedidoStatusAdapter.recebido(pedidoId);

        // Assert
        verify(pedidoIntegration, times(1)).pedidoRecebido(pedidoId);
    }

    @Test
    @DisplayName("Pedido em Preparo")
    void deveChamarPedidoEmPreparo_QuandoEmPreparo() {
        // Arrange
        Long pedidoId = 1L;

        // Act
        alterarPedidoStatusAdapter.emPreparo(pedidoId);

        // Assert
        verify(pedidoIntegration, times(1)).pedidoEmPreparo(pedidoId);
    }

    @Test
    @DisplayName("Pedido Pronto")
    void deveChamarPedidoPronto_QuandoPronto() {
        // Arrange
        Long pedidoId = 1L;

        // Act
        alterarPedidoStatusAdapter.pronto(pedidoId);

        // Assert
        verify(pedidoIntegration, times(1)).pedidoPronto(pedidoId);
    }

    @Test
    @DisplayName("Pedido Finalizado")
    void deveChamarPedidoFinalizado_QuandoFinalizado() {
        // Arrange
        Long pedidoId = 1L;

        // Act
        alterarPedidoStatusAdapter.finalizado(pedidoId);

        // Assert
        verify(pedidoIntegration, times(1)).pedidoFinalizado(pedidoId);
    }
}
