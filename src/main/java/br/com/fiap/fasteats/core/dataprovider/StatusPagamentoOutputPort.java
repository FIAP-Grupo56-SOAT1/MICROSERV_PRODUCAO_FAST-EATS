package br.com.fiap.fasteats.core.dataprovider;

import br.com.fiap.fasteats.core.domain.model.StatusPagamento;
import br.com.fiap.fasteats.core.domain.model.StatusPedido;

import java.util.List;
import java.util.Optional;

public interface StatusPagamentoOutputPort {
    Optional<StatusPagamento> consultar(Long id);
    Optional<List<StatusPagamento>> listar();

    Optional<StatusPagamento> consultarPorNome(String nome);
}
