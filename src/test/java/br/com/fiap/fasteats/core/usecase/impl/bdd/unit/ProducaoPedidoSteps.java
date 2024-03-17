package br.com.fiap.fasteats.core.usecase.impl.bdd.unit;

import br.com.fiap.fasteats.core.dataprovider.CozinhaPedidoOutputPort;
import br.com.fiap.fasteats.core.dataprovider.IniciarProcessoOutputPort;
import br.com.fiap.fasteats.core.domain.model.CozinhaPedido;
import br.com.fiap.fasteats.core.domain.model.Pedido;
import br.com.fiap.fasteats.core.usecase.impl.ProducaoPedidoUseCase;
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

import static br.com.fiap.fasteats.core.constants.ProcessoCozinhaConstants.*;
import static br.com.fiap.fasteats.core.constants.StatusPedidoConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class ProducaoPedidoSteps {

    @Mock
    private CozinhaPedidoOutputPort cozinhaPedidoOutputPort;
    @Mock
    private IniciarProcessoOutputPort iniciarProcessoOutputPort;
    @InjectMocks
    private ProducaoPedidoUseCase producaoPedidoUseCase;


    private CozinhaPedido cozinhaPedido;
    private Pedido pedido ;
    private CozinhaPedido cozinhaPedidoRetornado;

    AutoCloseable openMocks;

    @Before
    public void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        openMocks.close();
    }


    @Quando("o usuário solicita receber o Pedido na Cozinha com ID {long}")
    public void usuarioSolicitaReceberPedidoNaCozinha(Long pedidoId) {
        configureMockForStatus(pedidoId, STATUS_PEDIDO_RECEBIDO);

        LocalDateTime dataHoraTeste = LocalDateTime.now();

        CozinhaPedido result = new CozinhaPedido("1234556", dataHoraTeste, dataHoraTeste,
                dataHoraTeste, dataHoraTeste, pedidoId,
                STATUS_PEDIDO_RECEBIDO, STATUS_COZINHA_RECEBIDO);

        when(cozinhaPedidoOutputPort.consultarPorIdPedido(anyLong())).thenReturn(Optional.of(cozinhaPedido));
        when(iniciarProcessoOutputPort.receber(cozinhaPedido)).thenReturn(cozinhaPedido);
        when(cozinhaPedidoOutputPort.salvar(any(CozinhaPedido.class))).thenReturn(result);

        cozinhaPedidoRetornado = producaoPedidoUseCase.receber(pedidoId);
    }

    @Quando("o usuário solicita iniciar o preparo na Cozinha com ID {long}")
    public void usuarioSolicitaIniciarPreparoNaCozinha(Long pedidoId) {
        configureMockForStatus(pedidoId, STATUS_PEDIDO_EM_PREPARO);

        LocalDateTime dataHoraTeste = LocalDateTime.now();

        CozinhaPedido result = new CozinhaPedido("1234556", dataHoraTeste, dataHoraTeste,
                dataHoraTeste, dataHoraTeste, pedidoId,
                STATUS_PEDIDO_EM_PREPARO, STATUS_COZINHA_INICIO_PREPARO);

        when(cozinhaPedidoOutputPort.consultarPorIdPedido(anyLong())).thenReturn(Optional.of(cozinhaPedido));
        when(iniciarProcessoOutputPort.iniciarPreparo(cozinhaPedido)).thenReturn(cozinhaPedido);
        when(cozinhaPedidoOutputPort.salvar(any(CozinhaPedido.class))).thenReturn(result);

        cozinhaPedidoRetornado = producaoPedidoUseCase.iniciarPreparo(pedidoId);
    }

    @Quando("o usuário solicita finalizar o preparo na Cozinha com ID {long}")
    public void usuarioSolicitaFinalizarPreparoNaCozinha(Long pedidoId) {
        configureMockForStatus(pedidoId, STATUS_PEDIDO_PRONTO);
        LocalDateTime dataHoraTeste = LocalDateTime.now();
        CozinhaPedido result = new CozinhaPedido("1234556", dataHoraTeste, dataHoraTeste,
                dataHoraTeste, dataHoraTeste, pedidoId,
                STATUS_PEDIDO_PRONTO, STATUS_COZINHA_FINALIZANDO_PREPARO);

        when(cozinhaPedidoOutputPort.consultarPorIdPedido(anyLong())).thenReturn(Optional.of(cozinhaPedido));
        when(iniciarProcessoOutputPort.finalizarPreparo(cozinhaPedido)).thenReturn(cozinhaPedido);
        when(cozinhaPedidoOutputPort.salvar(any(CozinhaPedido.class))).thenReturn(result);

        cozinhaPedidoRetornado = producaoPedidoUseCase.finalizarPreparo(pedidoId);
    }

    @Quando("o usuário solicita retirar o Pedido na Cozinha com ID {long}")
    public void usuarioSolicitaRetirarPedidoNaCozinha(Long pedidoId) {
        configureMockForStatus(pedidoId, STATUS_PEDIDO_FINALIZADO);
        LocalDateTime dataHoraTeste = LocalDateTime.now();
        CozinhaPedido result = new CozinhaPedido("1234556", dataHoraTeste, dataHoraTeste,
                dataHoraTeste, dataHoraTeste, pedidoId,
                STATUS_PEDIDO_FINALIZADO, STATUS_COZINHA_ENTREGAR_PEDIDO);

        when(cozinhaPedidoOutputPort.consultarPorIdPedido(anyLong())).thenReturn(Optional.of(cozinhaPedido));
        when(iniciarProcessoOutputPort.entregarPedido(cozinhaPedido)).thenReturn(cozinhaPedido);
        when(cozinhaPedidoOutputPort.salvar(any(CozinhaPedido.class))).thenReturn(result);

        cozinhaPedidoRetornado =  producaoPedidoUseCase.retirar(pedidoId);
    }

    private void configureMockForStatus(Long pedidoId, String status) {
        pedido = new Pedido();
        pedido.setId(pedidoId);
        pedido.setStatusPedido(status);
        pedido.setDataHoraCriado(LocalDateTime.now());
        pedido.setDataHoraRecebimento(LocalDateTime.now());
        pedido.setDataHoraFinalizado(LocalDateTime.now());


        cozinhaPedido = new CozinhaPedido();
        cozinhaPedido.setIdPedido(pedidoId);
        cozinhaPedido.setDataRecebimentoDoPedido(LocalDateTime.now());
        cozinhaPedido.setDataInicioPreparo(LocalDateTime.now());
        cozinhaPedido.setDataFinalizacaoPreparo(LocalDateTime.now());
        cozinhaPedido.setDataEntregaPedido(LocalDateTime.now());
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

    @Dado("existe um Cozinha Pedido associado ao Pedido com ID {long}")
    public void existeUmCozinhaPedidoAssociadoAoPedidoComId(Long pedidoId) {
        configureMockForStatus(pedidoId, "Recebido");
        when(cozinhaPedidoOutputPort.consultarPorIdPedido(pedidoId)).thenReturn(Optional.of(cozinhaPedido));
    }

    @Dado("que existe um Pedido com ID {long}")
    public void existeUmPedidoComId(Long pedidoId) {
        configureMockForStatus(pedidoId, "Recebido");
    }


}
