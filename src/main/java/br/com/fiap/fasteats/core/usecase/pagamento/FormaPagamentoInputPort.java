package br.com.fiap.fasteats.core.usecase.pagamento;

import br.com.fiap.fasteats.core.domain.model.FormaPagamento;

import java.util.List;

public interface FormaPagamentoInputPort {
    FormaPagamento consultar(Long id);
    List<FormaPagamento> listar();
    FormaPagamento consultarPorNome(String nome);
}