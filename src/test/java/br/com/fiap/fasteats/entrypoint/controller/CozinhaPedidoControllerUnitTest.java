package br.com.fiap.fasteats.entrypoint.controller;

import br.com.fiap.fasteats.core.domain.model.CozinhaPedido;
import br.com.fiap.fasteats.core.usecase.CozinhaPedidoInputPort;
import br.com.fiap.fasteats.entrypoint.controller.mapper.CozinhaPedidoResponseMapper;
import br.com.fiap.fasteats.entrypoint.controller.response.CozinhaPedidoResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class CozinhaPedidoControllerUnitTest {

    @Mock
    private CozinhaPedidoInputPort cozinhaPedidoInputPort;

    @Mock
    private CozinhaPedidoResponseMapper cozinhaPedidoResponseMapper;

    @InjectMocks
    private CozinhaPedidoController cozinhaPedidoController;

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
    @DisplayName("Teste - Listar Pedidos")
    void deveRetornarListaPedidos_QuandoListar() {
        // Arrange
        List<CozinhaPedido> cozinhaPedidoList = List.of(new CozinhaPedido());
        List<CozinhaPedidoResponse> cozinhaPedidoResponseList = List.of(new CozinhaPedidoResponse());
        when(cozinhaPedidoInputPort.listar()).thenReturn(cozinhaPedidoList);
        when(cozinhaPedidoResponseMapper.toCozinhaPedidoResponseList(cozinhaPedidoList)).thenReturn(cozinhaPedidoResponseList);

        // Act
        ResponseEntity<List<CozinhaPedidoResponse>> result = cozinhaPedidoController.listar();

        // Assert
        assertNotNull(result);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(cozinhaPedidoResponseList, result.getBody());
    }

    @Test
    @DisplayName("Teste - Consultar Pedido por ID da Cozinha")
    void deveRetornarPedido_QuandoConsultarPorIdCozinha() {
        // Arrange
        String cozinhaId = "12345";
        CozinhaPedido cozinhaPedido = new CozinhaPedido();
        CozinhaPedidoResponse cozinhaPedidoResponse = new CozinhaPedidoResponse();
        when(cozinhaPedidoInputPort.consultar(cozinhaId)).thenReturn(cozinhaPedido);
        when(cozinhaPedidoResponseMapper.toCozinhaPedidoResponse(cozinhaPedido)).thenReturn(cozinhaPedidoResponse);

        // Act
        ResponseEntity<CozinhaPedidoResponse> result = cozinhaPedidoController.consultarPorIdPedido(cozinhaId);

        // Assert
        assertNotNull(result);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(cozinhaPedidoResponse, result.getBody());
    }

    @Test
    @DisplayName("Teste - Consultar Pedido por ID do Pedido")
    void deveRetornarPedido_QuandoConsultarPorIdPedido() {
        // Arrange
        Long pedidoId = 1L;
        CozinhaPedido cozinhaPedido = new CozinhaPedido();
        CozinhaPedidoResponse cozinhaPedidoResponse = new CozinhaPedidoResponse();
        when(cozinhaPedidoInputPort.consultarPorIdPedido(pedidoId)).thenReturn(cozinhaPedido);
        when(cozinhaPedidoResponseMapper.toCozinhaPedidoResponse(cozinhaPedido)).thenReturn(cozinhaPedidoResponse);

        // Act
        ResponseEntity<CozinhaPedidoResponse> result = cozinhaPedidoController.consultarPorIdPedido(pedidoId);

        // Assert
        assertNotNull(result);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(cozinhaPedidoResponse, result.getBody());
    }
}