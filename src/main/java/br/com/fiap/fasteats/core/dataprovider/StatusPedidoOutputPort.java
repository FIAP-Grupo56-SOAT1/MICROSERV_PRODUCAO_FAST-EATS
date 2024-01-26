package br.com.fiap.fasteats.core.dataprovider;

import br.com.fiap.fasteats.core.domain.model.StatusPedido;

import java.util.Optional;

public interface StatusPedidoOutputPort {
    Optional<StatusPedido> consultarPorNome(String nome);
}
