package br.com.fiap.fasteats.dataprovider.client;

import br.com.fiap.fasteats.dataprovider.client.response.StatusPedidoResponse;

import java.util.Optional;

public interface StatusPedidoIntegration {
    Optional<StatusPedidoResponse> consultarPorNome(String nome);
}
