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

import static br.com.fiap.fasteats.core.constants.StatusPedidoConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class PedidoFinalizado {

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

    @Dado("que o pedido está finalizado")
    public void que_o_pedido_está_finalizado() {
       pedido = getPedido();
        when(alterarPedidoStatusOutputPort.finalizado(PEDIDO_ID)).thenReturn(Optional.of(pedido));
    }
    @Quando("o pedido e definido na cozinha como status finalizado")
    public void o_pedido_e_definido_na_cozinha_como_status_finalizado() {
        pedidoRecebido = alterarPedidoStatusUseCase.finalizado(PEDIDO_ID);
    }
    @Entao("o pedido é atualiza o pedido")
    public void o_pedido_é_atualiza_o_pedido() {
        assertEquals(STATUS_PEDIDO_FINALIZADO, pedidoRecebido.getStatusPedido());

        verify(alterarPedidoStatusValidator).validarFinalizado(PEDIDO_ID);
        verify(alterarPedidoStatusOutputPort).finalizado(PEDIDO_ID);
    }

    private Pedido getPedido() {
        Pedido pedido = new Pedido();
        pedido.setStatusPedido(STATUS_PEDIDO_FINALIZADO);
        pedido.setId(PEDIDO_ID);
        pedido.setDataHoraCriado(dateTimeNow);
        pedido.setDataHoraRecebimento(dateTimeNow);
        pedido.setDataHoraFinalizado(dateTimeNow);
        return pedido;
    }

//    private Pedido getPedidoDifernteFinalizado() {
//        Pedido pedidoStatusDiferenteFinalizado = new Pedido();
//        pedidoStatusDiferenteFinalizado.setStatusPedido(STATUS_PEDIDO_RECEBIDO);
//        pedidoStatusDiferenteFinalizado.setId(2l);
//        pedidoStatusDiferenteFinalizado.setDataHoraCriado(dateTimeNow);
//        pedidoStatusDiferenteFinalizado.setDataHoraRecebimento(dateTimeNow);
//        pedidoStatusDiferenteFinalizado.setDataHoraFinalizado(dateTimeNow);
//        return pedidoStatusDiferenteFinalizado;
//    }

    @Dado("que o pedido nao esta com status finalizado")
    public void que_o_pedido_nao_esta_com_status_finalizado() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Quando("o pedido esta com status difernete de pronto")
    public void o_pedido_esta_com_status_difernete_de_pronto() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Entao("o pedido nao e finalizado na cozinha")
    public void o_pedido_nao_e_finalizado_na_cozinha() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
