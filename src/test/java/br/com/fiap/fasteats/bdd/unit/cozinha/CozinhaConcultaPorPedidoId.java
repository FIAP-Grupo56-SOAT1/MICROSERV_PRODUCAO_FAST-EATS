package br.com.fiap.fasteats.bdd.unit.cozinha;

import br.com.fiap.fasteats.core.dataprovider.AlterarPedidoStatusOutputPort;
import br.com.fiap.fasteats.core.dataprovider.CozinhaPedidoOutputPort;
import br.com.fiap.fasteats.core.dataprovider.PedidoOutputPort;
import br.com.fiap.fasteats.core.usecase.AlterarPedidoStatusInputPort;
import br.com.fiap.fasteats.core.usecase.impl.AlterarPedidoStatusUseCase;
import br.com.fiap.fasteats.core.usecase.impl.CozinhaPedidoUseCase;
import br.com.fiap.fasteats.core.validator.AlterarPedidoStatusValidator;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;


public class CozinhaConcultaPorPedidoId {

    AutoCloseable openMocks;
    LocalDateTime dateTimeNow = LocalDateTime.now();
    @Before
    public void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        openMocks.close();
    }

    @Mock
    private CozinhaPedidoOutputPort cozinhaPedidoOutputPort;

    @Mock
    private PedidoOutputPort pedidoOutputPort;

    @Mock
    private AlterarPedidoStatusInputPort alterarPedidoStatusInputPort;

    @InjectMocks
    private CozinhaPedidoUseCase cozinhaPedidoUseCase;


    @Mock
    private AlterarPedidoStatusOutputPort alterarPedidoStatusOutputPort;

    @InjectMocks
    private AlterarPedidoStatusUseCase alterarPedidoStatusUseCase;


    @Mock
    private AlterarPedidoStatusValidator alterarPedidoStatusValidator;



    @Dado("que o a cozinha recebe o pedidoId")
    public void que_o_a_cozinha_recebe_o_pedido_id() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Quando("o pedido ja foi iniciado na cozinha")
    public void o_pedido_ja_foi_iniciado_na_cozinha() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Entao("os dados do pedido fica disponivel na cozinha")
    public void os_dados_do_pedido_fica_disponivel_na_cozinha() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


}
