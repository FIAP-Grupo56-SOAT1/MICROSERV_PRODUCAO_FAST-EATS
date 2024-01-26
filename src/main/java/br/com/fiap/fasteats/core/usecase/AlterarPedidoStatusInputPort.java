package br.com.fiap.fasteats.core.usecase;

import br.com.fiap.fasteats.core.domain.model.Pedido;

public interface AlterarPedidoStatusInputPort {
    Pedido recebido(Long pedidoId);

    Pedido emPreparo(Long pedidoId);

    Pedido pronto(Long pedidoId);

    Pedido finalizado(Long pedidoId);
}
