package br.com.fiap.fasteats.dataprovider.client;

import br.com.fiap.fasteats.dataprovider.client.response.PedidoResponse;

import java.util.Optional;

public interface PedidoIntegration {
    Optional<PedidoResponse> consultar(Long pedidoId);
    void atualizarStatus(Long pedidoId, Long idStatus);
}
