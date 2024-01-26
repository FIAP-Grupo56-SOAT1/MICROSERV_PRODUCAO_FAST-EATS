package br.com.fiap.fasteats.core.dataprovider;

import br.com.fiap.fasteats.core.domain.model.Pedido;

public interface PedidoOutputPort {
    Pedido consultar(Long pedidoId);
}
