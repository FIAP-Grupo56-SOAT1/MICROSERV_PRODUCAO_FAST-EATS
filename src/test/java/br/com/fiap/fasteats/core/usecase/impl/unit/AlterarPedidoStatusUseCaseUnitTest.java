package br.com.fiap.fasteats.core.usecase.impl.unit;

import br.com.fiap.fasteats.core.dataprovider.AlterarPedidoStatusOutputPort;
import br.com.fiap.fasteats.core.usecase.impl.AlterarPedidoStatusUseCase;
import br.com.fiap.fasteats.core.validator.AlterarPedidoStatusValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

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
        doNothing().when(alterarPedidoStatusValidator).validarRecebido(PEDIDO_ID);

        //Act
        alterarPedidoStatusUseCase.recebido(PEDIDO_ID);

        //Assert
        verify(alterarPedidoStatusValidator).validarRecebido(PEDIDO_ID);
        verify(alterarPedidoStatusOutputPort).recebido(PEDIDO_ID);
    }

    @Test
    void emPreparo() {
        //Arrange
        doNothing().when(alterarPedidoStatusValidator).validarEmPreparo(PEDIDO_ID);

        //Act
        alterarPedidoStatusUseCase.emPreparo(PEDIDO_ID);

        //Assert
        verify(alterarPedidoStatusValidator).validarEmPreparo(PEDIDO_ID);
        verify(alterarPedidoStatusOutputPort).emPreparo(PEDIDO_ID);
    }

    @Test
    void pronto() {
        //Arrange
        doNothing().when(alterarPedidoStatusValidator).validarPronto(PEDIDO_ID);

        //Act
        alterarPedidoStatusUseCase.pronto(PEDIDO_ID);

        //Assert
        verify(alterarPedidoStatusValidator).validarPronto(PEDIDO_ID);
        verify(alterarPedidoStatusOutputPort).pronto(PEDIDO_ID);
    }

    @Test
    void finalizado() {
        //Arrange
        doNothing().when(alterarPedidoStatusValidator).validarFinalizado(PEDIDO_ID);

        //Act
        alterarPedidoStatusUseCase.finalizado(PEDIDO_ID);

        //Assert
        verify(alterarPedidoStatusValidator).validarFinalizado(PEDIDO_ID);
        verify(alterarPedidoStatusOutputPort).finalizado(PEDIDO_ID);
    }
}