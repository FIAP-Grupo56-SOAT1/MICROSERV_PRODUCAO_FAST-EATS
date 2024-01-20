package br.com.fiap.fasteats.core.usecase.impl.unit;

import br.com.fiap.fasteats.core.domain.exception.RegraNegocioException;
import br.com.fiap.fasteats.core.domain.model.Pedido;
import br.com.fiap.fasteats.core.usecase.impl.CozinhaPedidoUseCase;
import br.com.fiap.fasteats.core.usecase.pedido.AlterarPedidoStatusInputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("CozinhaPedidoUseCaseUnitTest")
class CozinhaPedidoUseCaseUnitTest {
    private CozinhaPedidoUseCase cozinhaPedidoUseCase;
    @Mock
    private AlterarPedidoStatusInputPort alterarPedidoStatusInputPort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cozinhaPedidoUseCase = new CozinhaPedidoUseCase(alterarPedidoStatusInputPort);
    }

    @Test
    @DisplayName("Deve receber um pedido na cozinha")
    void testeReceberPedido() {
        Long idPedido = 1L;
        Long idStatusPedido = 1L;
        Long idStatusPedidoRecebido = 2L;

        Pedido pedidoRecebido = new Pedido();
        pedidoRecebido.setId(idPedido);
        pedidoRecebido.setStatusPedido(idStatusPedido);
        pedidoRecebido.setDataHoraRecebimento(LocalDateTime.now());
        pedidoRecebido.setStatusPedido(idStatusPedidoRecebido);

        when(alterarPedidoStatusInputPort.recebido(idPedido)).thenReturn(pedidoRecebido);

        Pedido resultado = cozinhaPedidoUseCase.receberPedido(idPedido);

        assertNotNull(resultado);
        assertEquals(resultado.getStatusPedido(), idStatusPedidoRecebido);
        assertNotNull(resultado.getDataHoraRecebimento());
        verify(alterarPedidoStatusInputPort, times(1)).recebido(idPedido);
    }

    @Test
    @DisplayName("Deve iniciar o preparo de um pedido na cozinha")
    void testeIniciarPreparoPedido() {
        Long idPedido = 1L;
        Long idStatusPedidoEmPreparo = 2L;

        Pedido pedidoEmPreparo = new Pedido();
        pedidoEmPreparo.setId(idPedido);
        pedidoEmPreparo.setStatusPedido(idStatusPedidoEmPreparo);

        when(alterarPedidoStatusInputPort.emPreparo(idPedido)).thenReturn(pedidoEmPreparo);

        Pedido resultado = cozinhaPedidoUseCase.iniciarPreparoPedido(idPedido);

        assertNotNull(resultado);
        assertEquals(resultado.getStatusPedido(), idStatusPedidoEmPreparo);
        verify(alterarPedidoStatusInputPort, times(1)).emPreparo(idPedido);
    }

    @Test
    @DisplayName("Deve finalizar o preparo de um pedido na cozinha")
    void testeFinalizarPreparoPedido() {
        Long idPedido = 1L;
        Long idStatusPedidoPronto = 2L;

        Pedido pedidoPronto = new Pedido();
        pedidoPronto.setId(idPedido);
        pedidoPronto.setStatusPedido(idStatusPedidoPronto);

        when(alterarPedidoStatusInputPort.pronto(idPedido)).thenReturn(pedidoPronto);

        Pedido resultado = cozinhaPedidoUseCase.finalizarPreparoPedido(idPedido);

        assertNotNull(resultado);
        assertEquals(resultado.getStatusPedido(), idStatusPedidoPronto);
        verify(alterarPedidoStatusInputPort, times(1)).pronto(idPedido);
    }

    @Test
    @DisplayName("Deve retirar um pedido da cozinha")
    void testeRetirarPedido() {
        Long idPedido = 1L;
        Long idStatusPedidoFinalizado = 2L;

        Pedido pedidoFinalizado = new Pedido();
        pedidoFinalizado.setId(idPedido);
        pedidoFinalizado.setStatusPedido(idStatusPedidoFinalizado);
        pedidoFinalizado.setDataHoraFinalizado(LocalDateTime.now());

        when(alterarPedidoStatusInputPort.finalizado(idPedido)).thenReturn(pedidoFinalizado);

        Pedido resultado = cozinhaPedidoUseCase.retirarPedido(idPedido);

        assertNotNull(resultado);
        assertEquals(resultado.getStatusPedido(), idStatusPedidoFinalizado);
        assertNotNull(resultado.getDataHoraFinalizado());
        verify(alterarPedidoStatusInputPort, times(1)).finalizado(idPedido);
    }

    @Test
    @DisplayName("Deve lançar RegraNegocioException ao receber um pedido não pago")
    void testeReceberPedidoNaoPago() {
        Long idPedido = 1L;
        Long idStatusPedidoCancelado = 1L;

        Pedido pedido = new Pedido();
        pedido.setId(idPedido);
        pedido.setStatusPedido(idStatusPedidoCancelado);

        when(alterarPedidoStatusInputPort.recebido(idPedido)).thenThrow(new RegraNegocioException("O Pedido só pode ser recebido se estiver com o status PAGO"));

        assertThrows(RegraNegocioException.class, () -> alterarPedidoStatusInputPort.recebido(idPedido));
    }

    @Test
    @DisplayName("Deve lançar RegraNegocioException ao iniciar o preparo de um pedido não recebido")
    void testeIniciarPreparoPedidoNaoRecebido() {
        Long idPedido = 1L;
        Long idStatusPedidoCancelado = 1L;

        Pedido pedido = new Pedido();
        pedido.setId(idPedido);
        pedido.setStatusPedido(idStatusPedidoCancelado);

        when(alterarPedidoStatusInputPort.emPreparo(idPedido)).thenThrow(new RegraNegocioException("O Pedido só pode ser iniciado se estiver com o status RECEBIDO"));

        assertThrows(RegraNegocioException.class, () -> alterarPedidoStatusInputPort.emPreparo(idPedido));
    }

    @Test
    @DisplayName("Deve lançar RegraNegocioException ao finalizar o preparo de um pedido não em preparo")
    void testeFinalizarPreparoPedidoNaoEmPreparo() {
        Long idPedido = 1L;
        Long idStatusPedidoCancelado = 1L;

        Pedido pedido = new Pedido();
        pedido.setId(idPedido);
        pedido.setStatusPedido(idStatusPedidoCancelado);

        when(alterarPedidoStatusInputPort.pronto(idPedido)).thenThrow(new RegraNegocioException("O Pedido só pode ser finalizado se estiver com o status EM_PREPARO"));

        assertThrows(RegraNegocioException.class, () -> alterarPedidoStatusInputPort.pronto(idPedido));
    }

    @Test
    @DisplayName("Deve lançar RegraNegocioException ao retirar um pedido não pronto")
    void testeRetirarPedidoNaoPronto() {
        Long idPedido = 1L;
        Long idStatusPedidoCancelado = 1L;

        Pedido pedido = new Pedido();
        pedido.setId(idPedido);
        pedido.setStatusPedido(idStatusPedidoCancelado);

        when(alterarPedidoStatusInputPort.finalizado(idPedido)).thenThrow(new RegraNegocioException("O Pedido só pode ser retirado se estiver com o status PRONTO"));

        assertThrows(RegraNegocioException.class, () -> alterarPedidoStatusInputPort.finalizado(idPedido));
    }
}
