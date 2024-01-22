package br.com.fiap.fasteats.core.usecase.pedido;

import br.com.fiap.fasteats.core.domain.model.StatusPedido;

import java.util.List;

public interface StatusPedidoInputPort {
    List<StatusPedido> listar();
    StatusPedido consultarPorNome(String nome);

    StatusPedido consultar(Long id);
}
