package br.com.fiap.fasteats.core.validator.impl;

import br.com.fiap.fasteats.core.dataprovider.PedidoOutputPort;
import br.com.fiap.fasteats.core.domain.exception.RegraNegocioException;
import br.com.fiap.fasteats.core.domain.model.Pedido;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static br.com.fiap.fasteats.core.constants.StatusPedidoConstants.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AlterarPedidoStatusValidatorImplUnitTest {
    @Mock
    private PedidoOutputPort pedidoOutputPort;
    @InjectMocks
    private AlterarPedidoStatusValidatorImpl alterarPedidoStatusValidatorImpl;
    Long pedidoId = 1L;

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
    void validarRecebido() {
        // Arrange
        Pedido pedido = getPedido(pedidoId, STATUS_PEDIDO_PAGO);
        when(pedidoOutputPort.consultar(pedidoId)).thenReturn(pedido);

        // Act
        alterarPedidoStatusValidatorImpl.validarRecebido(pedidoId);

        // Assert
        verify(pedidoOutputPort).consultar(pedidoId);
    }

    @Test
    void validarRecebido_DeveLancarExcecao_QuandoStatusPedidoNaoForPago() {
        // Arrange
        Pedido pedido = getPedido(pedidoId, STATUS_PEDIDO_RECEBIDO);
        when(pedidoOutputPort.consultar(pedidoId)).thenReturn(pedido);

        // Act & Assert
        assertThrows(RegraNegocioException.class, () -> alterarPedidoStatusValidatorImpl.validarRecebido(pedidoId));
    }

    @Test
    void validarEmPreparo() {
        // Arrange
        Pedido pedido = getPedido(pedidoId, STATUS_PEDIDO_RECEBIDO);
        when(pedidoOutputPort.consultar(pedidoId)).thenReturn(pedido);

        // Act
        alterarPedidoStatusValidatorImpl.validarEmPreparo(pedidoId);

        // Assert
        verify(pedidoOutputPort).consultar(pedidoId);
    }

    @Test
    void validarEmPreparo_DeveLancarExcecao_QuandoStatusPedidoNaoForRecebido() {
        // Arrange
        Pedido pedido = getPedido(pedidoId, STATUS_PEDIDO_EM_PREPARO);
        when(pedidoOutputPort.consultar(pedidoId)).thenReturn(pedido);

        // Act & Assert
        assertThrows(RegraNegocioException.class, () -> alterarPedidoStatusValidatorImpl.validarEmPreparo(pedidoId));
    }

    @Test
    void validarPronto() {
        // Arrange
        Pedido pedido = getPedido(pedidoId, STATUS_PEDIDO_EM_PREPARO);
        when(pedidoOutputPort.consultar(pedidoId)).thenReturn(pedido);

        // Act
        alterarPedidoStatusValidatorImpl.validarPronto(pedidoId);

        // Assert
        verify(pedidoOutputPort).consultar(pedidoId);
    }

    @Test
    void validarPronto_DeveLancarExcecao_QuandoStatusPedidoNaoForEmPreparo() {
        // Arrange
        Pedido pedido = getPedido(pedidoId, STATUS_PEDIDO_PRONTO);
        when(pedidoOutputPort.consultar(pedidoId)).thenReturn(pedido);

        // Act & Assert
        assertThrows(RegraNegocioException.class, () -> alterarPedidoStatusValidatorImpl.validarPronto(pedidoId));
    }

    @Test
    void validarFinalizado() {
        // Arrange
        Pedido pedido = getPedido(pedidoId, STATUS_PEDIDO_PRONTO);
        when(pedidoOutputPort.consultar(pedidoId)).thenReturn(pedido);

        // Act
        alterarPedidoStatusValidatorImpl.validarFinalizado(pedidoId);

        // Assert
        verify(pedidoOutputPort).consultar(pedidoId);
    }

    @Test
    void validarFinalizado_DeveLancarExcecao_QuandoStatusPedidoNaoForPronto() {
        // Arrange
        Pedido pedido = getPedido(pedidoId, STATUS_PEDIDO_FINALIZADO);
        when(pedidoOutputPort.consultar(pedidoId)).thenReturn(pedido);

        // Act & Assert
        assertThrows(RegraNegocioException.class, () -> alterarPedidoStatusValidatorImpl.validarFinalizado(pedidoId));
    }

    private Pedido getPedido(Long id, String statusPedido) {
        LocalDateTime dataHoraTeste = LocalDateTime.now();
        return new Pedido(id, statusPedido, dataHoraTeste, dataHoraTeste, dataHoraTeste);
    }
}
