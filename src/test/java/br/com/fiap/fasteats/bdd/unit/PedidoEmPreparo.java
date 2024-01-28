package br.com.fiap.fasteats.bdd.unit;

import br.com.fiap.fasteats.core.dataprovider.AlterarPedidoStatusOutputPort;
import br.com.fiap.fasteats.core.domain.exception.PedidoNotFound;
import br.com.fiap.fasteats.core.domain.exception.RegraNegocioException;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class PedidoEmPreparo {

    AutoCloseable openMocks;

    LocalDateTime dateTimeNow = LocalDateTime.now();

    Pedido pedido;

    Pedido pedidoRecebido;

    Pedido pedidoNaoPago ;

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

    @Dado("que o pedido está com status recebido")
    public void que_o_pedido_está_com_status_recebido() {
        pedido = getPedido();
        when(alterarPedidoStatusOutputPort.emPreparo(PEDIDO_ID)).thenReturn(Optional.of(pedido));
    }
    @Quando("o pedido e preparado na cozinha")
    public void o_pedido_e_preparado_na_cozinha() {
        pedidoRecebido = alterarPedidoStatusUseCase.emPreparo(PEDIDO_ID);
    }
    @Entao("o pedido e definido como status em preparo")
    public void o_pedido_e_definido_como_status_em_preparo() {
        assertEquals(STATUS_PEDIDO_RECEBIDO,pedidoRecebido.getStatusPedido());

        verify(alterarPedidoStatusValidator).validarEmPreparo(PEDIDO_ID);
        verify(alterarPedidoStatusOutputPort).emPreparo(PEDIDO_ID);
    }


    @Dado("que o pedido que não existe é enviado para cozinha")
    public void que_o_pedido_que_não_existe_é_enviado_para_cozinha() {
        when(alterarPedidoStatusOutputPort.recebido(getPedido().getId())).thenReturn(Optional.empty());
    }
    @Quando("o pedido e nao existe")
    public void o_pedido_e_nao_existe() {
        assertThrows(PedidoNotFound.class, () -> alterarPedidoStatusUseCase.recebido(getPedido().getId()));
    }
    @Entao("o pedido nao e preparado e retorna mensagem de erro")
    public void o_pedido_nao_e_preparado_e_retorna_mensagem_de_erro() {
        verify(alterarPedidoStatusValidator).validarRecebido(getPedido().getId());
        verify(alterarPedidoStatusOutputPort).recebido(getPedido().getId());
    }


    @Dado("que o pedido enviado nao esta pago")
    public void que_o_pedido_enviado_nao_esta_pago() {
        pedidoNaoPago = getPedidoNaoPago();
        when(alterarPedidoStatusOutputPort.recebido(pedidoNaoPago.getId())).thenReturn(Optional.of(pedidoNaoPago));
    }
    @Quando("o pedido esta com staus diferente de pago")
    public void o_pedido_esta_com_staus_difernte_de_pago() {

    }
    @Entao("o pedido nao pode ser recebido na cozinha")
    public void o_pedido_nao_pode_ser_recebido_na_cozinha() {

    }


    private Pedido getPedido(){
        Pedido pedido = new Pedido();
        pedido.setStatusPedido(STATUS_PEDIDO_RECEBIDO);
        return pedido;
    }

    private Pedido getPedidoNaoPago(){
        Pedido pedidoNaoPago = new Pedido();
        pedidoNaoPago.setStatusPedido(STATUS_PEDIDO_RECEBIDO);
        pedidoNaoPago.setId(2l);
        return pedidoNaoPago;
    }



}
