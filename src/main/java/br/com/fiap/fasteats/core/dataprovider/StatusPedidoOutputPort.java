package br.com.fiap.fasteats.core.dataprovider;

import br.com.fiap.fasteats.core.domain.model.StatusPedido;

import java.util.List;
import java.util.Optional;

public interface StatusPedidoOutputPort {
    Optional<StatusPedido> consultar(Long id);
    Optional<List<StatusPedido>> listar();
    Optional<StatusPedido> consultarPorNome(String nome);
}
