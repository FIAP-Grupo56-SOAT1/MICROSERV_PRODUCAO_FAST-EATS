package br.com.fiap.fasteats.dataprovider;

import br.com.fiap.fasteats.dataprovider.repository.StatusPagamentoRepository;
import br.com.fiap.fasteats.dataprovider.repository.mapper.StatusPagamentoEntityMapper;
import br.com.fiap.fasteats.core.domain.model.StatusPagamento;
import br.com.fiap.fasteats.core.dataprovider.StatusPagamentoOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StatusPagamentoAdapter implements StatusPagamentoOutputPort {
    private final StatusPagamentoRepository statusPagamentoRepository;
    private final StatusPagamentoEntityMapper statusPagamentoEntityMapper;

    @Override
    public List<StatusPagamento> listar() {
        var statusPagamentoEntities = statusPagamentoRepository.findAll();
        return statusPagamentoEntities.stream()
                .map(statusPagamentoEntityMapper::toStatusPagamento)
                .toList();
    }

    @Override
    public Optional<StatusPagamento> consultarPorNome(String nome) {
        var statusPedidoEntity = statusPagamentoRepository.findByNome(nome.toUpperCase());
        var statusPedidos = statusPedidoEntity.stream()
                .map(statusPagamentoEntityMapper::toStatusPagamento)
                .toList();
        return statusPedidos.stream().findFirst();
    }

    @Override
    public Optional<StatusPagamento> consultar(Long id) {
        return statusPagamentoRepository.findById(id).map(statusPagamentoEntityMapper::toStatusPagamento);
    }
}
