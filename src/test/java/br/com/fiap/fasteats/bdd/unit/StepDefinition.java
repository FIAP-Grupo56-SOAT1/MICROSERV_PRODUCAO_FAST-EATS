package br.com.fiap.fasteats.bdd.unit;


import br.com.fiap.fasteats.core.domain.model.CozinhaPedido;
import br.com.fiap.fasteats.core.domain.model.Pedido;
import br.com.fiap.fasteats.core.usecase.CozinhaPedidoInputPort;
import br.com.fiap.fasteats.entrypoint.controller.mapper.CozinhaPedidoResponseMapper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static br.com.fiap.fasteats.core.constants.StatusPedidoConstants.STATUS_PEDIDO_PAGO;
import static org.mockito.Mockito.when;

public class StepDefinition {


    @Mock
    private CozinhaPedidoInputPort cozinhaPedidoInputPort;

    @Mock
    private CozinhaPedidoResponseMapper cozinhaPedidoResponseMapper;
    private Long pedidoId = 1L;

    private CozinhaPedido cozinhaPedido;
    AutoCloseable openMocks;
    @Before
    public void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        openMocks.close();
    }

    @Quando("o pedido e recebido na cozinha")
    public void o_pedido_e_recebido_na_cozinha() {
        //cozinhaPedidoInputPort.receber(pedidoId);
        when(cozinhaPedidoInputPort.receber(pedidoId)).thenReturn(cozinhaPedido);
    }


    @Entao("o pedido e definido como status recebido")
    public void o_pedido_e_definido_como_status_recebido() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
    }


    @Dado("que o numero do pedido e informado na cozinha")
    public void queONumeroDoPedidoEInformadoNaCozinha() {
    }


    private Pedido getPedido(){

        var pedido = new Pedido();
        pedido.setStatusPedido(STATUS_PEDIDO_PAGO);
        pedido.setId(pedidoId);

        return pedido;
    }
}
