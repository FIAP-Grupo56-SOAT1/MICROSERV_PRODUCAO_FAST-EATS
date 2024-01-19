package br.com.fiap.fasteats.core.usecase.impl.pagamento;

import br.com.fiap.fasteats.core.domain.exception.FormaPagamentoNotFound;
import br.com.fiap.fasteats.core.domain.model.FormaPagamento;
import br.com.fiap.fasteats.core.usecase.pagamento.FormaPagamentoInputPort;
import br.com.fiap.fasteats.core.dataprovider.FormaPagamentoOutputPort;

import java.util.List;

public class FormaPagamentoUseCase implements FormaPagamentoInputPort {
    FormaPagamentoOutputPort formaPagamentoOutputPort;

    public FormaPagamentoUseCase(FormaPagamentoOutputPort formaPagamentoOutputPort) {
        this.formaPagamentoOutputPort = formaPagamentoOutputPort;
    }

    @Override
    public FormaPagamento criar(FormaPagamento formaPagamento) {
        formaPagamento.setNome(formaPagamento.getNome().toUpperCase());
        formaPagamento.setAtivo(true);
        return formaPagamentoOutputPort.criar(formaPagamento);
    }

    @Override
    public FormaPagamento consultar(Long id) {
        return formaPagamentoOutputPort.consultar(id).orElseThrow(() -> new FormaPagamentoNotFound("Forma de pagamento não encontrada"));
    }

    @Override
    public FormaPagamento atualizar(FormaPagamento formaPagamento) {
        if (formaPagamento.getAtivo() == null) formaPagamento.setAtivo(true);
        formaPagamento.setNome(formaPagamento.getNome().toUpperCase());
        return formaPagamentoOutputPort.atualizar(formaPagamento);
    }

    @Override
    public void deletar(Long id) {
        formaPagamentoOutputPort.deletar(id);
    }

    @Override
    public List<FormaPagamento> listar() {
        return formaPagamentoOutputPort.listar().orElseThrow(() -> new FormaPagamentoNotFound("Nenhuma forma de pagamento encontrada"));
    }

    @Override
    public FormaPagamento consultarPorNome(String nome) {
        return formaPagamentoOutputPort.consultarPorNome(nome).orElseThrow(() -> new FormaPagamentoNotFound("Forma de pagamento não encontrada"));
    }
}
