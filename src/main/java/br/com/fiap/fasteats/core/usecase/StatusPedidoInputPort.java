package br.com.fiap.fasteats.core.usecase;

import br.com.fiap.fasteats.core.domain.model.StatusPedido;

public interface StatusPedidoInputPort {
    StatusPedido consultarPorNome(String nome);
}
