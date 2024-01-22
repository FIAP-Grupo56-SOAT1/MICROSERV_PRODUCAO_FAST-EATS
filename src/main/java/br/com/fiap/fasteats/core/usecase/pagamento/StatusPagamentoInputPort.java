package br.com.fiap.fasteats.core.usecase.pagamento;

import br.com.fiap.fasteats.core.domain.model.StatusPagamento;

import java.util.List;
import java.util.Optional;

public interface StatusPagamentoInputPort {
    Optional<List<StatusPagamento>> listar();

    StatusPagamento consultarPorNome(String nome);

    StatusPagamento consultar(Long id);
}
