package br.com.fiap.fasteats.core.usecase.pagamento;

import br.com.fiap.fasteats.core.domain.model.Pagamento;

import java.util.List;
import java.util.Optional;

public interface PagamentoInputPort {
    Optional<List<Pagamento>> listar();

    Pagamento consultarPorIdPedido(Long idPedido);

    Pagamento criar(Pagamento pagamento);

    Pagamento atualizar(Pagamento pagamento);

    Pagamento consultar(Long pagamentoId);

    Pagamento consultarPorIdPagamentoExterno(Long pagamentoExternoId);
}
