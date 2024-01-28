package br.com.fiap.fasteats.core.usecase.impl.bdd.unit;

import br.com.fiap.fasteats.core.dataprovider.CozinhaPedidoOutputPort;
import br.com.fiap.fasteats.core.domain.model.CozinhaPedido;
import br.com.fiap.fasteats.core.domain.model.Pedido;
import br.com.fiap.fasteats.core.usecase.AlterarPedidoStatusInputPort;
import br.com.fiap.fasteats.core.usecase.impl.CozinhaPedidoUseCase;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static br.com.fiap.fasteats.core.constants.ProcessoCozinhaConstants.*;
import static br.com.fiap.fasteats.core.constants.StatusPedidoConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

public class CozinhaPedidoSteps {

    @Mock
    private CozinhaPedidoOutputPort cozinhaPedidoOutputPort;
    @Mock
    private AlterarPedidoStatusInputPort alterarPedidoStatusInputPort;
    @InjectMocks
    private CozinhaPedidoUseCase cozinhaPedidoUseCase;
    private CozinhaPedido cozinhaPedido;
    private CozinhaPedido cozinhaPedidoRetornado;

    public CozinhaPedidoSteps() {
        MockitoAnnotations.openMocks(this);
    }

    @Dado("que existe um Cozinha Pedido com ID {string}")
    public void existeUmCozinhaPedidoComId(String cozinhaPedidoId) {
        cozinhaPedido = new CozinhaPedido();
        cozinhaPedido.setCozinhaId(cozinhaPedidoId);

        when(cozinhaPedidoOutputPort.consultar(cozinhaPedidoId)).thenReturn(Optional.of(cozinhaPedido));
    }

    @Dado("que existe um Pedido com ID {long}")
    public void existeUmPedidoComId(Long pedidoId) {
        configureMockForStatus(pedidoId, "Recebido");
    }

    @Dado("existe um Cozinha Pedido associado ao Pedido com ID {long}")
    public void existeUmCozinhaPedidoAssociadoAoPedidoComId(Long pedidoId) {
        configureMockForStatus(pedidoId, "Recebido");
        when(cozinhaPedidoOutputPort.consultarPorIdPedido(pedidoId)).thenReturn(Optional.of(cozinhaPedido));
    }

    @Quando("o usuário consulta o Cozinha Pedido por ID {string}")
    public void usuarioConsultaCozinhaPedidoPorId(String cozinhaPedidoId) {
        cozinhaPedidoRetornado = cozinhaPedidoUseCase.consultar(cozinhaPedidoId);
    }

    @Quando("o usuário consulta o Cozinha Pedido por ID de Pedido {long}")
    public void usuarioConsultaCozinhaPedidoPorIdPedido(Long pedidoId) {
        try {
            CozinhaPedido result = cozinhaPedidoUseCase.consultarPorIdPedido(pedidoId);
            assertNotNull(result);
            assertEquals(cozinhaPedido.getIdPedido(), result.getIdPedido());
        } catch (Exception e) {
            fail("Erro ao consultar Cozinha Pedido por ID de Pedido");
        }
    }

    @Quando("o usuário solicita receber o Pedido na Cozinha com ID {long}")
    public void usuarioSolicitaReceberPedidoNaCozinha(Long pedidoId) {
        configureMockForStatus(pedidoId, STATUS_PEDIDO_RECEBIDO);
        LocalDateTime dataHoraTeste = LocalDateTime.now();
        CozinhaPedido result = new CozinhaPedido("1234556", dataHoraTeste, dataHoraTeste,
                dataHoraTeste, dataHoraTeste, pedidoId,
                STATUS_PEDIDO_RECEBIDO, STATUS_COZINHA_RECEBIDO);

        when(cozinhaPedidoOutputPort.salvar(any(CozinhaPedido.class))).thenReturn(result);

        cozinhaPedidoRetornado = cozinhaPedidoUseCase.receber(pedidoId);
    }

    @Quando("o usuário solicita iniciar o preparo na Cozinha com ID {long}")
    public void usuarioSolicitaIniciarPreparoNaCozinha(Long pedidoId) {
        configureMockForStatus(pedidoId, STATUS_PEDIDO_EM_PREPARO);
        LocalDateTime dataHoraTeste = LocalDateTime.now();
        CozinhaPedido result = new CozinhaPedido("1234556", dataHoraTeste, dataHoraTeste,
                dataHoraTeste, dataHoraTeste, pedidoId,
                STATUS_PEDIDO_EM_PREPARO, STATUS_COZINHA_INICIO_PREPARO);

        when(cozinhaPedidoOutputPort.salvar(any(CozinhaPedido.class))).thenReturn(result);

        cozinhaPedidoRetornado = cozinhaPedidoUseCase.iniciarPreparo(pedidoId);
    }

