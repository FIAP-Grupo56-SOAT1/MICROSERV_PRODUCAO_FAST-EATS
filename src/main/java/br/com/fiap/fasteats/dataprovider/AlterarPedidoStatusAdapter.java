package br.com.fiap.fasteats.dataprovider;

import br.com.fiap.fasteats.core.dataprovider.AlterarPedidoStatusOutputPort;
import br.com.fiap.fasteats.core.domain.exception.StatusPedidoNotFound;
import br.com.fiap.fasteats.core.domain.model.Pedido;
import br.com.fiap.fasteats.core.domain.model.StatusPedido;
import br.com.fiap.fasteats.dataprovider.client.PedidoIntegration;
import br.com.fiap.fasteats.dataprovider.client.StatusPedidoIntegration;
import br.com.fiap.fasteats.dataprovider.client.mapper.PedidoMapper;
import br.com.fiap.fasteats.dataprovider.client.mapper.StatusPedidoMapper;
import br.com.fiap.fasteats.dataprovider.client.response.StatusPedidoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static br.com.fiap.fasteats.core.constants.StatusPedidoConstants.*;

@Component
@RequiredArgsConstructor
public class AlterarPedidoStatusAdapter implements AlterarPedidoStatusOutputPort {
    private final PedidoIntegration pedidoIntegration;
    private final StatusPedidoIntegration statusPedidoIntegration;
    private final StatusPedidoMapper statusPedidoMapper;
    private final PedidoMapper pedidoMapper;

    @Override
    public Optional<Pedido> recebido(Long pedidoId) {
        Long idStatus = consultarStatusId(STATUS_PEDIDO_RECEBIDO);
        pedidoIntegration.atualizarStatus(pedidoId, idStatus);
        return pedidoIntegration.consultar(pedidoId).map(pedidoMapper::toPedido);
    }

    @Override
    public Optional<Pedido> emPreparo(Long pedidoId) {
        Long idStatus = consultarStatusId(STATUS_PEDIDO_EM_PREPARO);
        pedidoIntegration.atualizarStatus(pedidoId, idStatus);
        return pedidoIntegration.consultar(pedidoId).map(pedidoMapper::toPedido);
    }

    @Override
    public Optional<Pedido> pronto(Long pedidoId) {
        Long idStatus = consultarStatusId(STATUS_PEDIDO_PRONTO);
        pedidoIntegration.atualizarStatus(pedidoId, idStatus);
        return pedidoIntegration.consultar(pedidoId).map(pedidoMapper::toPedido);
    }

    @Override
    public Optional<Pedido> finalizado(Long pedidoId) {
        Long idStatus = consultarStatusId(STATUS_PEDIDO_FINALIZADO);
        pedidoIntegration.atualizarStatus(pedidoId, idStatus);
        return pedidoIntegration.consultar(pedidoId).map(pedidoMapper::toPedido);
    }

    private Long consultarStatusId(String status) {
        StatusPedidoResponse statusPedidoResponse = statusPedidoIntegration.consultarPorNome(status)
                .orElseThrow(() -> new StatusPedidoNotFound("Status Pedido não encontrado"));
        StatusPedido statusPedido = statusPedidoMapper.toStatusPedido(statusPedidoResponse);
        return statusPedido.getId();
    }
}
