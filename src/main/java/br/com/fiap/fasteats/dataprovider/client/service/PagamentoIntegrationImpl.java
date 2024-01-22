package br.com.fiap.fasteats.dataprovider.client.service;

import br.com.fiap.fasteats.dataprovider.client.PagamentoIntegration;
import br.com.fiap.fasteats.dataprovider.client.response.PagamentoResponse;
import br.com.fiap.fasteats.dataprovider.repository.entity.PagamentoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PagamentoIntegrationImpl implements PagamentoIntegration {

    @Override
    public Optional<List<PagamentoResponse>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<PagamentoResponse> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public PagamentoResponse saveAndFlush(PagamentoEntity pagamentoEntity) {
        return null;
    }

    @Override
    public Optional<PagamentoResponse> findPagamentoByIdPagamentoExterno(Long idPagamentoExterno) {
        return Optional.empty();
    }

    @Override
    public PagamentoResponse save(PagamentoEntity pagamentoEntity) {
        return null;
    }

    @Override
    public Optional<PagamentoResponse> findFirstByPedidoIdOrderByDataHoraCriadoDesc(long pedidoId) {
        return Optional.empty();
    }
}
