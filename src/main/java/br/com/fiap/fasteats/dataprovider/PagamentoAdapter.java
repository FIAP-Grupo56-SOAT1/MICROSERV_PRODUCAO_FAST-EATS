package br.com.fiap.fasteats.dataprovider;

import br.com.fiap.fasteats.core.dataprovider.PagamentoOutputPort;
import br.com.fiap.fasteats.core.domain.model.Pagamento;
import br.com.fiap.fasteats.dataprovider.client.PagamentoIntegration;
import br.com.fiap.fasteats.dataprovider.client.mapper.PagamentoMapper;
import br.com.fiap.fasteats.dataprovider.client.response.PagamentoResponse;
import br.com.fiap.fasteats.dataprovider.repository.entity.PagamentoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PagamentoAdapter implements PagamentoOutputPort {
    private final PagamentoMapper pagamentoMapper;
    private final PagamentoIntegration pagamentoIntegration;

    @Override
    public Optional<List<Pagamento>> listar() {

        Optional<List<PagamentoResponse>> pagamentoEntity = pagamentoIntegration.findAll();
        return pagamentoEntity.map(pagamentoMapper::toPagamento);
    }

    @Override
    public Optional<Pagamento> consultar(Long id) {

        Optional<PagamentoResponse> pagamentoEntity = pagamentoIntegration.findById(id);

        if (pagamentoEntity.isEmpty()) {
            return Optional.empty();
        }

        Pagamento pagamento = pagamentoMapper.toPagamento(pagamentoEntity.get());
        return Optional.of(pagamento);

    }

    @Override
    public Optional<Pagamento> consultarPorPedidoId(long pedidoId) {
        Optional<PagamentoResponse> pagamentoEntity = pagamentoIntegration.findFirstByPedidoIdOrderByDataHoraCriadoDesc(pedidoId);
        return pagamentoEntity.map(pagamentoMapper::toPagamento);
    }

    @Override
    public Pagamento salvarPagamento(Pagamento pagamento) {

        PagamentoEntity pagamentoEntity = pagamentoMapper.toPagamentoEntity(pagamento);
        PagamentoResponse pagamentoResponse = pagamentoIntegration.save(pagamentoEntity);
        return pagamentoMapper.toPagamento(pagamentoResponse);

    }

    @Override
    public Pagamento atualizarPagamento(Pagamento pagamento) {
        PagamentoEntity pagamentoEntity = pagamentoMapper.toPagamentoEntity(pagamento);
        PagamentoResponse pagamentoResponse = pagamentoIntegration.saveAndFlush(pagamentoEntity);
        return pagamentoMapper.toPagamento(pagamentoResponse);
    }

    @Override
    public Optional<Pagamento> consultarPorIdPagamentoExterno(Long idPagamentoExterno) {
        return pagamentoIntegration.findPagamentoByIdPagamentoExterno(idPagamentoExterno).map(pagamentoMapper::toPagamento);
    }

}
