package br.com.fiap.fasteats.core.usecase.impl.unit;

import br.com.fiap.fasteats.core.dataprovider.CozinhaPedidoOutputPort;
import br.com.fiap.fasteats.core.domain.exception.CozinhaPedidoNotFound;
import br.com.fiap.fasteats.core.domain.model.CozinhaPedido;
import br.com.fiap.fasteats.core.usecase.impl.CozinhaPedidoUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class CozinhaPedidoUseCaseUnitTest {
    @Mock
    private CozinhaPedidoOutputPort cozinhaPedidoOutputPort;
    @InjectMocks
    private CozinhaPedidoUseCase cozinhaPedidoUseCase;
    private AutoCloseable openMocks;
    private final String COZINHA_PEDIDO_ID = "1";

    @BeforeEach
    public void setUp() {
        openMocks = openMocks(this);
    }

    @AfterEach
    public void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    @DisplayName("Listar Cozinha Pedido")
    void listar() {
        // Arrange
        CozinhaPedido cozinhaPedido1 = new CozinhaPedido();
        CozinhaPedido cozinhaPedido2 = new CozinhaPedido();

        when(cozinhaPedidoOutputPort.listar()).thenReturn(Arrays.asList(cozinhaPedido1, cozinhaPedido2));

        // Act
        List<CozinhaPedido> cozinhaPedidos = cozinhaPedidoUseCase.listar();

        // Assert
        assertEquals(Arrays.asList(cozinhaPedido1, cozinhaPedido2), cozinhaPedidos);
        verify(cozinhaPedidoOutputPort).listar();
    }

    @Test
    @DisplayName("Consultar Cozinha Pedido")
    void consultar() {
        // Arrange
        CozinhaPedido cozinhaPedido = new CozinhaPedido();


        when(cozinhaPedidoOutputPort.consultar(COZINHA_PEDIDO_ID)).thenReturn(Optional.of(cozinhaPedido));

        // Act
        CozinhaPedido result = cozinhaPedidoUseCase.consultar(COZINHA_PEDIDO_ID);

        // Assert
        assertEquals(cozinhaPedido, result);
        verify(cozinhaPedidoOutputPort).consultar(COZINHA_PEDIDO_ID);
    }

    @Test
    @DisplayName("Consultar Cozinha Pedido não encontrado")
    void consultarCozinhaPedidoNaoEncontrado() {
        // Arrange
        when(cozinhaPedidoOutputPort.consultar(COZINHA_PEDIDO_ID)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CozinhaPedidoNotFound.class, () -> cozinhaPedidoUseCase.consultar(COZINHA_PEDIDO_ID));
    }

    @Test
    @DisplayName("Consultar por id pedido")
    void ConsultarPorIdPedido() {
        // Arrange
        Long pedidoId = 1L;
        CozinhaPedido cozinhaPedido = new CozinhaPedido();
        cozinhaPedido.setIdPedido(pedidoId);
        when(cozinhaPedidoOutputPort.consultarPorIdPedido(pedidoId)).thenReturn(Optional.of(cozinhaPedido));

        // Act
        CozinhaPedido result = cozinhaPedidoUseCase.consultarPorIdPedido(pedidoId);

        // Assert
        assertEquals(cozinhaPedido, result);
        verify(cozinhaPedidoOutputPort).consultarPorIdPedido(pedidoId);
    }

    @Test
    @DisplayName("Consultar por id pedido não encontrado")
    void consultarPorIdPedidoNaoEncontrado() {
        // Arrange
        when(cozinhaPedidoOutputPort.consultarPorIdPedido(2L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CozinhaPedidoNotFound.class, () -> cozinhaPedidoUseCase.consultarPorIdPedido(2L));
    }
}
