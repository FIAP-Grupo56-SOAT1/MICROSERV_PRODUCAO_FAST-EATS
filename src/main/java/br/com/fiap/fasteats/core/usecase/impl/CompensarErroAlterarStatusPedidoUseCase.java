package br.com.fiap.fasteats.core.usecase.impl;

import br.com.fiap.fasteats.core.dataprovider.CozinhaPedidoOutputPort;
import br.com.fiap.fasteats.core.domain.exception.CozinhaPedidoNotFound;
import br.com.fiap.fasteats.core.domain.model.CozinhaPedido;
import br.com.fiap.fasteats.core.usecase.CompensarErroAlterarStatusPedidoInputPort;

import java.time.LocalDateTime;

import static br.com.fiap.fasteats.core.constants.ProcessoCozinhaConstants.*;
import static br.com.fiap.fasteats.core.constants.StatusPedidoConstants.*;

public class CompensarErroAlterarStatusPedidoUseCase implements CompensarErroAlterarStatusPedidoInputPort {
    private final CozinhaPedidoOutputPort cozinhaPedidoOutputPort;

    public CompensarErroAlterarStatusPedidoUseCase(CozinhaPedidoOutputPort cozinhaPedidoOutputPort) {
        this.cozinhaPedidoOutputPort = cozinhaPedidoOutputPort;
    }

    @Override
    public void pendente(Long pedidoId) {
        CozinhaPedido cozinhaPedido = consultarPorIdPedido(pedidoId);
        cozinhaPedido.setIdPedido(pedidoId);
        cozinhaPedido.setStatusPedido(STATUS_PEDIDO_PAGO);
        cozinhaPedido.setProcessoAtual(STATUS_COZINHA_PENDENTE);
        cozinhaPedido.setDataRecebimentoDoPedido(null);
        cozinhaPedidoOutputPort.salvar(cozinhaPedido);
    }

    @Override
    public void recebido(Long pedidoId) {
        CozinhaPedido cozinhaPedido = consultarPorIdPedido(pedidoId);
        cozinhaPedido.setIdPedido(pedidoId);
        cozinhaPedido.setStatusPedido(STATUS_PEDIDO_RECEBIDO);
        cozinhaPedido.setProcessoAtual(STATUS_COZINHA_RECEBIDO);
        cozinhaPedido.setDataRecebimentoDoPedido(LocalDateTime.now());
        cozinhaPedido.setDataInicioPreparo(null);
        cozinhaPedido.setDataFinalizacaoPreparo(null);
        cozinhaPedido.setDataEntregaPedido(null);
        cozinhaPedidoOutputPort.salvar(cozinhaPedido);
    }

    @Override
    public void emPreparo(Long pedidoId) {
        CozinhaPedido cozinhaPedido = consultarPorIdPedido(pedidoId);
        cozinhaPedido.setStatusPedido(STATUS_PEDIDO_EM_PREPARO);
        cozinhaPedido.setProcessoAtual(STATUS_COZINHA_INICIO_PREPARO);
        cozinhaPedido.setDataInicioPreparo(LocalDateTime.now());
        cozinhaPedido.setDataFinalizacaoPreparo(null);
        cozinhaPedido.setDataEntregaPedido(null);
        cozinhaPedidoOutputPort.salvar(cozinhaPedido);
    }

    @Override
    public void pronto(Long pedidoId) {
        CozinhaPedido cozinhaPedido = consultarPorIdPedido(pedidoId);
        cozinhaPedido.setStatusPedido(STATUS_PEDIDO_PRONTO);
        cozinhaPedido.setProcessoAtual(STATUS_COZINHA_FINALIZANDO_PREPARO);
        cozinhaPedido.setDataFinalizacaoPreparo(LocalDateTime.now());
        cozinhaPedido.setDataEntregaPedido(null);
        cozinhaPedidoOutputPort.salvar(cozinhaPedido);
    }

    public CozinhaPedido consultarPorIdPedido(Long pedidoId) {
        return cozinhaPedidoOutputPort.consultarPorIdPedido(pedidoId)
                .orElseThrow(() -> cozinhaPedidoNaoEncontradoPorPedidoId(pedidoId));
    }

    private RuntimeException cozinhaPedidoNaoEncontradoPorPedidoId(Long pedidoId) {
        return new CozinhaPedidoNotFound("Cozinha Pedido para o pedido " + pedidoId + " não encontrado");
    }
}
