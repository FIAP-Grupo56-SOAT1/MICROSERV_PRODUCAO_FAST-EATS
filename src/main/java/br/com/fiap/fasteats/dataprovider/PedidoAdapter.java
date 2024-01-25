package br.com.fiap.fasteats.dataprovider;

import br.com.fiap.fasteats.core.dataprovider.PedidoOutputPort;
import br.com.fiap.fasteats.core.domain.model.Pedido;
import br.com.fiap.fasteats.dataprovider.client.PedidoIntegration;
import br.com.fiap.fasteats.dataprovider.client.mapper.PedidoResponseMapper;
import br.com.fiap.fasteats.dataprovider.client.response.PedidoResponse;
import br.com.fiap.fasteats.dataprovider.repository.entity.PedidoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PedidoAdapter implements PedidoOutputPort {

    private final PedidoResponseMapper pedidoMapper;
    private final PedidoIntegration pedidoIntegration;


    @Override
    public Pedido salvarPedido(Pedido pedido) {
        PedidoEntity pedidoEntity = pedidoMapper.toPedidoEntity(pedido);
        PedidoResponse pedidoResponse = pedidoIntegration.saveAndFlush(pedidoEntity);
        return pedidoMapper.toPedido(pedidoResponse);
    }

    @Override
    public Pedido consultarPedido(Long id) {
        PedidoResponse pedidoEntity = pedidoIntegration.findById(id);
        if (pedidoEntity == null) {
            return null;
        }
        return  pedidoMapper.toPedido(pedidoEntity);
    }

    @Override
    public List<Pedido> listar() {
        List<PedidoResponse> pedidosEntity = pedidoIntegration.findAll();
        return pedidosEntity.stream().map(pedidoMapper::toPedido).toList();
    }

    @Override
    public List<Pedido> consultarPedidoAndamento(Long id) {
        List<PedidoResponse> pedidosEntity = pedidoIntegration.consultarPedidoAndamento(id);
        return pedidosEntity.stream().map(pedidoMapper::toPedido).toList();
    }

    @Override
    public Pedido atualizarStatus(Pedido pedido) {
        pedidoIntegration.atualizarStatus(pedido.getId(),pedido.getIdStatusPedido());
        return pedido;
    }

    @Override
    public Pedido cancelaPedido(Pedido pedidoAtualizado) {
        pedidoIntegration.cancelaPedido(pedidoAtualizado.getId());
        return pedidoAtualizado;
    }

    @Override
    public Pedido finalizarPedido(Pedido pedidoAtualizado) {
        pedidoIntegration.finalizarPedido(pedidoAtualizado.getId());
        return pedidoAtualizado;
    }

    @Override
    public Pedido pronto(Pedido pedidoAtualizado) {
        pedidoIntegration.pronto(pedidoAtualizado.getId());
        return pedidoAtualizado;
    }

    @Override
    public Pedido recebido(Pedido pedidoAtualizado) {
        pedidoIntegration.recebido(pedidoAtualizado.getId());
        return pedidoAtualizado;
    }

    @Override
    public List<Pedido> listarPedidosAndamento() {
        List<PedidoResponse> pedidosEntity = pedidoIntegration.listarPedidosAndamento();
        return pedidosEntity.stream().map(pedidoMapper::toPedido).toList();
    }


}
