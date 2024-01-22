package br.com.fiap.fasteats.dataprovider;

import br.com.fiap.fasteats.core.dataprovider.PedidoOutputPort;
import br.com.fiap.fasteats.core.domain.model.Pedido;
import br.com.fiap.fasteats.dataprovider.client.PedidoIntegration;
import br.com.fiap.fasteats.dataprovider.client.mapper.PedidoMapper;
import br.com.fiap.fasteats.dataprovider.client.response.PedidoResponse;
import br.com.fiap.fasteats.dataprovider.repository.entity.PedidoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PedidoAdapter implements PedidoOutputPort {

    private final PedidoMapper pedidoEntityMapper;
    private final PedidoIntegration pedidoIntegration;


    @Override
    public Pedido salvarPedido(Pedido pedido) {
        PedidoEntity pedidoEntity = pedidoEntityMapper.toPedidoEntity(pedido);
        PedidoResponse pedidoResponse = pedidoIntegration.saveAndFlush(pedidoEntity);
        return pedidoEntityMapper.toPedido(pedidoResponse);
    }

    @Override
    public Pedido consultarPedido(Long id) {
        PedidoResponse pedidoEntity = pedidoIntegration.findById(id);
        if (pedidoEntity == null) {
            return null;
        }
        return  pedidoEntityMapper.toPedido(pedidoEntity);
    }

    @Override
    public List<Pedido> listar() {
        List<PedidoResponse> pedidosEntity = pedidoIntegration.findAll();
        return pedidosEntity.stream().map(pedidoEntityMapper::toPedido).toList();
    }

    @Override
    public List<Pedido> consultarPedidoAndamento(Long id) {
        List<PedidoResponse> pedidosEntity = pedidoIntegration.consultarPedidoAndamento(id);
        return pedidosEntity.stream().map(pedidoEntityMapper::toPedido).toList();
    }

    @Override
    public List<Pedido> listarPedidosAndamento() {
        List<PedidoResponse> pedidosEntity = pedidoIntegration.listarPedidosAndamento();
        return pedidosEntity.stream().map(pedidoEntityMapper::toPedido).toList();
    }


}
