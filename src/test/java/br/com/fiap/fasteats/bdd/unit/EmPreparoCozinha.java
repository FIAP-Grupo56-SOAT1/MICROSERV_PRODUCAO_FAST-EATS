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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class EmPreparoCozinha {

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


    private Pedido getPedido(){
        Pedido pedido = new Pedido();
        pedido.setStatusPedido(STATUS_PEDIDO_RECEBIDO);
        pedido.setId(PEDIDO_ID);
        pedido.setDataHoraRecebimento(dateTimeNow);
        pedido.setDataHoraCriado(dateTimeNow);
        return pedido;
    }

}
