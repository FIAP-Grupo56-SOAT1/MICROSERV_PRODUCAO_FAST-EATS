package br.com.fiap.fasteats.dataprovider;

import br.com.fiap.fasteats.core.dataprovider.PedidoOutputPort;
import br.com.fiap.fasteats.core.domain.exception.PedidoNotFound;
import br.com.fiap.fasteats.core.domain.model.Pedido;
import br.com.fiap.fasteats.dataprovider.client.PedidoIntegration;
import br.com.fiap.fasteats.dataprovider.client.mapper.PedidoMapper;
import br.com.fiap.fasteats.dataprovider.client.response.PedidoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PedidoAdapter implements PedidoOutputPort {
    private final PedidoIntegration pedidoIntegration;
    private final PedidoMapper pedidoMapper;

    @Override
    public Pedido consultar(Long pedidoId) {
        PedidoResponse pedidoResponse = pedidoIntegration.consultar(pedidoId)
                .orElseThrow(() -> new PedidoNotFound("Pedido id:" + pedidoId + " não encontrado"));
        return pedidoMapper.toPedido(pedidoResponse);
    }
}
