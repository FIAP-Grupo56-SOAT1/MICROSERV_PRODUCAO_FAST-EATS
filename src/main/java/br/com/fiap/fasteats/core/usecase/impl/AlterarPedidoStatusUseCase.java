package br.com.fiap.fasteats.core.usecase.impl;

import br.com.fiap.fasteats.core.dataprovider.AlterarPedidoStatusOutputPort;
import br.com.fiap.fasteats.core.domain.exception.PedidoNotFound;
import br.com.fiap.fasteats.core.domain.model.Pedido;
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
    public Pedido recebido(Long pedidoId) {
        alterarPedidoStatusValidator.validarRecebido(pedidoId);
        return alterarPedidoStatusOutputPort.recebido(pedidoId)
                .orElseThrow(() -> pedidoNaoEncontrado(pedidoId));
    }

    @Override
    public Pedido emPreparo(Long pedidoId) {
        alterarPedidoStatusValidator.validarEmPreparo(pedidoId);
        return alterarPedidoStatusOutputPort.emPreparo(pedidoId)
                .orElseThrow(() -> pedidoNaoEncontrado(pedidoId));
    }

    @Override
    public Pedido pronto(Long pedidoId) {
        alterarPedidoStatusValidator.validarPronto(pedidoId);
        return alterarPedidoStatusOutputPort.pronto(pedidoId)
                .orElseThrow(() -> pedidoNaoEncontrado(pedidoId));
    }

    @Override
    public Pedido finalizado(Long pedidoId) {
        alterarPedidoStatusValidator.validarFinalizado(pedidoId);
        return alterarPedidoStatusOutputPort.finalizado(pedidoId)
                .orElseThrow(() -> pedidoNaoEncontrado(pedidoId));
    }

    private PedidoNotFound pedidoNaoEncontrado(Long pedidoId) {
        throw new PedidoNotFound("Pedido não encontrado id " + pedidoId);
    }
}
