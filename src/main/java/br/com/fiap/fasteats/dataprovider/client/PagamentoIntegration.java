package br.com.fiap.fasteats.dataprovider.client;

import br.com.fiap.fasteats.dataprovider.client.response.PagamentoResponse;
import br.com.fiap.fasteats.dataprovider.repository.entity.PagamentoEntity;

import java.util.List;
import java.util.Optional;

public interface PagamentoIntegration {
    Optional<List<PagamentoResponse>> findAll();
    Optional<PagamentoResponse> findById(Long id);
    PagamentoResponse saveAndFlush(PagamentoEntity pagamentoEntity);
    Optional<PagamentoResponse> findPagamentoByIdPagamentoExterno(Long idPagamentoExterno);
    PagamentoResponse save(PagamentoEntity pagamentoEntity);
    Optional<PagamentoResponse> findFirstByPedidoIdOrderByDataHoraCriadoDesc(long pedidoId);
}
