package br.com.fiap.fasteats.core.usecase.impl.pagamento;

import br.com.fiap.fasteats.core.domain.model.Pagamento;
import br.com.fiap.fasteats.core.usecase.pagamento.FormaPagamentoInputPort;
import br.com.fiap.fasteats.core.usecase.pagamento.AlterarFormaPagamentoInputPort;
import br.com.fiap.fasteats.core.usecase.pagamento.GerarPagamentoInputPort;
import br.com.fiap.fasteats.core.usecase.pagamento.PagamentoInputPort;

public class AlterarFormaPagamentoUseCase implements AlterarFormaPagamentoInputPort {

    private final FormaPagamentoInputPort formaPagamentoInputPort;
    private final GerarPagamentoInputPort gerarPagamentoInputPort;

    public AlterarFormaPagamentoUseCase(FormaPagamentoInputPort formaPagamentoInputPort, GerarPagamentoInputPort gerarPagamentoInputPort) {

        this.formaPagamentoInputPort = formaPagamentoInputPort;
        this.gerarPagamentoInputPort = gerarPagamentoInputPort;
    }


    @Override
    public Pagamento alterarFormaPagamento(Long pagamentoId, Long formaPagamentoId) {
        return null;
    }
}
