package br.com.fiap.fasteats.core.usecase.impl;

import br.com.fiap.fasteats.core.dataprovider.CozinhaPedidoOutputPort;
import br.com.fiap.fasteats.core.dataprovider.IniciarProcessoOutputPort;
import br.com.fiap.fasteats.core.domain.exception.CozinhaPedidoNotFound;
import br.com.fiap.fasteats.core.domain.model.CozinhaPedido;
import br.com.fiap.fasteats.core.usecase.ProducaoPedidoInputPort;

import java.time.LocalDateTime;

import static br.com.fiap.fasteats.core.constants.ProcessoCozinhaConstants.*;
import static br.com.fiap.fasteats.core.constants.StatusPedidoConstants.*;

public class ProducaoPedidoUseCase implements ProducaoPedidoInputPort {
    private final IniciarProcessoOutputPort iniciarProcessoOutputPort;
    private final CozinhaPedidoOutputPort cozinhaPedidoOutputPort;

    public ProducaoPedidoUseCase(IniciarProcessoOutputPort iniciarProcessoOutputPort,
                                 CozinhaPedidoOutputPort cozinhaPedidoOutputPort) {
        this.iniciarProcessoOutputPort = iniciarProcessoOutputPort;
        this.cozinhaPedidoOutputPort = cozinhaPedidoOutputPort;
    }

    @Override
    public CozinhaPedido pendente(Long pedidoId) {
        CozinhaPedido cozinhaPedido = cozinhaPedidoOutputPort.consultarPorIdPedido(pedidoId).orElse(new CozinhaPedido());
        cozinhaPedido.setIdPedido(pedidoId);
        cozinhaPedido.setStatusPedido(STATUS_PEDIDO_PAGO);
        cozinhaPedido.setProcessoAtual(STATUS_COZINHA_PENDENTE);
        cozinhaPedido.setDataRecebimentoDoPedido(null);
        return iniciarProcessoOutputPort.pendente(cozinhaPedido);
    }

    @Override
    public CozinhaPedido receber(Long pedidoId) {
        CozinhaPedido cozinhaPedido = consultarPorIdPedido(pedidoId);
        cozinhaPedido.setIdPedido(pedidoId);
        cozinhaPedido.setStatusPedido(STATUS_PEDIDO_RECEBIDO);
        cozinhaPedido.setProcessoAtual(STATUS_COZINHA_RECEBIDO);
        cozinhaPedido.setDataRecebimentoDoPedido(LocalDateTime.now());
        return iniciarProcessoOutputPort.receber(cozinhaPedido);
    }

    @Override
    public CozinhaPedido iniciarPreparo(Long pedidoId) {
        CozinhaPedido cozinhaPedido = consultarPorIdPedido(pedidoId);
        cozinhaPedido.setStatusPedido(STATUS_PEDIDO_EM_PREPARO);
        cozinhaPedido.setProcessoAtual(STATUS_COZINHA_INICIO_PREPARO);
        cozinhaPedido.setDataInicioPreparo(LocalDateTime.now());
        return iniciarProcessoOutputPort.iniciarPreparo(cozinhaPedido);
    }

    @Override
    public CozinhaPedido finalizarPreparo(Long pedidoId) {
        CozinhaPedido cozinhaPedido = consultarPorIdPedido(pedidoId);
        cozinhaPedido.setStatusPedido(STATUS_PEDIDO_PRONTO);
        cozinhaPedido.setProcessoAtual(STATUS_COZINHA_FINALIZANDO_PREPARO);
        cozinhaPedido.setDataFinalizacaoPreparo(LocalDateTime.now());
        return iniciarProcessoOutputPort.finalizarPreparo(cozinhaPedido);
    }

    @Override
    public CozinhaPedido retirar(Long pedidoId) {
        CozinhaPedido cozinhaPedido = consultarPorIdPedido(pedidoId);
        cozinhaPedido.setStatusPedido(STATUS_PEDIDO_FINALIZADO);
        cozinhaPedido.setProcessoAtual(STATUS_COZINHA_ENTREGAR_PEDIDO);
        cozinhaPedido.setDataEntregaPedido(LocalDateTime.now());
        return iniciarProcessoOutputPort.entregarPedido(cozinhaPedido);
    }

    private CozinhaPedido consultarPorIdPedido(Long pedidoId) {
        return cozinhaPedidoOutputPort.consultarPorIdPedido(pedidoId)
                .orElseThrow(() -> cozinhaPedidoNaoEncontradoPorPedidoId(pedidoId));
    }

    private RuntimeException cozinhaPedidoNaoEncontradoPorPedidoId(Long pedidoId) {
        return new CozinhaPedidoNotFound("Cozinha Pedido para o pedido " + pedidoId + " não encontrado");
    }
}
