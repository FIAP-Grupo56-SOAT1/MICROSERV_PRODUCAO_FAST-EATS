package br.com.fiap.fasteats.dataprovider;

import br.com.fiap.fasteats.core.dataprovider.StatusPedidoOutputPort;
import br.com.fiap.fasteats.core.domain.model.StatusPedido;
import br.com.fiap.fasteats.dataprovider.client.StatusPedidoIntegration;
import br.com.fiap.fasteats.dataprovider.client.mapper.StatusPedidoMapper;
import br.com.fiap.fasteats.dataprovider.client.response.StatusPedidoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StatusPedidoAdapter implements StatusPedidoOutputPort {
    private final StatusPedidoIntegration statusPedidoIntegration;
    private final StatusPedidoMapper statusPedidoMapper;

    @Override
    public Optional<StatusPedido> consultarPorNome(String nome) {
        Optional<StatusPedidoResponse> statusPedidoEntity = statusPedidoIntegration.consultarPorNome(nome.toUpperCase());
        return statusPedidoEntity.map(statusPedidoMapper::toStatusPedido);
    }
}

