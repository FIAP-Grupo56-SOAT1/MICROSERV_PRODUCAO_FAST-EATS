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

import static br.com.fiap.fasteats.core.constants.StatusPedidoConstants.STATUS_PEDIDO_PRONTO;
import static br.com.fiap.fasteats.core.constants.StatusPedidoConstants.STATUS_PEDIDO_RECEBIDO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class PedidoPronto {

    AutoCloseable openMocks;

    LocalDateTime dateTimeNow = LocalDateTime.now();

    Pedido pedido;

    Pedido pedidoRecebido;

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


    @Dado("que o pedido está pronto na cozinha")
    public void que_o_pedido_está_pronto_na_cozinha() {
        pedido = getPedido();
        when(alterarPedidoStatusOutputPort.pronto(PEDIDO_ID)).thenReturn(Optional.of(pedido));
    }

    @Quando("o pedido e definido na cozinha como status pronto")
    public void o_pedido_e_definido_na_cozinha_como_status_pronto() {
        pedidoRecebido = alterarPedidoStatusUseCase.pronto(PEDIDO_ID);
    }

    @Entao("a atualiza o pedido")
    public void a_atualiza_o_pedido() {
        assertEquals(STATUS_PEDIDO_PRONTO, pedidoRecebido.getStatusPedido());

        verify(alterarPedidoStatusValidator).validarPronto(PEDIDO_ID);
        verify(alterarPedidoStatusOutputPort).pronto(PEDIDO_ID);
    }


    @Dado("que o pedido existe no sistema")
    public void que_o_pedido_existe_no_sistema() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Quando("o pedido esta com status difente de em preparo")
    public void o_pedido_esta_com_status_difente_de_em_preparo() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Entao("o estatus do pedido nao pode ser atualizado para pronto")
    public void o_estatus_do_pedido_nao_pode_ser_atualizado_para_pronto() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    private Pedido getPedido() {
        Pedido pedido = new Pedido();
        pedido.setStatusPedido(STATUS_PEDIDO_PRONTO);
        pedido.setId(PEDIDO_ID);
        pedido.setDataHoraRecebimento(dateTimeNow);
        pedido.setDataHoraCriado(dateTimeNow);
        pedido.setDataHoraFinalizado(dateTimeNow);
        return pedido;
    }

}
