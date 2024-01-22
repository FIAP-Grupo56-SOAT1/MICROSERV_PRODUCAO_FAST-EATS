package br.com.fiap.fasteats.core.usecase.pedido;

import br.com.fiap.fasteats.core.domain.model.Pedido;

public interface CozinhaPedidoInputPort {
    Pedido receberPedido(Long pedidoId);

    Pedido iniciarPreparoPedido(Long pedidoId);

    Pedido finalizarPreparoPedido(Long pedidoId);

    Pedido retirarPedido(Long pedidoId);
}
