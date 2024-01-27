package br.com.fiap.fasteats.core.usecase.impl.unit;

import br.com.fiap.fasteats.core.dataprovider.CozinhaPedidoOutputPort;
import br.com.fiap.fasteats.core.dataprovider.PedidoOutputPort;
import br.com.fiap.fasteats.core.domain.model.CozinhaPedido;
import br.com.fiap.fasteats.core.domain.model.Pedido;
import br.com.fiap.fasteats.core.usecase.AlterarPedidoStatusInputPort;
import br.com.fiap.fasteats.core.usecase.impl.CozinhaPedidoUseCase;
import br.com.fiap.fasteats.core.validator.AlterarPedidoStatusValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static br.com.fiap.fasteats.core.constants.ProcessoCozinhaConstants.*;
import static br.com.fiap.fasteats.core.constants.StatusPedidoConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CozinhaPedidoInputPortTest {


    @Mock
    private  CozinhaPedidoOutputPort cozinhaPedidoOutputPort;

    @Mock
    private PedidoOutputPort pedidoOutputPort;

    @Mock
    private  AlterarPedidoStatusInputPort alterarPedidoStatusInputPort;

    @InjectMocks
    private CozinhaPedidoUseCase  cozinhaPedidoUseCase;

    @Mock
    private AlterarPedidoStatusValidator alterarPedidoStatusValidator;

    private AutoCloseable openMocks;

    private Long pedidoId = 1l;
    private String cozinhaId = "65b1762af79a235f75630fa6";
    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }


    @Test
    void listar() {

        // Arrange
        List<CozinhaPedido> listCozinhaPedido;
        listCozinhaPedido = List.of(
                new CozinhaPedido(cozinhaId,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        pedidoId,
                        STATUS_PEDIDO_EM_PREPARO,RECEBIDO),

                new CozinhaPedido("65b1762af79a235f75630fa7",
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        2l,
                        STATUS_PEDIDO_EM_PREPARO,INICIO_PREPARO),

                new CozinhaPedido( "65b1762af79a235f75630fa8",
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        3l,
                        STATUS_PEDIDO_EM_PREPARO,FINALIZANDO_PREPARO),

                new CozinhaPedido( "65b1762af79a235f75630fa9",
                        LocalDateTime.now(),
                        LocalDateTime.now()
                        ,LocalDateTime.now(),
                        LocalDateTime.now(),
                        4l,
                        STATUS_PEDIDO_EM_PREPARO,ENTREGAR_PEDIDO)
        );

        when(cozinhaPedidoOutputPort.listar()).thenReturn(listCozinhaPedido);

        // Act
        List<CozinhaPedido> result = cozinhaPedidoUseCase.listar();

        // Assert
        assertEquals(listCozinhaPedido, result);
    }

    @Test
    void consultar() {

        // Arrange
        CozinhaPedido cozinhaPedido = new CozinhaPedido(
                cozinhaId,
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                pedidoId,STATUS_PEDIDO_EM_PREPARO,RECEBIDO);

        when(cozinhaPedidoOutputPort.consultar(cozinhaId)).thenReturn(Optional.of(cozinhaPedido));

        // Act
        CozinhaPedido result = cozinhaPedidoUseCase.consultar(cozinhaId);

        // Assert
        assertEquals(cozinhaPedido, result);
        verify(cozinhaPedidoOutputPort).consultar(cozinhaId);
    }

    @Test
    void consultarPorIdPedido() {

        // Arrange
        CozinhaPedido cozinhaPedido = new CozinhaPedido(
                cozinhaId,
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                pedidoId,
                STATUS_PEDIDO_EM_PREPARO,
                RECEBIDO);

        when(cozinhaPedidoOutputPort.consultarPorIdPedido(pedidoId)).thenReturn(Optional.of(cozinhaPedido));

        // Act
        CozinhaPedido result = cozinhaPedidoUseCase.consultarPorIdPedido(pedidoId);

        // Assert
        assertEquals(cozinhaPedido, result);
        verify(cozinhaPedidoOutputPort).consultarPorIdPedido(pedidoId);
    }

    @Test
    void receber() {
        var dateTimeNow = LocalDateTime.now();

        Pedido pedido = new Pedido();
        pedido.setId(pedidoId);
        pedido.setStatusPedido(STATUS_PEDIDO_PAGO);
        pedido.setDataHoraRecebimento(dateTimeNow);

        CozinhaPedido cozinhaPedido = new CozinhaPedido();
        cozinhaPedido.setIdPedido(pedidoId);
        cozinhaPedido.setStatusPedido(pedido.getStatusPedido());
        cozinhaPedido.setProcessoAtual(RECEBIDO);
        cozinhaPedido.setDataRecebimentoDoPedido(dateTimeNow);

        when(alterarPedidoStatusInputPort.recebido(pedidoId)).thenReturn(pedido);
        when(cozinhaPedidoOutputPort.salvar(cozinhaPedido)).thenReturn(cozinhaPedido);
        when(pedidoOutputPort.consultar(pedidoId)).thenReturn(pedido);

        // Act
        CozinhaPedido result = cozinhaPedidoUseCase.receber(pedidoId);

        // Assert
        assertEquals(cozinhaPedido, result);
        verify(cozinhaPedidoOutputPort).salvar(result);
        verify(alterarPedidoStatusValidator).validarRecebido(pedidoId);
    }

    @Test
    void iniciarPreparo() {
    }

    @Test
    void finalizarPreparo() {
    }

    @Test
    void retirar() {
    }
}