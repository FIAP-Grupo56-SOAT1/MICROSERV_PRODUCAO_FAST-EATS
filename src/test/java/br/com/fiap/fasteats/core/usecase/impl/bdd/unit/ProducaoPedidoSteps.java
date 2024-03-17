package br.com.fiap.fasteats.core.usecase.impl.bdd.unit;

import br.com.fiap.fasteats.core.dataprovider.CozinhaPedidoOutputPort;
import br.com.fiap.fasteats.core.dataprovider.IniciarProcessoOutputPort;
import br.com.fiap.fasteats.core.domain.model.CozinhaPedido;
import br.com.fiap.fasteats.core.domain.model.Pedido;
import br.com.fiap.fasteats.core.usecase.AlterarPedidoStatusInputPort;
import br.com.fiap.fasteats.core.usecase.impl.ProducaoPedidoUseCase;
import io.cucumber.java.pt.Quando;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static br.com.fiap.fasteats.core.constants.ProcessoCozinhaConstants.*;
import static br.com.fiap.fasteats.core.constants.StatusPedidoConstants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProducaoPedidoSteps {

    @Mock
    private CozinhaPedidoOutputPort cozinhaPedidoOutputPort;

    @Mock
    private IniciarProcessoOutputPort iniciarProcessoOutputPort;
    @Mock
    private AlterarPedidoStatusInputPort alterarPedidoStatusInputPort;
    @InjectMocks
    private ProducaoPedidoUseCase producaoPedidoUseCase;

    private CozinhaPedido cozinhaPedido;
    private CozinhaPedido cozinhaPedidoRetornado;

    public ProducaoPedidoSteps() {
        MockitoAnnotations.openMocks(this);
    }


    @Quando("o usuário solicita receber o Pedido na Cozinha com ID {long}")
    public void usuarioSolicitaReceberPedidoNaCozinha(Long pedidoId) {
        configureMockForStatus(pedidoId, STATUS_PEDIDO_RECEBIDO);
        LocalDateTime dataHoraTeste = LocalDateTime.now();
        CozinhaPedido result = new CozinhaPedido("1234556", dataHoraTeste, dataHoraTeste,
                dataHoraTeste, dataHoraTeste, pedidoId,
                STATUS_PEDIDO_RECEBIDO, STATUS_COZINHA_RECEBIDO);

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

        when(cozinhaPedidoOutputPort.salvar(any(CozinhaPedido.class))).thenReturn(result);

        cozinhaPedidoRetornado =  producaoPedidoUseCase.retirar(pedidoId);
    }

    private void configureMockForStatus(Long pedidoId, String status) {
        Pedido pedido = new Pedido();
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

}
