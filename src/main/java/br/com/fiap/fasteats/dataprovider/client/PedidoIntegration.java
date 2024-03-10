package br.com.fiap.fasteats.dataprovider.client;

import br.com.fiap.fasteats.dataprovider.client.response.PedidoResponse;

import java.util.Optional;

public interface PedidoIntegration {
    Optional<PedidoResponse> consultar(Long pedidoId);

    void pedidoRecebido(Long pedidoId);

    void pedidoEmPreparo(Long pedidoId);

    void pedidoPronto(Long pedidoId);

    void pedidoFinalizado(Long pedidoId);
}
