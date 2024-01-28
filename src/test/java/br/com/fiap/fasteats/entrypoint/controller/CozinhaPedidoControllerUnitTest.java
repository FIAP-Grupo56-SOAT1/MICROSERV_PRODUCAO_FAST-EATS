package br.com.fiap.fasteats.entrypoint.controller;

import br.com.fiap.fasteats.core.domain.model.CozinhaPedido;
import br.com.fiap.fasteats.core.usecase.CozinhaPedidoInputPort;
import br.com.fiap.fasteats.entrypoint.controller.mapper.CozinhaPedidoResponseMapper;
import br.com.fiap.fasteats.entrypoint.controller.response.CozinhaPedidoResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

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
    void receberPedido_DeveRetornarOk_QuandoComunicacaoBemSucedida() {
        // Arrange
        Long pedidoId = 1L;

        // Act
        ResponseEntity<Void> response = cozinhaPedidoController.receberPedido(pedidoId);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(cozinhaPedidoInputPort, times(1)).receber(pedidoId);
    }

    @Test
    void iniciarPreparo_DeveRetornarCozinhaPedidoResponse_QuandoComunicacaoBemSucedida() {
        // Arrange
        Long pedidoId = 1L;
        CozinhaPedido cozinhaPedido = new CozinhaPedido();
        CozinhaPedidoResponse cozinhaPedidoResponse = new CozinhaPedidoResponse();

        when(cozinhaPedidoInputPort.iniciarPreparo(pedidoId)).thenReturn(cozinhaPedido);
        when(cozinhaPedidoResponseMapper.toCozinhaPedidoResponse(cozinhaPedido)).thenReturn(cozinhaPedidoResponse);

        // Act
        ResponseEntity<CozinhaPedidoResponse> response = cozinhaPedidoController.iniciarPreparo(pedidoId);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(cozinhaPedidoResponse, response.getBody());
        verify(cozinhaPedidoInputPort, times(1)).iniciarPreparo(pedidoId);
        verify(cozinhaPedidoResponseMapper, times(1)).toCozinhaPedidoResponse(cozinhaPedido);
    }

    @Test
    void finalizarPreparo_DeveRetornarCozinhaPedidoResponse_QuandoComunicacaoBemSucedida() {
        // Arrange
        Long pedidoId = 1L;
        CozinhaPedido cozinhaPedido = new CozinhaPedido();
        CozinhaPedidoResponse cozinhaPedidoResponse = new CozinhaPedidoResponse();

        when(cozinhaPedidoInputPort.finalizarPreparo(pedidoId)).thenReturn(cozinhaPedido);
        when(cozinhaPedidoResponseMapper.toCozinhaPedidoResponse(cozinhaPedido)).thenReturn(cozinhaPedidoResponse);

        // Act
        ResponseEntity<CozinhaPedidoResponse> response = cozinhaPedidoController.finalizarPreparo(pedidoId);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(cozinhaPedidoResponse, response.getBody());
        verify(cozinhaPedidoInputPort, times(1)).finalizarPreparo(pedidoId);
        verify(cozinhaPedidoResponseMapper, times(1)).toCozinhaPedidoResponse(cozinhaPedido);
    }

    @Test
    void retirarPedido_DeveRetornarCozinhaPedidoResponse_QuandoComunicacaoBemSucedida() {
        // Arrange
        Long pedidoId = 1L;
        CozinhaPedido cozinhaPedido = new CozinhaPedido();
        CozinhaPedidoResponse cozinhaPedidoResponse = new CozinhaPedidoResponse();

        when(cozinhaPedidoInputPort.retirar(pedidoId)).thenReturn(cozinhaPedido);
        when(cozinhaPedidoResponseMapper.toCozinhaPedidoResponse(cozinhaPedido)).thenReturn(cozinhaPedidoResponse);

        // Act
        ResponseEntity<CozinhaPedidoResponse> response = cozinhaPedidoController.retirarPedido(pedidoId);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(cozinhaPedidoResponse, response.getBody());
        verify(cozinhaPedidoInputPort, times(1)).retirar(pedidoId);
        verify(cozinhaPedidoResponseMapper, times(1)).toCozinhaPedidoResponse(cozinhaPedido);
    }

    @Test
    void listar_DeveRetornarListaCozinhaPedidoResponse_QuandoComunicacaoBemSucedida() {
        // Arrange
        List<CozinhaPedido> cozinhaPedidoList = List.of(new CozinhaPedido());
        List<CozinhaPedidoResponse> cozinhaPedidoResponseList = List.of(new CozinhaPedidoResponse());

        when(cozinhaPedidoInputPort.listar()).thenReturn(cozinhaPedidoList);
        when(cozinhaPedidoResponseMapper.toCozinhaPedidoResponseList(cozinhaPedidoList)).thenReturn(cozinhaPedidoResponseList);

        // Act
        ResponseEntity<List<CozinhaPedidoResponse>> response = cozinhaPedidoController.listar();

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(cozinhaPedidoResponseList, response.getBody());
        verify(cozinhaPedidoInputPort, times(1)).listar();
        verify(cozinhaPedidoResponseMapper, times(1)).toCozinhaPedidoResponseList(cozinhaPedidoList);
    }

    @Test
    void consultarPorIdPedido_DeveRetornarCozinhaPedidoResponse_QuandoComunicacaoBemSucedida() {
        // Arrange
        Long pedidoId = 1L;
        CozinhaPedido cozinhaPedido = new CozinhaPedido();
        CozinhaPedidoResponse cozinhaPedidoResponse = new CozinhaPedidoResponse();

        when(cozinhaPedidoInputPort.consultarPorIdPedido(pedidoId)).thenReturn(cozinhaPedido);
        when(cozinhaPedidoResponseMapper.toCozinhaPedidoResponse(cozinhaPedido)).thenReturn(cozinhaPedidoResponse);

        // Act
        ResponseEntity<CozinhaPedidoResponse> response = cozinhaPedidoController.consultarPorIdPedido(pedidoId);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(cozinhaPedidoResponse, response.getBody());
        verify(cozinhaPedidoInputPort, times(1)).consultarPorIdPedido(pedidoId);
        verify(cozinhaPedidoResponseMapper, times(1)).toCozinhaPedidoResponse(cozinhaPedido);
    }
}
