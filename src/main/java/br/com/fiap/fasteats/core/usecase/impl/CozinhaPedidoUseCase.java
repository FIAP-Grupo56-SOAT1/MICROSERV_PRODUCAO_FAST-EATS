package br.com.fiap.fasteats.core.usecase.impl;

import br.com.fiap.fasteats.core.dataprovider.CozinhaPedidoOutputPort;
import br.com.fiap.fasteats.core.domain.exception.CozinhaPedidoNotFound;
import br.com.fiap.fasteats.core.domain.model.CozinhaPedido;
import br.com.fiap.fasteats.core.usecase.CozinhaPedidoInputPort;

import java.util.List;


public class CozinhaPedidoUseCase implements CozinhaPedidoInputPort {
    private final CozinhaPedidoOutputPort cozinhaPedidoOutputPort;

    public CozinhaPedidoUseCase(CozinhaPedidoOutputPort cozinhaPedidoOutputPort) {
        this.cozinhaPedidoOutputPort = cozinhaPedidoOutputPort;
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

    private RuntimeException cozinhaPedidoNaoEncontrado(String cozinhaPedidoId) {
        return new CozinhaPedidoNotFound("Cozinha Pedido " + cozinhaPedidoId + " não encontrado");
    }

    private RuntimeException cozinhaPedidoNaoEncontradoPorPedidoId(Long pedidoId) {
        return new CozinhaPedidoNotFound("Cozinha Pedido para o pedido " + pedidoId + " não encontrado");
    }
}
