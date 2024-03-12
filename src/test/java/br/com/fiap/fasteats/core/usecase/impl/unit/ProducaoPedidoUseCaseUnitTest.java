package br.com.fiap.fasteats.core.usecase.impl.unit;

import br.com.fiap.fasteats.core.dataprovider.CozinhaPedidoOutputPort;
import br.com.fiap.fasteats.core.dataprovider.IniciarProcessoOutputPort;
import br.com.fiap.fasteats.core.domain.exception.CozinhaPedidoNotFound;
import br.com.fiap.fasteats.core.domain.model.CozinhaPedido;
import br.com.fiap.fasteats.core.usecase.impl.ProducaoPedidoUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Optional;

import static br.com.fiap.fasteats.core.constants.ProcessoCozinhaConstants.*;
import static br.com.fiap.fasteats.core.constants.StatusPedidoConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class ProducaoPedidoUseCaseUnitTest {
    @Mock
    private IniciarProcessoOutputPort iniciarProcessoOutputPort;
    @Mock
    private CozinhaPedidoOutputPort cozinhaPedidoOutputPort;
    @InjectMocks
    private ProducaoPedidoUseCase producaoPedidoUseCase;
    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    @DisplayName("Deve iniciar o processo de cozinha pedido como pendente")
    void pendente() {
        Long pedidoId = 1L;
        CozinhaPedido cozinhaPedido = new CozinhaPedido();
        CozinhaPedido cozinhaPedidoPendente = new CozinhaPedido();
        cozinhaPedidoPendente.setIdPedido(pedidoId);
        cozinhaPedidoPendente.setStatusPedido(STATUS_PEDIDO_PAGO);
        cozinhaPedidoPendente.setProcessoAtual(STATUS_COZINHA_PENDENTE);
        when(cozinhaPedidoOutputPort.consultarPorIdPedido(pedidoId)).thenReturn(Optional.of(cozinhaPedido));
        when(iniciarProcessoOutputPort.pendente(any(CozinhaPedido.class))).thenReturn(cozinhaPedidoPendente);

        CozinhaPedido result = producaoPedidoUseCase.pendente(pedidoId);

        assertEquals(STATUS_PEDIDO_PAGO, result.getStatusPedido());
        assertEquals(STATUS_COZINHA_PENDENTE, result.getProcessoAtual());
        assertNull(result.getDataRecebimentoDoPedido());
        verify(cozinhaPedidoOutputPort).consultarPorIdPedido(pedidoId);
        verify(iniciarProcessoOutputPort).pendente(cozinhaPedido);
    }

   @Test
   @DisplayName("Deve iniciar o processo de cozinha pedido recebido")
    void receber() {
         Long pedidoId = 1L;
         CozinhaPedido cozinhaPedido = new CozinhaPedido();
         CozinhaPedido cozinhaPedidoRecebido = new CozinhaPedido();
         cozinhaPedidoRecebido.setIdPedido(pedidoId);
         cozinhaPedidoRecebido.setStatusPedido(STATUS_PEDIDO_RECEBIDO);
         cozinhaPedidoRecebido.setProcessoAtual(STATUS_COZINHA_RECEBIDO);
         cozinhaPedidoRecebido.setDataRecebimentoDoPedido(LocalDateTime.now());
         when(cozinhaPedidoOutputPort.consultarPorIdPedido(pedidoId)).thenReturn(Optional.of(cozinhaPedido));
         when(iniciarProcessoOutputPort.receber(any(CozinhaPedido.class))).thenReturn(cozinhaPedidoRecebido);

         CozinhaPedido result = producaoPedidoUseCase.receber(pedidoId);

         assertEquals(STATUS_PEDIDO_RECEBIDO, result.getStatusPedido());
         assertEquals(STATUS_COZINHA_RECEBIDO, result.getProcessoAtual());
         assertNotNull(result.getDataRecebimentoDoPedido());
         verify(cozinhaPedidoOutputPort).consultarPorIdPedido(pedidoId);
         verify(iniciarProcessoOutputPort).receber(cozinhaPedido);
    }

    @Test
    @DisplayName("Deve iniciar o processo de cozinha pedido em preparo")
    void iniciarPreparo() {
        Long pedidoId = 1L;
        CozinhaPedido cozinhaPedido = new CozinhaPedido();
        CozinhaPedido cozinhaPedidoPreparo = new CozinhaPedido();
        cozinhaPedidoPreparo.setStatusPedido(STATUS_PEDIDO_EM_PREPARO);
        cozinhaPedidoPreparo.setProcessoAtual(STATUS_COZINHA_INICIO_PREPARO);
        cozinhaPedidoPreparo.setDataInicioPreparo(LocalDateTime.now());
        when(cozinhaPedidoOutputPort.consultarPorIdPedido(pedidoId)).thenReturn(Optional.of(cozinhaPedido));
        when(iniciarProcessoOutputPort.iniciarPreparo(cozinhaPedido)).thenReturn(cozinhaPedidoPreparo);

        CozinhaPedido result = producaoPedidoUseCase.iniciarPreparo(pedidoId);

        assertEquals(STATUS_PEDIDO_EM_PREPARO, result.getStatusPedido());
        assertEquals(STATUS_COZINHA_INICIO_PREPARO, result.getProcessoAtual());
        assertNotNull(result.getDataInicioPreparo());
        verify(cozinhaPedidoOutputPort).consultarPorIdPedido(pedidoId);
        verify(iniciarProcessoOutputPort).iniciarPreparo(cozinhaPedido);
    }

    @Test
    @DisplayName("Deve iniciar o processo de cozinha pedido finalizar preparo")
    void finalizarPreparo() {
        Long pedidoId = 1L;
        CozinhaPedido cozinhaPedido = new CozinhaPedido();
        CozinhaPedido cozinhaPedidoFinalizado = new CozinhaPedido();
        cozinhaPedidoFinalizado.setStatusPedido(STATUS_PEDIDO_PRONTO);
        cozinhaPedidoFinalizado.setProcessoAtual(STATUS_COZINHA_FINALIZANDO_PREPARO);
        cozinhaPedidoFinalizado.setDataFinalizacaoPreparo(LocalDateTime.now());
        when(cozinhaPedidoOutputPort.consultarPorIdPedido(pedidoId)).thenReturn(Optional.of(cozinhaPedido));
        when(iniciarProcessoOutputPort.finalizarPreparo(cozinhaPedido)).thenReturn(cozinhaPedidoFinalizado);

        CozinhaPedido result = producaoPedidoUseCase.finalizarPreparo(pedidoId);

        assertEquals(STATUS_PEDIDO_PRONTO, result.getStatusPedido());
        assertEquals(STATUS_COZINHA_FINALIZANDO_PREPARO, result.getProcessoAtual());
        assertNotNull(result.getDataFinalizacaoPreparo());
        verify(cozinhaPedidoOutputPort).consultarPorIdPedido(pedidoId);
        verify(iniciarProcessoOutputPort).finalizarPreparo(cozinhaPedido);
    }

    @Test
    @DisplayName("Deve iniciar o processo de cozinha pedido retirar")
    void retirar() {
        Long pedidoId = 1L;
        CozinhaPedido cozinhaPedido = new CozinhaPedido();
        CozinhaPedido cozinhaPedidoRetirado = new CozinhaPedido();
        cozinhaPedidoRetirado.setStatusPedido(STATUS_PEDIDO_FINALIZADO);
        cozinhaPedidoRetirado.setProcessoAtual(STATUS_COZINHA_ENTREGAR_PEDIDO);
        cozinhaPedidoRetirado.setDataEntregaPedido(LocalDateTime.now());
        when(cozinhaPedidoOutputPort.consultarPorIdPedido(pedidoId)).thenReturn(Optional.of(cozinhaPedido));
        when(iniciarProcessoOutputPort.entregarPedido(cozinhaPedido)).thenReturn(cozinhaPedidoRetirado);

        CozinhaPedido result = producaoPedidoUseCase.retirar(pedidoId);

        assertEquals(STATUS_PEDIDO_FINALIZADO, result.getStatusPedido());
        assertEquals(STATUS_COZINHA_ENTREGAR_PEDIDO, result.getProcessoAtual());
        assertNotNull(result.getDataEntregaPedido());
        verify(cozinhaPedidoOutputPort).consultarPorIdPedido(pedidoId);
        verify(iniciarProcessoOutputPort).entregarPedido(cozinhaPedido);
    }

    @Test
    @DisplayName("Deve apresentar erro ao consultar cozinha pedido por id e não encontrar")
    void consultarPorIdPedido() {
        Long pedidoId = 1L;
        when(cozinhaPedidoOutputPort.consultarPorIdPedido(pedidoId)).thenReturn(Optional.empty());

        assertThrows(CozinhaPedidoNotFound.class, () -> producaoPedidoUseCase.retirar(pedidoId));
        verify(cozinhaPedidoOutputPort).consultarPorIdPedido(pedidoId);
    }
}
