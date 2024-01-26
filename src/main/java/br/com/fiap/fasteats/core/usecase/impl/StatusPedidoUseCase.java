package br.com.fiap.fasteats.core.usecase.impl;

import br.com.fiap.fasteats.core.dataprovider.StatusPedidoOutputPort;
import br.com.fiap.fasteats.core.domain.exception.StatusPedidoNotFound;
import br.com.fiap.fasteats.core.domain.model.StatusPedido;
import br.com.fiap.fasteats.core.usecase.StatusPedidoInputPort;

public class StatusPedidoUseCase implements StatusPedidoInputPort {
    private final StatusPedidoOutputPort statusPedidoOutputPort;

    public StatusPedidoUseCase(StatusPedidoOutputPort statusPedidoOutputPort) {
        this.statusPedidoOutputPort = statusPedidoOutputPort;
    }

    @Override
    public StatusPedido consultarPorNome(String nome) {
        return statusPedidoOutputPort.consultarPorNome(nome)
                .orElseThrow(() -> new StatusPedidoNotFound("StatusPedido com nome:" + nome + " não encontrado"));
    }
}
