package br.com.fiap.fasteats.core.usecase.impl.bdd.unit;

import br.com.fiap.fasteats.core.dataprovider.AlterarPedidoStatusOutputPort;
import br.com.fiap.fasteats.core.domain.exception.PedidoNotFound;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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

    /*@Dado("que não existe um pedido com id {long}")
    public void naoExisteUmPedidoComId(Long pedidoId) {
        when(alterarPedidoStatusOutputPort.recebido(pedidoId)).thenReturn(Optional.empty());
        when(alterarPedidoStatusOutputPort.emPreparo(pedidoId)).thenReturn(Optional.empty());
        when(alterarPedidoStatusOutputPort.pronto(pedidoId)).thenReturn(Optional.empty());
        when(alterarPedidoStatusOutputPort.finalizado(pedidoId)).thenReturn(Optional.empty());
    }

    @Quando("o usuário solicita a alteração do status do pedido {long} para Recebido")
    public void usuarioSolicitaAlteracaoParaRecebido(Long pedidoId) {
        when(alterarPedidoStatusOutputPort.recebido(pedidoId)).thenReturn(Optional.ofNullable(pedido));
    }

    @Quando("o usuário solicita a alteração do status do pedido {long} para Em Preparo")
    public void usuarioSolicitaAlteracaoParaEmPreparo(Long pedidoId) {
        when(alterarPedidoStatusOutputPort.emPreparo(pedidoId)).thenReturn(Optional.ofNullable(pedido));
    }

    @Quando("o usuário solicita a alteração do status do pedido {long} para Pronto")
    public void usuarioSolicitaAlteracaoParaPronto(Long pedidoId) {
        when(alterarPedidoStatusOutputPort.pronto(pedidoId)).thenReturn(Optional.ofNullable(pedido));
    }

    @Quando("o usuário solicita a alteração do status do pedido {long} para Finalizado")
    public void usuarioSolicitaAlteracaoParaFinalizado(Long pedidoId) {
        when(alterarPedidoStatusOutputPort.finalizado(pedidoId)).thenReturn(Optional.ofNullable(pedido));
    }

    @Entao("o status do pedido {long} é alterado para Recebido com sucesso")
    public void statusDoPedidoAlteradoParaRecebidoComSucesso(Long pedidoId) {
        Pedido resultado = alterarPedidoStatusUseCase.recebido(pedidoId);
        assertNotNull(resultado);
        assertEquals(pedidoId, resultado.getId());
        verify(alterarPedidoStatusValidator, times(1)).validarRecebido(pedidoId);
        verify(alterarPedidoStatusOutputPort, times(1)).recebido(pedidoId);
    }

    @Entao("o status do pedido {long} é alterado para Em Preparo com sucesso")
    public void statusDoPedidoAlteradoParaEmPreparoComSucesso(Long pedidoId) {
        Pedido resultado = alterarPedidoStatusUseCase.emPreparo(pedidoId);
        assertNotNull(resultado);
        assertEquals(pedidoId, resultado.getId());
        verify(alterarPedidoStatusValidator, times(1)).validarEmPreparo(pedidoId);
        verify(alterarPedidoStatusOutputPort, times(1)).emPreparo(pedidoId);
    }

    @Entao("o status do pedido {long} é alterado para Pronto com sucesso")
    public void statusDoPedidoAlteradoParaProntoComSucesso(Long pedidoId) {
        Pedido resultado = alterarPedidoStatusUseCase.pronto(pedidoId);
        assertNotNull(resultado);
        assertEquals(pedidoId, resultado.getId());
        verify(alterarPedidoStatusValidator, times(1)).validarPronto(pedidoId);
        verify(alterarPedidoStatusOutputPort, times(1)).pronto(pedidoId);
    }

    @Entao("o status do pedido {long} é alterado para Finalizado com sucesso")
    public void statusDoPedidoAlteradoParaFinalizadoComSucesso(Long pedidoId) {
        Pedido resultado = alterarPedidoStatusUseCase.finalizado(pedidoId);
        assertNotNull(resultado);
        assertEquals(pedidoId, resultado.getId());
        verify(alterarPedidoStatusValidator, times(1)).validarFinalizado(pedidoId);
        verify(alterarPedidoStatusOutputPort, times(1)).finalizado(pedidoId);
    }

    @Entao("é exibida uma excessão informando que o pedido {long} não foi encontrado")
    public void exibidaExcecaoPedidoNaoEncontrado(Long pedidoId) {
        assertThrows(PedidoNotFound.class, () -> alterarPedidoStatusUseCase.recebido(pedidoId));
        assertThrows(PedidoNotFound.class, () -> alterarPedidoStatusUseCase.emPreparo(pedidoId));
        assertThrows(PedidoNotFound.class, () -> alterarPedidoStatusUseCase.pronto(pedidoId));
        assertThrows(PedidoNotFound.class, () -> alterarPedidoStatusUseCase.finalizado(pedidoId));
    }*/
}