    @Quando("o usuário solicita finalizar o preparo na Cozinha com ID {long}")
    public void usuarioSolicitaFinalizarPreparoNaCozinha(Long pedidoId) {
        configureMockForStatus(pedidoId, STATUS_PEDIDO_PRONTO);
        LocalDateTime dataHoraTeste = LocalDateTime.now();
        CozinhaPedido result = new CozinhaPedido("1234556", dataHoraTeste, dataHoraTeste,
                dataHoraTeste, dataHoraTeste, pedidoId,
                STATUS_PEDIDO_PRONTO, STATUS_COZINHA_FINALIZANDO_PREPARO);

        when(cozinhaPedidoOutputPort.salvar(any(CozinhaPedido.class))).thenReturn(result);

        cozinhaPedidoRetornado = cozinhaPedidoUseCase.finalizarPreparo(pedidoId);
    }

    @Quando("o usuário solicita retirar o Pedido na Cozinha com ID {long}")
    public void usuarioSolicitaRetirarPedidoNaCozinha(Long pedidoId) {
        configureMockForStatus(pedidoId, STATUS_PEDIDO_FINALIZADO);
        LocalDateTime dataHoraTeste = LocalDateTime.now();
        CozinhaPedido result = new CozinhaPedido("1234556", dataHoraTeste, dataHoraTeste,
                dataHoraTeste, dataHoraTeste, pedidoId,
                STATUS_PEDIDO_FINALIZADO, STATUS_COZINHA_ENTREGAR_PEDIDO);

        when(cozinhaPedidoOutputPort.salvar(any(CozinhaPedido.class))).thenReturn(result);

        cozinhaPedidoRetornado =  cozinhaPedidoUseCase.retirar(pedidoId);
    }

    @Entao("é retornado o Cozinha Pedido com ID {string}")
    public void retornadoCozinhaPedidoComId(String cozinhaPedidoId) {
        assertNotNull(cozinhaPedidoRetornado);
        assertEquals(cozinhaPedidoId, cozinhaPedido.getCozinhaId());
        assertEquals(cozinhaPedidoId, cozinhaPedidoRetornado.getCozinhaId());
    }

    @Entao("é retornado o Cozinha Pedido associado ao Pedido com ID {long}")
    public void retornadoCozinhaPedidoAssociadoAoPedidoComId(Long pedidoId) {
        assertNotNull(cozinhaPedido.getIdPedido());
        assertEquals(pedidoId, cozinhaPedido.getIdPedido());
    }

    @Entao("é retornado o Cozinha Pedido atualizado com o status {string}")
    public void retornadoCozinhaPedidoAtualizadoComStatus(String status) {
        assertNotNull(cozinhaPedidoRetornado);
        assertEquals(status, cozinhaPedidoRetornado.getProcessoAtual());
    }

    @Entao("o status do pedido foi atualizado para {string}")
    public void statusPedidoAtualizadoPara(String status) {
        assertNotNull(cozinhaPedidoRetornado);
        assertEquals(status, cozinhaPedidoRetornado.getStatusPedido());
    }

    private void configureMockForStatus(Long pedidoId, String status) {
        Pedido pedido = new Pedido();
        pedido.setId(pedidoId);
        pedido.setStatusPedido(status);
        pedido.setDataHoraCriado(LocalDateTime.now());
        pedido.setDataHoraRecebimento(LocalDateTime.now());
        pedido.setDataHoraFinalizado(LocalDateTime.now());

        when(alterarPedidoStatusInputPort.recebido(anyLong())).thenReturn(pedido);
        when(alterarPedidoStatusInputPort.emPreparo(anyLong())).thenReturn(pedido);
        when(alterarPedidoStatusInputPort.pronto(anyLong())).thenReturn(pedido);
        when(alterarPedidoStatusInputPort.finalizado(anyLong())).thenReturn(pedido);

        cozinhaPedido = new CozinhaPedido();
        cozinhaPedido.setIdPedido(pedidoId);
        cozinhaPedido.setDataRecebimentoDoPedido(LocalDateTime.now());
        cozinhaPedido.setDataInicioPreparo(LocalDateTime.now());
        cozinhaPedido.setDataFinalizacaoPreparo(LocalDateTime.now());
        cozinhaPedido.setDataEntregaPedido(LocalDateTime.now());
    }
}
