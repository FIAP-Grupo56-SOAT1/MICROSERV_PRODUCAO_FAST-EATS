package br.com.fiap.fasteats.core.usecase.pedido;

import br.com.fiap.fasteats.core.domain.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface AndamentoPedidoInputPort {
    Pedido consultarAndamentoPedido(Long idPedido);

    List<Pedido> consultarPedidosEmAndamento();
}
