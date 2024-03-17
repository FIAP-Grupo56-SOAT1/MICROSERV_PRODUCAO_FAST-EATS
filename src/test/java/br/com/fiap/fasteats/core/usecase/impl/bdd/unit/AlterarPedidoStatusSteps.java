package br.com.fiap.fasteats.core.usecase.impl.bdd.unit;

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

import static br.com.fiap.fasteats.core.constants.StatusPedidoConstants.*;
import static org.mockito.Mockito.*;

public class AlterarPedidoStatusSteps {
    @Mock
    private AlterarPedidoStatusOutputPort alterarPedidoStatusOutputPort;
    @Mock
    private AlterarPedidoStatusValidator alterarPedidoStatusValidator;
    @InjectMocks
    private AlterarPedidoStatusUseCase alterarPedidoStatusUseCase;
    AutoCloseable openMocks;
    private Pedido pedido;

    @Before
    public void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @After
    public void close() throws Exception {
        openMocks.close();
    }

    @Dado("que existe um pedido com id {long}")
    public void existeUmPedidoComId(Long pedidoId) {
        pedido = new Pedido();
        pedido.setId(pedidoId);
    }

    @Quando("o usuário solicita a alteração do status do pedido {long} para Recebido")
    public void usuarioSolicitaAlteracaoParaRecebido(Long pedidoId) {
        doNothing().when(alterarPedidoStatusOutputPort).recebido(pedidoId);
    }

    @Quando("o usuário solicita a alteração do status do pedido {long} para Em Preparo")
    public void usuarioSolicitaAlteracaoParaEmPreparo(Long pedidoId) {
        doNothing().when(alterarPedidoStatusOutputPort).emPreparo(pedidoId);
    }

    @Quando("o usuário solicita a alteração do status do pedido {long} para Pronto")
    public void usuarioSolicitaAlteracaoParaPronto(Long pedidoId) {
        doNothing().when(alterarPedidoStatusOutputPort).pronto(pedidoId);
    }

    @Quando("o usuário solicita a alteração do status do pedido {long} para Finalizado")
    public void usuarioSolicitaAlteracaoParaFinalizado(Long pedidoId) {
        doNothing().when(alterarPedidoStatusOutputPort).finalizado(pedidoId);
    }

    @Entao("o status do pedido {long} é alterado para Recebido com sucesso")
    public void statusDoPedidoAlteradoParaRecebidoComSucesso(Long pedidoId) {
       alterarPedidoStatusUseCase.recebido(pedidoId);
        verify(alterarPedidoStatusValidator, times(1)).validarRecebido(pedidoId);
        verify(alterarPedidoStatusOutputPort, times(1)).recebido(pedidoId);
    }

    @Entao("o status do pedido {long} é alterado para Em Preparo com sucesso")
    public void statusDoPedidoAlteradoParaEmPreparoComSucesso(Long pedidoId) {
        alterarPedidoStatusUseCase.emPreparo(pedidoId);
        verify(alterarPedidoStatusValidator, times(1)).validarEmPreparo(pedidoId);
        verify(alterarPedidoStatusOutputPort, times(1)).emPreparo(pedidoId);
    }

    @Entao("o status do pedido {long} é alterado para Pronto com sucesso")
    public void statusDoPedidoAlteradoParaProntoComSucesso(Long pedidoId) {
        alterarPedidoStatusUseCase.pronto(pedidoId);
        verify(alterarPedidoStatusValidator, times(1)).validarPronto(pedidoId);
        verify(alterarPedidoStatusOutputPort, times(1)).pronto(pedidoId);
    }

    @Entao("o status do pedido {long} é alterado para Finalizado com sucesso")
    public void statusDoPedidoAlteradoParaFinalizadoComSucesso(Long pedidoId) {
         alterarPedidoStatusUseCase.finalizado(pedidoId);
        verify(alterarPedidoStatusValidator, times(1)).validarFinalizado(pedidoId);
        verify(alterarPedidoStatusOutputPort, times(1)).finalizado(pedidoId);
    }


