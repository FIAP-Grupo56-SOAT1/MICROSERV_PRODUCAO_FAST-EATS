package br.com.fiap.fasteats.dataprovider;

import br.com.fiap.fasteats.core.domain.exception.PedidoNotFound;
import br.com.fiap.fasteats.core.domain.model.Pedido;
import br.com.fiap.fasteats.dataprovider.client.PedidoIntegration;
import br.com.fiap.fasteats.dataprovider.client.mapper.PedidoMapper;
import br.com.fiap.fasteats.dataprovider.client.response.PedidoResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidoAdapterUnitTest {
    @Mock
    private PedidoIntegration pedidoIntegration;
    @Mock
    private PedidoMapper pedidoMapper;
    @InjectMocks
    private PedidoAdapter pedidoAdapter;
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
    void consultar_DeveRetornarPedido_QuandoPedidoExiste() {
        // Arrange
        Long pedidoId = 1L;
        PedidoResponse pedidoResponse = new PedidoResponse();
        Pedido pedido = new Pedido();
        pedido.setId(pedidoId);
        pedido.setDataHoraCriado(LocalDateTime.now());
        when(pedidoIntegration.consultar(pedidoId)).thenReturn(Optional.of(pedidoResponse));
        when(pedidoMapper.toPedido(pedidoResponse)).thenReturn(pedido);

        // Act
        Pedido result = pedidoAdapter.consultar(pedidoId);

        // Assert
        assertNotNull(result);
        assertEquals(pedido, result);
        assertEquals(pedido.getDataHoraCriado(), result.getDataHoraCriado());
        verify(pedidoIntegration, times(1)).consultar(pedidoId);
        verify(pedidoMapper, times(1)).toPedido(pedidoResponse);
    }

    @Test
    void consultar_DeveLancarPedidoNotFound_QuandoPedidoNaoExiste() {
        // Arrange
        Long pedidoId = 1L;
        when(pedidoIntegration.consultar(pedidoId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PedidoNotFound.class, () -> pedidoAdapter.consultar(pedidoId));
        verify(pedidoIntegration, times(1)).consultar(pedidoId);
        verifyNoInteractions(pedidoMapper);
    }
}
