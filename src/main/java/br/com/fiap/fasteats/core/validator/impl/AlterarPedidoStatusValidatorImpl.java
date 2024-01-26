package br.com.fiap.fasteats.core.validator.impl;

import br.com.fiap.fasteats.core.dataprovider.PedidoOutputPort;
import br.com.fiap.fasteats.core.domain.exception.RegraNegocioException;
import br.com.fiap.fasteats.core.validator.AlterarPedidoStatusValidator;

import static br.com.fiap.fasteats.core.constants.StatusPedidoConstants.*;

public class AlterarPedidoStatusValidatorImpl implements AlterarPedidoStatusValidator {
    private final PedidoOutputPort pedidoOutputPort;

    public AlterarPedidoStatusValidatorImpl(PedidoOutputPort pedidoOutputPort) {
        this.pedidoOutputPort = pedidoOutputPort;
    }

    @Override
    public void validarRecebido(Long pedidoId) {
        if (!recuperarStatusPedido(pedidoId).equals(STATUS_PEDIDO_PAGO))
            throw new RegraNegocioException("O Pedido só pode ser recebido se estiver com o status PAGO");
    }

    @Override
    public void validarEmPreparo(Long pedidoId) {
        if (!recuperarStatusPedido(pedidoId).equals(STATUS_PEDIDO_RECEBIDO))
            throw new RegraNegocioException("O Pedido só pode ser iniciado se estiver com o status RECEBIDO");
    }

    @Override
    public void validarPronto(Long pedidoId) {
        if (!recuperarStatusPedido(pedidoId).equals(STATUS_PEDIDO_EM_PREPARO))
            throw new RegraNegocioException("O Pedido só pode ser finalizado se estiver com o status EM_PREPARO");
    }

    @Override
    public void validarFinalizado(Long pedidoId) {
        if (!recuperarStatusPedido(pedidoId).equals(STATUS_PEDIDO_PRONTO))
            throw new RegraNegocioException("O Pedido só pode ser retirado se estiver com o status PRONTO");
    }

    private String recuperarStatusPedido(Long pedidoId) {
        return pedidoOutputPort.consultar(pedidoId).getStatusPedido();
    }
}
