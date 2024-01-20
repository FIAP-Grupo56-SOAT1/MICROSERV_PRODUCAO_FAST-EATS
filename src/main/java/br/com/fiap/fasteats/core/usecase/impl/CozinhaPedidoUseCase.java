package br.com.fiap.fasteats.core.usecase.impl;

import br.com.fiap.fasteats.core.domain.model.Pedido;
import br.com.fiap.fasteats.core.usecase.pedido.AlterarPedidoStatusInputPort;
import br.com.fiap.fasteats.core.usecase.pedido.CozinhaPedidoInputPort;


public class CozinhaPedidoUseCase implements CozinhaPedidoInputPort {
    private final AlterarPedidoStatusInputPort alterarPedidoStatusInputPort;

    public CozinhaPedidoUseCase(AlterarPedidoStatusInputPort alterarPedidoStatusInputPort) {
        this.alterarPedidoStatusInputPort = alterarPedidoStatusInputPort;
    }

    @Override
    public Pedido receberPedido(Long pedidoId) {
        return alterarPedidoStatusInputPort.recebido(pedidoId);
    }

    @Override
    public Pedido iniciarPreparoPedido(Long pedidoId) {
        return alterarPedidoStatusInputPort.emPreparo(pedidoId);
    }

    @Override
    public Pedido finalizarPreparoPedido(Long pedidoId) {
        return alterarPedidoStatusInputPort.pronto(pedidoId);
    }

    @Override
    public Pedido retirarPedido(Long pedidoId) {
        return alterarPedidoStatusInputPort.finalizado(pedidoId);
    }
}
