package br.com.fiap.fasteats.core.usecase.impl;

import br.com.fiap.fasteats.core.dataprovider.CozinhaPedidoOutputPort;
import br.com.fiap.fasteats.core.domain.exception.CozinhaPedidoNotFound;
import br.com.fiap.fasteats.core.domain.model.CozinhaPedido;
import br.com.fiap.fasteats.core.domain.model.Pedido;
import br.com.fiap.fasteats.core.usecase.AlterarPedidoStatusInputPort;
import br.com.fiap.fasteats.core.usecase.CozinhaPedidoInputPort;

import java.time.LocalDateTime;
import java.util.List;

import static br.com.fiap.fasteats.core.constants.ProcessoCozinhaConstants.*;


public class CozinhaPedidoUseCase implements CozinhaPedidoInputPort {
    private final CozinhaPedidoOutputPort cozinhaPedidoOutputPort;
    private final AlterarPedidoStatusInputPort alterarPedidoStatusInputPort;

    public CozinhaPedidoUseCase(CozinhaPedidoOutputPort cozinhaPedidoOutputPort,
                                AlterarPedidoStatusInputPort alterarPedidoStatusInputPort) {
        this.cozinhaPedidoOutputPort = cozinhaPedidoOutputPort;
        this.alterarPedidoStatusInputPort = alterarPedidoStatusInputPort;
    }

    @Override
    public List<CozinhaPedido> listar() {
        return cozinhaPedidoOutputPort.listar();
    }

    @Override
    public CozinhaPedido consultar(String cozinhaPedidoId) {
        return cozinhaPedidoOutputPort.consultar(cozinhaPedidoId)
                .orElseThrow(() -> cozinhaPedidoNaoEncontrado(cozinhaPedidoId));
    }

    @Override
    public CozinhaPedido consultarPorIdPedido(Long pedidoId) {
        return cozinhaPedidoOutputPort.consultarPorIdPedido(pedidoId)
                .orElseThrow(() -> cozinhaPedidoNaoEncontradoPorPedidoId(pedidoId));
    }

    @Override
    public CozinhaPedido receber(Long pedidoId) {
        Pedido pedido = alterarPedidoStatusInputPort.recebido(pedidoId);

        CozinhaPedido cozinhaPedido = new CozinhaPedido();
        cozinhaPedido.setIdPedido(pedidoId);
        cozinhaPedido.setStatusPedido(pedido.getStatusPedido());
        cozinhaPedido.setProcessoAtual(RECEBIDO);
        cozinhaPedido.setDataRecebimentoDoPedido(pedido.getDataHoraRecebimento());

        return cozinhaPedidoOutputPort.salvar(cozinhaPedido);
    }

    @Override
    public CozinhaPedido iniciarPreparo(Long pedidoId) {
        Pedido pedido = alterarPedidoStatusInputPort.emPreparo(pedidoId);

        CozinhaPedido cozinhaPedido = consultarPorIdPedido(pedidoId);
        cozinhaPedido.setStatusPedido(pedido.getStatusPedido());
        cozinhaPedido.setProcessoAtual(INICIO_PREPARO);
        cozinhaPedido.setDataInicioPreparo(LocalDateTime.now());

        return cozinhaPedidoOutputPort.salvar(cozinhaPedido);
    }

    @Override
    public CozinhaPedido finalizarPreparo(Long pedidoId) {
        Pedido pedido = alterarPedidoStatusInputPort.pronto(pedidoId);

        CozinhaPedido cozinhaPedido = consultarPorIdPedido(pedidoId);
        cozinhaPedido.setStatusPedido(pedido.getStatusPedido());
        cozinhaPedido.setProcessoAtual(FINALIZANDO_PREPARO);
        cozinhaPedido.setDataFinalizacaoPreparo(pedido.getDataHoraFinalizado());

        return cozinhaPedidoOutputPort.salvar(cozinhaPedido);
    }

    @Override
    public CozinhaPedido retirar(Long pedidoId) {
        Pedido pedido = alterarPedidoStatusInputPort.finalizado(pedidoId);

        CozinhaPedido cozinhaPedido = consultarPorIdPedido(pedidoId);
        cozinhaPedido.setStatusPedido(pedido.getStatusPedido());
        cozinhaPedido.setProcessoAtual(ENTREGAR_PEDIDO);
        cozinhaPedido.setDataEntregaPedido(pedido.getDataHoraFinalizado());

        return cozinhaPedidoOutputPort.salvar(cozinhaPedido);
    }

    private CozinhaPedidoNotFound cozinhaPedidoNaoEncontrado(String cozinhaPedidoId) {
        return new CozinhaPedidoNotFound("Cozinha Pedido " + cozinhaPedidoId + " não encontrado");
    }

    private CozinhaPedidoNotFound cozinhaPedidoNaoEncontradoPorPedidoId(Long pedidoId) {
        return new CozinhaPedidoNotFound("Cozinha Pedido para o pedido " + pedidoId + " não encontrado");
    }
}
