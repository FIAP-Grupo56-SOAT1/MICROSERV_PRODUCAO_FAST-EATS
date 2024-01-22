package br.com.fiap.fasteats.dataprovider;

import br.com.fiap.fasteats.core.dataprovider.StatusPagamentoOutputPort;
import br.com.fiap.fasteats.core.domain.exception.StatusPedidoNotFound;
import br.com.fiap.fasteats.core.domain.model.StatusPagamento;
import br.com.fiap.fasteats.dataprovider.client.StatusPagamentoIntegration;
import br.com.fiap.fasteats.dataprovider.client.mapper.StatusPagamentoMapper;
import br.com.fiap.fasteats.dataprovider.client.response.StatusPagamentoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StatusPagamentoAdapter implements StatusPagamentoOutputPort {
    private final StatusPagamentoIntegration statusPagamentoIntegration;
    private final StatusPagamentoMapper statusPagamentoMapper;

    @Override
    public Optional<StatusPagamento> consultar(Long id) {
        return statusPagamentoIntegration.consultar(id).map(statusPagamentoMapper::toStatusPedido);
    }

    @Override
    public Optional<List<StatusPagamento>> listar() {
        var statusPedidoEntities = statusPagamentoIntegration.listar();
        return statusPedidoEntities.map(statusPagamentoMapper::toStatusPagamentoList);
    }

    @Override
    public Optional<StatusPagamento> consultarPorNome(String nome) {
        var statusPedidoEntity = statusPagamentoIntegration.consultarPorNome(nome.toUpperCase());
        return statusPedidoEntity.map(statusPagamentoMapper::toStatusPedido);
    }

    private Long consultarStatusId(String status) {
        StatusPagamentoResponse statusPedidoResponse = statusPagamentoIntegration.consultarPorNome(status)
                .orElseThrow(() -> new StatusPedidoNotFound("Status Pagamento não encontrado"));
        StatusPagamento statusPagamento = statusPagamentoMapper.toStatusPedido(statusPedidoResponse);
        return statusPagamento.getId();
    }
}
