package br.com.fiap.fasteats.dataprovider;

import br.com.fiap.fasteats.core.dataprovider.StatusPedidoOutputPort;
import br.com.fiap.fasteats.core.domain.exception.StatusPedidoNotFound;
import br.com.fiap.fasteats.core.domain.model.StatusPedido;
import br.com.fiap.fasteats.dataprovider.client.StatusPedidoIntegration;
import br.com.fiap.fasteats.dataprovider.client.mapper.StatusPedidoMapper;
import br.com.fiap.fasteats.dataprovider.client.response.StatusPedidoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StatusPedidoAdapter implements StatusPedidoOutputPort {
    private final StatusPedidoIntegration statusPedidoIntegration;
    private final StatusPedidoMapper statusPedidoMapper;


    @Override
    public Optional<StatusPedido> consultar(Long id) {
        return statusPedidoIntegration.consultar(id).map(statusPedidoMapper::toStatusPedido);
    }

    @Override
    public Optional<List<StatusPedido>> listar() {
        var statusPedidoEntities = statusPedidoIntegration.listar();
        return statusPedidoEntities.map(statusPedidoMapper::toStatusPedidoList);
    }

    @Override
    public Optional<StatusPedido> consultarPorNome(String nome) {
        var statusPedidoEntity = statusPedidoIntegration.consultarPorNome(nome.toUpperCase());
        return statusPedidoEntity.map(statusPedidoMapper::toStatusPedido);
    }

    private Long consultarStatusId(String status) {
        StatusPedidoResponse statusPedidoResponse = statusPedidoIntegration.consultarPorNome(status)
                .orElseThrow(() -> new StatusPedidoNotFound("Status Pedido não encontrado"));
        StatusPedido statusPedido = statusPedidoMapper.toStatusPedido(statusPedidoResponse);
        return statusPedido.getId();
    }
}

