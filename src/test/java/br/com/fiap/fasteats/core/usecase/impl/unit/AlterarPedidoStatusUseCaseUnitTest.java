package br.com.fiap.fasteats.core.usecase.impl.unit;

import br.com.fiap.fasteats.core.dataprovider.AlterarPedidoStatusOutputPort;
import br.com.fiap.fasteats.core.domain.exception.PedidoNotFound;
import br.com.fiap.fasteats.core.domain.model.Pedido;
import br.com.fiap.fasteats.core.usecase.impl.AlterarPedidoStatusUseCase;
import br.com.fiap.fasteats.core.validator.AlterarPedidoStatusValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static br.com.fiap.fasteats.core.constants.StatusPedidoConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AlterarPedidoStatusUseCaseUnitTest {

    @Mock
    private AlterarPedidoStatusOutputPort alterarPedidoStatusOutputPort;
    @Mock
    private AlterarPedidoStatusValidator alterarPedidoStatusValidator;
    @InjectMocks
    private AlterarPedidoStatusUseCase alterarPedidoStatusUseCase;
    private AutoCloseable openMocks;

    private final Long PEDIDO_ID = 1L;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void recebido() {

        //Arrange
        Pedido pedido = new Pedido();
        pedido.setStatusPedido(STATUS_PEDIDO_RECEBIDO);
        pedido.setId(PEDIDO_ID);
        pedido.setDataHoraRecebimento(LocalDateTime.now());
        when(alterarPedidoStatusOutputPort.recebido(PEDIDO_ID)).thenReturn(Optional.of(pedido));

        //Act
        var pedidoRecebido = alterarPedidoStatusUseCase.recebido(PEDIDO_ID);

        //Assert
        assertEquals(STATUS_PEDIDO_RECEBIDO,pedidoRecebido.getStatusPedido());
        assertNotNull(pedidoRecebido.getDataHoraRecebimento());

        verify(alterarPedidoStatusValidator).validarRecebido(PEDIDO_ID);
        verify(alterarPedidoStatusOutputPort).recebido(PEDIDO_ID);
    }

    @Test
    void recebidoErro() {

        //Arrange
        when(alterarPedidoStatusOutputPort.recebido(PEDIDO_ID)).thenReturn(Optional.empty());

        //Act & Assert
        assertThrows(PedidoNotFound.class, () -> alterarPedidoStatusUseCase.recebido(PEDIDO_ID));

        verify(alterarPedidoStatusValidator).validarRecebido(PEDIDO_ID);
        verify(alterarPedidoStatusOutputPort).recebido(PEDIDO_ID);
    }

    @Test
    void emPreparo() {

        //Arrange
        Pedido pedido = new Pedido();
        pedido.setStatusPedido(STATUS_PEDIDO_RECEBIDO);
        pedido.setId(PEDIDO_ID);
        when(alterarPedidoStatusOutputPort.emPreparo(PEDIDO_ID)).thenReturn(Optional.of(pedido));

        //Act
        var pedidoRecebido = alterarPedidoStatusUseCase.emPreparo(PEDIDO_ID);

        //Assert
        assertEquals(STATUS_PEDIDO_RECEBIDO,pedidoRecebido.getStatusPedido());

        verify(alterarPedidoStatusValidator).validarEmPreparo(PEDIDO_ID);
        verify(alterarPedidoStatusOutputPort).emPreparo(PEDIDO_ID);
    }

    @Test
    void emPreparo_erro() {

        //Arrange
        when(alterarPedidoStatusOutputPort.emPreparo(PEDIDO_ID)).thenReturn(Optional.empty());

        //Act & Assert
        assertThrows(PedidoNotFound.class, () -> alterarPedidoStatusUseCase.emPreparo(PEDIDO_ID));

        verify(alterarPedidoStatusValidator).validarEmPreparo(PEDIDO_ID);
        verify(alterarPedidoStatusOutputPort).emPreparo(PEDIDO_ID);
    }

    @Test
    void pronto() {
        //Arrange
        Pedido pedido = new Pedido();
        pedido.setStatusPedido(STATUS_PEDIDO_PRONTO);
        pedido.setId(PEDIDO_ID);
        when(alterarPedidoStatusOutputPort.pronto(PEDIDO_ID)).thenReturn(Optional.of(pedido));

        //Act
        var pedidoRecebido = alterarPedidoStatusUseCase.pronto(PEDIDO_ID);

        //Assert
        assertEquals(STATUS_PEDIDO_PRONTO,pedidoRecebido.getStatusPedido());

        verify(alterarPedidoStatusValidator).validarPronto(PEDIDO_ID);
        verify(alterarPedidoStatusOutputPort).pronto(PEDIDO_ID);
    }

    @Test
    void pronto_erro() {
        //Arrange
        when(alterarPedidoStatusOutputPort.pronto(PEDIDO_ID)).thenReturn(Optional.empty());

        //Act & Assert
        assertThrows(PedidoNotFound.class, () -> alterarPedidoStatusUseCase.pronto(PEDIDO_ID));

        verify(alterarPedidoStatusValidator).validarPronto(PEDIDO_ID);
        verify(alterarPedidoStatusOutputPort).pronto(PEDIDO_ID);
    }

    @Test
    void finalizado() {
        //Arrange
        Pedido pedido = new Pedido();
        pedido.setStatusPedido(STATUS_PEDIDO_FINALIZADO);
        pedido.setId(PEDIDO_ID);
        when(alterarPedidoStatusOutputPort.finalizado(PEDIDO_ID)).thenReturn(Optional.of(pedido));

        //Act
        var pedidoRecebido = alterarPedidoStatusUseCase.finalizado(PEDIDO_ID);

        //Assert
        assertEquals(STATUS_PEDIDO_FINALIZADO,pedidoRecebido.getStatusPedido());

        verify(alterarPedidoStatusValidator).validarFinalizado(PEDIDO_ID);
        verify(alterarPedidoStatusOutputPort).finalizado(PEDIDO_ID);
    }
    @Test
    void finalizado_erro() {
        //Arrange
        when(alterarPedidoStatusOutputPort.finalizado(PEDIDO_ID)).thenReturn(Optional.empty());

        //Act & Assert
        assertThrows(PedidoNotFound.class, () -> alterarPedidoStatusUseCase.finalizado(PEDIDO_ID));

        verify(alterarPedidoStatusValidator).validarFinalizado(PEDIDO_ID);
        verify(alterarPedidoStatusOutputPort).finalizado(PEDIDO_ID);
    }
}