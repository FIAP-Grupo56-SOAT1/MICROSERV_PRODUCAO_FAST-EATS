package br.com.fiap.fasteats.core.usecase.impl.unit;

import br.com.fiap.fasteats.core.dataprovider.AlterarPedidoStatusOutputPort;
import br.com.fiap.fasteats.core.dataprovider.CozinhaPedidoOutputPort;
import br.com.fiap.fasteats.core.dataprovider.PedidoOutputPort;
import br.com.fiap.fasteats.core.domain.exception.CozinhaPedidoNotFound;
import br.com.fiap.fasteats.core.domain.exception.PedidoNotFound;
import br.com.fiap.fasteats.core.domain.model.CozinhaPedido;
import br.com.fiap.fasteats.core.domain.model.Pedido;
import br.com.fiap.fasteats.core.usecase.AlterarPedidoStatusInputPort;
import br.com.fiap.fasteats.core.usecase.impl.AlterarPedidoStatusUseCase;
import br.com.fiap.fasteats.core.usecase.impl.CozinhaPedidoUseCase;
import br.com.fiap.fasteats.core.validator.AlterarPedidoStatusValidator;
import br.com.fiap.fasteats.dataprovider.client.PedidoIntegration;
import br.com.fiap.fasteats.dataprovider.client.StatusPedidoIntegration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    private AlterarPedidoStatusOutputPort alterarPedidoStatusOutputPort;

    @InjectMocks
    private AlterarPedidoStatusUseCase alterarPedidoStatusUseCase;


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
    void consultarErro() {

        assertThrows(CozinhaPedidoNotFound.class, () -> cozinhaPedidoUseCase.consultar(cozinhaId));
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
    void consultarPorIdPedidoErro() {

        assertThrows(CozinhaPedidoNotFound.class, () -> cozinhaPedidoUseCase.consultarPorIdPedido(pedidoId));
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
    }

    @Test
    void receberErro() {

        //Arrange
        when(alterarPedidoStatusOutputPort.recebido(pedidoId)).thenReturn(Optional.empty());

        //Act & Assert
        assertThrows(PedidoNotFound.class, () -> alterarPedidoStatusUseCase.recebido(pedidoId));

        verify(alterarPedidoStatusValidator).validarRecebido(pedidoId);
        verify(alterarPedidoStatusOutputPort).recebido(pedidoId);
    }

    @Test
    void iniciarPreparo() {


        Pedido pedido = new Pedido();
        pedido.setId(pedidoId);
        pedido.setStatusPedido(STATUS_PEDIDO_EM_PREPARO);

        CozinhaPedido cozinhaPedido = new CozinhaPedido();
        cozinhaPedido.setIdPedido(pedidoId);
        cozinhaPedido.setStatusPedido(pedido.getStatusPedido());
        cozinhaPedido.setProcessoAtual(RECEBIDO);

        when(alterarPedidoStatusInputPort.emPreparo(pedidoId)).thenReturn(pedido);
        when(cozinhaPedidoOutputPort.salvar(cozinhaPedido)).thenReturn(cozinhaPedido);
        when(cozinhaPedidoOutputPort.consultarPorIdPedido(pedidoId)).thenReturn(Optional.of(cozinhaPedido));

        // Act
        CozinhaPedido result = cozinhaPedidoUseCase.iniciarPreparo(pedidoId);

        // Assert
        assertEquals(cozinhaPedido, result);
        verify(cozinhaPedidoOutputPort).salvar(result);

    }

    @Test
    void iniciarPreparo_erro() {


        //Arrange
        when(alterarPedidoStatusOutputPort.emPreparo(pedidoId)).thenReturn(Optional.empty());

        //Act & Assert
        assertThrows(PedidoNotFound.class, () -> alterarPedidoStatusUseCase.emPreparo(pedidoId));

        verify(alterarPedidoStatusValidator).validarEmPreparo(pedidoId);
        verify(alterarPedidoStatusOutputPort).emPreparo(pedidoId);

    }


    @Test
    void finalizarPreparo() {


        Pedido pedido = new Pedido();
        pedido.setId(pedidoId);
        pedido.setStatusPedido(STATUS_PEDIDO_EM_PREPARO);

        CozinhaPedido cozinhaPedido = new CozinhaPedido();
        cozinhaPedido.setIdPedido(pedidoId);
        cozinhaPedido.setStatusPedido(pedido.getStatusPedido());
        cozinhaPedido.setProcessoAtual(RECEBIDO);

        when(alterarPedidoStatusInputPort.pronto(pedidoId)).thenReturn(pedido);
        when(cozinhaPedidoOutputPort.salvar(cozinhaPedido)).thenReturn(cozinhaPedido);
        when(cozinhaPedidoOutputPort.consultarPorIdPedido(pedidoId)).thenReturn(Optional.of(cozinhaPedido));


        // Act
        CozinhaPedido result = cozinhaPedidoUseCase.finalizarPreparo(pedidoId);

        // Assert
        assertEquals(cozinhaPedido, result);
        verify(cozinhaPedidoOutputPort).salvar(result);
    }

    @Test
    void finalizarPreparo_erro() {


        //Arrange
        when(alterarPedidoStatusOutputPort.finalizado(pedidoId)).thenReturn(Optional.empty());

        //Act & Assert
        assertThrows(PedidoNotFound.class, () -> alterarPedidoStatusUseCase.finalizado(pedidoId));

        verify(alterarPedidoStatusValidator).validarFinalizado(pedidoId);
        verify(alterarPedidoStatusOutputPort).finalizado(pedidoId);

    }


    @Test
    void retirar() {


        Pedido pedido = new Pedido();
        pedido.setId(pedidoId);
        pedido.setStatusPedido(STATUS_PEDIDO_PRONTO);

        CozinhaPedido cozinhaPedido = new CozinhaPedido();
        cozinhaPedido.setIdPedido(pedidoId);
        cozinhaPedido.setStatusPedido(pedido.getStatusPedido());
        cozinhaPedido.setProcessoAtual(ENTREGAR_PEDIDO);

        when(alterarPedidoStatusInputPort.finalizado(pedidoId)).thenReturn(pedido);
        when(cozinhaPedidoOutputPort.salvar(cozinhaPedido)).thenReturn(cozinhaPedido);
        when(cozinhaPedidoOutputPort.consultarPorIdPedido(pedidoId)).thenReturn(Optional.of(cozinhaPedido));


        // Act
        CozinhaPedido result = cozinhaPedidoUseCase.retirar(pedidoId);

        // Assert
        assertEquals(cozinhaPedido, result);
        verify(cozinhaPedidoOutputPort).salvar(result);
    }

    @Test
    void retirarErro() {


        //Arrange
        when(alterarPedidoStatusOutputPort.finalizado(pedidoId)).thenReturn(Optional.empty());

        //Act & Assert
        assertThrows(PedidoNotFound.class, () -> alterarPedidoStatusUseCase.finalizado(pedidoId));

        verify(alterarPedidoStatusValidator).validarFinalizado(pedidoId);
        verify(alterarPedidoStatusOutputPort).finalizado(pedidoId);

    }
}