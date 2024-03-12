package br.com.fiap.fasteats.core.usecase.impl.unit;

import br.com.fiap.fasteats.core.dataprovider.CozinhaPedidoOutputPort;
import br.com.fiap.fasteats.core.domain.model.CozinhaPedido;
import br.com.fiap.fasteats.core.usecase.impl.CompensarErroAlterarStatusPedidoUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static br.com.fiap.fasteats.core.constants.ProcessoCozinhaConstants.*;
import static br.com.fiap.fasteats.core.constants.StatusPedidoConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class CompensarErroAlterarStatusPedidoUseCaseUnitTest {
    @Mock
    private CozinhaPedidoOutputPort cozinhaPedidoOutputPort;
    @InjectMocks
    private CompensarErroAlterarStatusPedidoUseCase compensarErroAlterarStatusPedidoUseCase;
    private AutoCloseable openMocks;
    private final Long PEDIDO_ID = 1L;

    @BeforeEach
    public void setUp() {
        openMocks = openMocks(this);
    }

    @AfterEach
    public void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    @DisplayName("Deve alterar o status do pedido para pendente")
    void pendente() {
        // Arrange
        CozinhaPedido cozinhaPedido = getCozinhaPedido(PEDIDO_ID);

        when(cozinhaPedidoOutputPort.consultarPorIdPedido(PEDIDO_ID)).thenReturn(Optional.of(cozinhaPedido));

        // Act
        compensarErroAlterarStatusPedidoUseCase.pendente(PEDIDO_ID);

        // Assert
        assertEquals(STATUS_PEDIDO_PAGO, cozinhaPedido.getStatusPedido());
        assertEquals(STATUS_COZINHA_PENDENTE, cozinhaPedido.getProcessoAtual());
        assertNull(cozinhaPedido.getDataRecebimentoDoPedido());
        verify(cozinhaPedidoOutputPort).consultarPorIdPedido(PEDIDO_ID);
        verify(cozinhaPedidoOutputPort).salvar(cozinhaPedido);
    }

    @Test
    @DisplayName("Deve alterar o status do pedido para recebido")
    void recebido() {
        // Arrange
        CozinhaPedido cozinhaPedido = getCozinhaPedido(PEDIDO_ID);

        when(cozinhaPedidoOutputPort.consultarPorIdPedido(PEDIDO_ID)).thenReturn(Optional.of(cozinhaPedido));

        // Act
        compensarErroAlterarStatusPedidoUseCase.recebido(PEDIDO_ID);

        // Assert
        assertEquals(STATUS_PEDIDO_RECEBIDO, cozinhaPedido.getStatusPedido());
        assertEquals(STATUS_COZINHA_RECEBIDO, cozinhaPedido.getProcessoAtual());
        assertNotNull(cozinhaPedido.getDataRecebimentoDoPedido());
        assertNull(cozinhaPedido.getDataInicioPreparo());
        assertNull(cozinhaPedido.getDataFinalizacaoPreparo());
        assertNull(cozinhaPedido.getDataEntregaPedido());
        verify(cozinhaPedidoOutputPort).consultarPorIdPedido(PEDIDO_ID);
        verify(cozinhaPedidoOutputPort).salvar(cozinhaPedido);
    }

    @Test
    @DisplayName("Deve alterar o status do pedido para em preparo")
    void emPreparo() {
        // Arrange
        CozinhaPedido cozinhaPedido = getCozinhaPedido(PEDIDO_ID);

        when(cozinhaPedidoOutputPort.consultarPorIdPedido(PEDIDO_ID)).thenReturn(Optional.of(cozinhaPedido));

        // Act
        compensarErroAlterarStatusPedidoUseCase.emPreparo(PEDIDO_ID);

        // Assert
        assertEquals(STATUS_PEDIDO_EM_PREPARO, cozinhaPedido.getStatusPedido());
        assertEquals(STATUS_COZINHA_INICIO_PREPARO, cozinhaPedido.getProcessoAtual());
        assertNotNull(cozinhaPedido.getDataInicioPreparo());
        assertNull(cozinhaPedido.getDataFinalizacaoPreparo());
        assertNull(cozinhaPedido.getDataEntregaPedido());
        verify(cozinhaPedidoOutputPort).consultarPorIdPedido(PEDIDO_ID);
        verify(cozinhaPedidoOutputPort).salvar(cozinhaPedido);
    }

    @Test
    @DisplayName("Deve alterar o status do pedido para pronto")
    void pronto() {
        // Arrange
        CozinhaPedido cozinhaPedido = getCozinhaPedido(PEDIDO_ID);

        when(cozinhaPedidoOutputPort.consultarPorIdPedido(PEDIDO_ID)).thenReturn(Optional.of(cozinhaPedido));

        // Act
        compensarErroAlterarStatusPedidoUseCase.pronto(PEDIDO_ID);

        // Assert
        assertEquals(STATUS_PEDIDO_PRONTO, cozinhaPedido.getStatusPedido());
        assertEquals(STATUS_COZINHA_FINALIZANDO_PREPARO, cozinhaPedido.getProcessoAtual());
        assertNotNull(cozinhaPedido.getDataFinalizacaoPreparo());
        assertNull(cozinhaPedido.getDataEntregaPedido());
        verify(cozinhaPedidoOutputPort).consultarPorIdPedido(PEDIDO_ID);
        verify(cozinhaPedidoOutputPort).salvar(cozinhaPedido);
    }

    @Test
    @DisplayName("Deve consultar o pedido por id e retornar uma exceção caso não encontre")
    void consultarPorIdPedido() {
        // Arrange
        when(cozinhaPedidoOutputPort.consultarPorIdPedido(PEDIDO_ID)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> compensarErroAlterarStatusPedidoUseCase.pendente(PEDIDO_ID));
        verify(cozinhaPedidoOutputPort).consultarPorIdPedido(PEDIDO_ID);
    }

    private CozinhaPedido getCozinhaPedido(Long pedidoId) {
        return new CozinhaPedido("1", null, null, null, null, pedidoId, null, null);
    }
}
