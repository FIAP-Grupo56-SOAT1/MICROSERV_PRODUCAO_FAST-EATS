package br.com.fiap.fasteats.dataprovider.client;


import br.com.fiap.fasteats.dataprovider.client.response.StatusPagamentoResponse;

import java.util.List;
import java.util.Optional;

public interface StatusPagamentoIntegration {
    Optional<StatusPagamentoResponse> consultar(Long id);
    Optional<List<StatusPagamentoResponse>> listar();
    Optional<StatusPagamentoResponse> consultarPorNome(String nome);
}
