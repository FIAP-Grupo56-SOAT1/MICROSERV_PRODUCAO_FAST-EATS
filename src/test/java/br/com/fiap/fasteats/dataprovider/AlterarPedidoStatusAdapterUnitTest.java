package br.com.fiap.fasteats.dataprovider;

import br.com.fiap.fasteats.core.domain.exception.StatusPedidoNotFound;
import br.com.fiap.fasteats.core.domain.model.Pedido;
import br.com.fiap.fasteats.core.domain.model.StatusPedido;
import br.com.fiap.fasteats.dataprovider.client.PedidoIntegration;
import br.com.fiap.fasteats.dataprovider.client.StatusPedidoIntegration;
import br.com.fiap.fasteats.dataprovider.client.mapper.PedidoMapper;
import br.com.fiap.fasteats.dataprovider.client.mapper.StatusPedidoMapper;
import br.com.fiap.fasteats.dataprovider.client.response.PedidoResponse;
import br.com.fiap.fasteats.dataprovider.client.response.StatusPedidoResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static br.com.fiap.fasteats.core.constants.StatusPedidoConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AlterarPedidoStatusAdapterUnitTest {
    @Mock
    private PedidoIntegration pedidoIntegration;
    @Mock
    private StatusPedidoIntegration statusPedidoIntegration;
    @Mock
    private StatusPedidoMapper statusPedidoMapper;
    @Mock
    private PedidoMapper pedidoMapper;
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
    void recebido_DeveAtualizarStatusParaRecebido_QuandoChamado() {
        // Arrange
        Long pedidoId = 1L;
        PedidoResponse pedidoResponse = new PedidoResponse();
        Pedido pedido = new Pedido();
        pedido.setId(pedidoId);
        pedidoResponse.setId(pedidoId);
        Long statusPedidoId = 1L;
        StatusPedidoResponse statusPedidoResponse = new StatusPedidoResponse(statusPedidoId, STATUS_PEDIDO_RECEBIDO, true);
        StatusPedido statusPedido = new StatusPedido(statusPedidoId, STATUS_PEDIDO_RECEBIDO, true);


        when(statusPedidoIntegration.consultarPorNome(STATUS_PEDIDO_RECEBIDO)).thenReturn(Optional.of(statusPedidoResponse));
        when(statusPedidoMapper.toStatusPedido(statusPedidoResponse)).thenReturn(statusPedido);
        when(pedidoIntegration.consultar(pedidoId)).thenReturn(Optional.of(pedidoResponse));
        when(pedidoMapper.toPedido(pedidoResponse)).thenReturn(pedido);

        // Act
        Optional<Pedido> result = alterarPedidoStatusAdapter.recebido(pedidoId);

        // Assert
        assertTrue(result.isPresent());
        verify(pedidoIntegration, times(1)).atualizarStatus(pedidoId, statusPedidoId);
    }

    @Test
    void recebido_DeveLancarStatusPedidoNotFound_QuandoStatusNaoEncontrado() {
        // Arrange
        Long pedidoId = 1L;
        when(statusPedidoIntegration.consultarPorNome(STATUS_PEDIDO_RECEBIDO)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(StatusPedidoNotFound.class, () -> alterarPedidoStatusAdapter.recebido(pedidoId));
    }

    @Test
    void emPreparo_DeveAtualizarStatusParaEmPreparo_QuandoChamado() {
        // Arrange
        Long pedidoId = 1L;
        PedidoResponse pedidoResponse = new PedidoResponse();
        Pedido pedido = new Pedido();
        pedido.setId(pedidoId);
        pedidoResponse.setId(pedidoId);
        Long statusPedidoId = 1L;
        StatusPedidoResponse statusPedidoResponse = new StatusPedidoResponse(statusPedidoId, STATUS_PEDIDO_EM_PREPARO, true);
        StatusPedido statusPedido = toStatusPedido(statusPedidoResponse);


        when(statusPedidoIntegration.consultarPorNome(STATUS_PEDIDO_EM_PREPARO)).thenReturn(Optional.of(statusPedidoResponse));
        when(statusPedidoMapper.toStatusPedido(statusPedidoResponse)).thenReturn(statusPedido);
        when(pedidoIntegration.consultar(pedidoId)).thenReturn(Optional.of(pedidoResponse));
        when(pedidoMapper.toPedido(pedidoResponse)).thenReturn(pedido);

        // Act
        Optional<Pedido> result = alterarPedidoStatusAdapter.emPreparo(pedidoId);

        // Assert
        assertTrue(result.isPresent());
        verify(pedidoIntegration, times(1)).atualizarStatus(pedidoId, statusPedidoId);
    }

    @Test
    void emPreparo_DeveLancarStatusPedidoNotFound_QuandoStatusNaoEncontrado() {
        // Arrange
        Long pedidoId = 1L;
        when(statusPedidoIntegration.consultarPorNome(STATUS_PEDIDO_EM_PREPARO)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(StatusPedidoNotFound.class, () -> alterarPedidoStatusAdapter.emPreparo(pedidoId));
    }

    @Test
    void pronto_DeveAtualizarStatusParaPronto_QuandoChamado() {
        // Arrange
        Long pedidoId = 1L;
        PedidoResponse pedidoResponse = new PedidoResponse();
        Pedido pedido = new Pedido();
        pedido.setId(pedidoId);
        pedido.setStatusPedido(STATUS_PEDIDO_PRONTO);
        pedidoResponse.setId(pedidoId);
        Long statusPedidoId = 1L;
        StatusPedido statusPedido = new StatusPedido(statusPedidoId, STATUS_PEDIDO_PRONTO, true);
        StatusPedidoResponse statusPedidoResponse = toStatusPedidoResponse(statusPedido);


        when(statusPedidoIntegration.consultarPorNome(STATUS_PEDIDO_PRONTO)).thenReturn(Optional.of(statusPedidoResponse));
        when(statusPedidoMapper.toStatusPedido(statusPedidoResponse)).thenReturn(statusPedido);
        when(pedidoIntegration.consultar(pedidoId)).thenReturn(Optional.of(pedidoResponse));
        when(pedidoMapper.toPedido(pedidoResponse)).thenReturn(pedido);

        // Act
        Optional<Pedido> result = alterarPedidoStatusAdapter.pronto(pedidoId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(pedidoId, result.get().getId());
        assertEquals(STATUS_PEDIDO_PRONTO, result.get().getStatusPedido());
        assertEquals(statusPedido, toStatusPedido(statusPedidoResponse));
        assertEquals(statusPedido.hashCode(), toStatusPedido(statusPedidoResponse).hashCode());
        verify(pedidoIntegration, times(1)).atualizarStatus(pedidoId, statusPedidoId);
    }

    @Test
    void pronto_DeveLancarStatusPedidoNotFound_QuandoStatusNaoEncontrado() {
        // Arrange
        Long pedidoId = 1L;
        when(statusPedidoIntegration.consultarPorNome(STATUS_PEDIDO_PRONTO)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(StatusPedidoNotFound.class, () -> alterarPedidoStatusAdapter.pronto(pedidoId));
    }

    @Test
    void finalizado_DeveAtualizarStatusParaFinalizado_QuandoChamado() {
        // Arrange
        Long pedidoId = 1L;
        PedidoResponse pedidoResponse = new PedidoResponse();
        Pedido pedido = new Pedido();
        pedido.setId(pedidoId);
        pedidoResponse.setId(pedidoId);
        Long statusPedidoId = 1L;
        StatusPedidoResponse statusPedidoResponse = new StatusPedidoResponse();
        statusPedidoResponse.setId(statusPedidoId);
        statusPedidoResponse.setNome(STATUS_PEDIDO_FINALIZADO);
        statusPedidoResponse.setAtivo(true);

        StatusPedido statusPedido = new StatusPedido();
        statusPedido.setId(statusPedidoId);
        statusPedido.setNome(STATUS_PEDIDO_FINALIZADO);
        statusPedido.setAtivo(true);

        when(statusPedidoIntegration.consultarPorNome(STATUS_PEDIDO_FINALIZADO)).thenReturn(Optional.of(statusPedidoResponse));
        when(statusPedidoMapper.toStatusPedido(statusPedidoResponse)).thenReturn(statusPedido);
        when(pedidoIntegration.consultar(pedidoId)).thenReturn(Optional.of(pedidoResponse));
        when(pedidoMapper.toPedido(pedidoResponse)).thenReturn(pedido);

        // Act
        Optional<Pedido> result = alterarPedidoStatusAdapter.finalizado(pedidoId);

        // Assert
        assertTrue(result.isPresent());
        verify(pedidoIntegration, times(1)).atualizarStatus(pedidoId, statusPedidoId);
    }

    @Test
    void finalizado_DeveLancarStatusPedidoNotFound_QuandoStatusNaoEncontrado() {
        // Arrange
        Long pedidoId = 1L;
        when(statusPedidoIntegration.consultarPorNome(STATUS_PEDIDO_FINALIZADO)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(StatusPedidoNotFound.class, () -> alterarPedidoStatusAdapter.finalizado(pedidoId));
    }

    private StatusPedido toStatusPedido(StatusPedidoResponse statusPedidoResponse) {
        StatusPedido statusPedido = new StatusPedido();
        statusPedido.setId(statusPedidoResponse.getId());
        statusPedido.setNome(statusPedidoResponse.getNome());
        statusPedido.setAtivo(statusPedidoResponse.getAtivo());
        return statusPedido;
    }

    private StatusPedidoResponse toStatusPedidoResponse(StatusPedido statusPedido) {
        StatusPedidoResponse statusPedidoResponse = new StatusPedidoResponse();
        statusPedidoResponse.setId(statusPedido.getId());
        statusPedidoResponse.setNome(statusPedido.getNome());
        statusPedidoResponse.setAtivo(statusPedido.getAtivo());
        return statusPedidoResponse;
    }
}
