package br.com.fiap.fasteats.core.usecase.impl;

import br.com.fiap.fasteats.core.dataprovider.AlterarPedidoStatusOutputPort;
import br.com.fiap.fasteats.core.usecase.AlterarPedidoStatusInputPort;
import br.com.fiap.fasteats.core.validator.AlterarPedidoStatusValidator;

public class AlterarPedidoStatusUseCase implements AlterarPedidoStatusInputPort {
    private final AlterarPedidoStatusOutputPort alterarPedidoStatusOutputPort;
    private final AlterarPedidoStatusValidator alterarPedidoStatusValidator;

    public AlterarPedidoStatusUseCase(AlterarPedidoStatusOutputPort alterarPedidoStatusOutputPort,
                                      AlterarPedidoStatusValidator alterarPedidoStatusValidator) {
        this.alterarPedidoStatusOutputPort = alterarPedidoStatusOutputPort;
        this.alterarPedidoStatusValidator = alterarPedidoStatusValidator;
    }

    @Override
    public void recebido(Long pedidoId) {
        alterarPedidoStatusValidator.validarRecebido(pedidoId);
        alterarPedidoStatusOutputPort.recebido(pedidoId);
    }

    @Override
    public void emPreparo(Long pedidoId) {
        alterarPedidoStatusValidator.validarEmPreparo(pedidoId);
        alterarPedidoStatusOutputPort.emPreparo(pedidoId);
    }

    @Override
    public void pronto(Long pedidoId) {
        alterarPedidoStatusValidator.validarPronto(pedidoId);
        alterarPedidoStatusOutputPort.pronto(pedidoId);
    }

    @Override
    public void finalizado(Long pedidoId) {
        alterarPedidoStatusValidator.validarFinalizado(pedidoId);
        alterarPedidoStatusOutputPort.finalizado(pedidoId);
    }
}
