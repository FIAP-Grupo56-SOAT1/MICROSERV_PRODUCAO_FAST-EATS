package br.com.fiap.fasteats.core.usecase.impl.unit;

import br.com.fiap.fasteats.core.dataprovider.CozinhaPedidoOutputPort;
import br.com.fiap.fasteats.core.domain.model.CozinhaPedido;
import br.com.fiap.fasteats.core.usecase.AlterarPedidoStatusInputPort;
import br.com.fiap.fasteats.core.usecase.impl.CozinhaPedidoUseCase;
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
import static org.mockito.Mockito.when;

class CozinhaPedidoInputPortTest {


    @Mock
    private  CozinhaPedidoOutputPort cozinhaPedidoOutputPort;

    @Mock
    private  AlterarPedidoStatusInputPort alterarPedidoStatusInputPort;

    @InjectMocks
    private CozinhaPedidoUseCase  cozinhaPedidoUseCase;

    private AutoCloseable openMocks;

    private Long pedidoId = 1l;
    private Long cozinhaId = 1l;
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
                new CozinhaPedido( LocalDateTime.now(),LocalDateTime.now(),LocalDateTime.now(),LocalDateTime.now(),1l,STATUS_PEDIDO_EM_PREPARO,RECEBIDO),
                new CozinhaPedido( LocalDateTime.now(),LocalDateTime.now(),LocalDateTime.now(),LocalDateTime.now(),2l,STATUS_PEDIDO_EM_PREPARO,INICIO_PREPARO),
                new CozinhaPedido( LocalDateTime.now(),LocalDateTime.now(),LocalDateTime.now(),LocalDateTime.now(),3l,STATUS_PEDIDO_EM_PREPARO,FINALIZANDO_PREPARO),
                new CozinhaPedido( LocalDateTime.now(),LocalDateTime.now(),LocalDateTime.now(),LocalDateTime.now(),4l,STATUS_PEDIDO_EM_PREPARO,ENTREGAR_PEDIDO)
        );

        when(cozinhaPedidoOutputPort.listar()).thenReturn(listCozinhaPedido);

        // Act
        List<CozinhaPedido> result = cozinhaPedidoUseCase.listar();

        // Assert
        assertEquals(listCozinhaPedido, result);
    }

    @Test
    void consultar() {
    }

    @Test
    void consultarPorIdPedido() {
    }

    @Test
    void receber() {
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