    @Dado("que existe um pedido status diferente de pago com id {long}")
    public void queExisteUmPedidoStatusDiferenteDePagoComId(Long pedidoId) {
        pedido = new Pedido();
        pedido.setId(pedidoId);
        pedido.setStatusPedido(STATUS_PEDIDO_RECEBIDO);

    }

    @Quando("o usuário solicita a alteração para o status do Recebido para Pedido {long}")
    public void oUsuárioSolicitaAAlteraçãoParaOStatusDoRecebidoParaPedido(Long pedidoId) {
        alterarPedidoStatusUseCase.recebido(pedidoId);
    }

    @Entao("é exibida uma excessão informando que o pedido {long} não pode ser recebido se não estiver com status pago")
    public void éExibidaUmaExcessãoInformandoQueOPedidoNãoPodeSerRecebidoSeNãoEstiverComStatusPago(Long pedidoId) {
        verify(alterarPedidoStatusValidator).validarRecebido(pedidoId);
    }

    @Dado("que existe um pedido status diferente de recebido com id {long}")
    public void queExisteUmPedidoStatusDiferenteDeRecebidoComId(long pedidoId) {
        pedido = new Pedido();
        pedido.setId(pedidoId);
        pedido.setStatusPedido(STATUS_PEDIDO_FINALIZADO);
    }

    @Quando("o usuário solicita a alteração para o status do Pedido {long} para status EmPreparo")
    public void oUsuárioSolicitaAAlteraçãoParaOStatusDoRecebidoParaEmPreparo(long pedidoId) {
        alterarPedidoStatusUseCase.emPreparo(pedidoId);
    }



    @Entao("é exibida uma excessão informando que o pedido {long} não pode mudar para status EmPreparo porque não está com status Recebido")
    public void éExibidaUmaExcessãoInformandoQueOPedidoNãoPodeMudarParaStatusEmPreparoPorqueNãoEstáComStatusRecebido(long pedidoId) {
        verify(alterarPedidoStatusValidator).validarEmPreparo(pedidoId);
    }

    @Dado("que existe um pedido com id {long} status diferente de EmPreparo")
    public void queExisteUmPedidoComIdStatusDiferenteDeEmPreparo(long pedidoId) {

        pedido = new Pedido();
        pedido.setId(pedidoId);
        pedido.setStatusPedido(STATUS_PEDIDO_FINALIZADO);
    }

    @Quando("o usuário solicita a alteração do pedido {long} para status Pronto")
    public void oUsuárioSolicitaAAlteraçãoDoPedidoParaStatusPronto(long pedidoId) {

        alterarPedidoStatusUseCase.pronto(pedidoId);
    }


    @Entao("é exibida uma excessão informando que o pedido {long} não pode mudar para status Pronto porque está com status diferente de EmPreparo")
    public void éExibidaUmaExcessãoInformandoQueOPedidoNãoPodeMudarParaStatusProntoPorqueEstáComStatusDiferenteDeEmPreparo(long pedidoId) {
        verify(alterarPedidoStatusValidator).validarPronto(pedidoId);
    }

    @Dado("que existe um pedido com id {long} status diferente de Pronto")
    public void queExisteUmPedidoComIdStatusDiferenteDePronto(long pedidoId) {
        pedido = new Pedido();
        pedido.setId(pedidoId);
        pedido.setStatusPedido(STATUS_PEDIDO_PAGO);
    }

    @Quando("o usuário solicita a alteração do pedido {long} para status Finalizado")
    public void oUsuárioSolicitaAAlteraçãoDoPedidoParaStatusFinalizado(long pedidoId) {
        alterarPedidoStatusUseCase.finalizado(pedidoId);
    }

    @Entao("é exibida uma excessão informando que o pedido {int} não pode mudar para status Finalizado porque está com status diferente de Pronto")
    public void éExibidaUmaExcessãoInformandoQueOPedidoNãoPodeMudarParaStatusFinalizadoPorqueEstáComStatusDiferenteDePronto(long pedidoId) {
        verify(alterarPedidoStatusValidator).validarFinalizado(pedidoId);
    }
}
