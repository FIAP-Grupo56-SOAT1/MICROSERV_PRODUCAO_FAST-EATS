package br.com.fiap.fasteats.bdd.unit;

import br.com.fiap.fasteats.core.dataprovider.AlterarPedidoStatusOutputPort;
import br.com.fiap.fasteats.core.domain.model.Pedido;
import br.com.fiap.fasteats.core.usecase.impl.AlterarPedidoStatusUseCase;
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
import java.util.Optional;

import static br.com.fiap.fasteats.core.constants.StatusPedidoConstants.STATUS_PEDIDO_RECEBIDO;
import static org.mockito.Mockito.when;


public class StepDefinition {

    AutoCloseable openMocks;

    LocalDateTime dateTimeNow = LocalDateTime.now();

    Pedido pedido;

    @Before
    public void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        openMocks.close();
    }

    @Mock
    private AlterarPedidoStatusOutputPort alterarPedidoStatusOutputPort;

    @Mock
    private AlterarPedidoStatusValidator alterarPedidoStatusValidator;

    @InjectMocks
    private AlterarPedidoStatusUseCase alterarPedidoStatusUseCase;

    private final Long PEDIDO_ID = 1L;

    @Dado("que o pedido e criado e enviado para cozinha")
    public void que_o_pedido_e_criado_e_enviado_para_cozinha() {
        pedido = getPedido();
        when(alterarPedidoStatusOutputPort.recebido(PEDIDO_ID)).thenReturn(Optional.of(pedido));
        var pedidoRecebido = alterarPedidoStatusUseCase.recebido(PEDIDO_ID);
    }
    @Quando("o pedido e recebido e processado na cozinha")
    public void o_pedido_e_recebido_e_processado_na_cozinha() {

    }
    @Entao("o pedido e definido como status recebido")
    public void o_pedido_e_definido_como_status_recebido() {

    }


    private Pedido getPedido(){
        Pedido pedido = new Pedido();
        pedido.setStatusPedido(STATUS_PEDIDO_RECEBIDO);
        pedido.setId(PEDIDO_ID);
        pedido.setDataHoraRecebimento(dateTimeNow);

        return pedido;
    }

}
