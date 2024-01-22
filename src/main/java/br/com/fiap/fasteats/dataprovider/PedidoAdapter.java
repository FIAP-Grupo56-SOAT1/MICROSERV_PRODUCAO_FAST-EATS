package br.com.fiap.fasteats.dataprovider;

import br.com.fiap.fasteats.dataprovider.repository.entity.PedidoEntity;
import br.com.fiap.fasteats.dataprovider.repository.mapper.PedidoEntityMapper;
import br.com.fiap.fasteats.core.domain.model.Pedido;
import br.com.fiap.fasteats.core.dataprovider.PedidoOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PedidoAdapter implements PedidoOutputPort {

    private final PedidoEntityMapper pedidoEntityMapper;

    @Override
    public Pedido salvarPedido(Pedido pedido) {
        return null;
    }

    @Override
    public Optional<Pedido> consultarPedido(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Pedido> listar() {
        return null;
    }

    @Override
    public List<Pedido> listarPedidosAndamento() {
        return null;
    }

    @Override
    public List<Pedido> consultarPedidoAndamento(Long id) {
        return null;
    }


//    @Override
//    public Optional<Pedido> consultarPedido(Long id) {
//        Optional<PedidoEntity> pedidoEntity = pedidoRepository.findById(id);
//
//        if (pedidoEntity.isEmpty()) {
//            return Optional.empty();
//        }
//
//        Pedido pedido = pedidoEntityMapper.toPedido(pedidoEntity.get());
//        return Optional.of(pedido);
//    }
//
//    @Override
//    public List<Pedido> listar() {
//        List<PedidoEntity> pedidosEntity = pedidoRepository.findAll();
//        return pedidoEntityMapper.toPedidos(pedidosEntity);
//    }
//
//    @Override
//    public List<Pedido> consultarPedidoAndamento(Long id) {
//        List<PedidoEntity> pedidosEntity = pedidoRepository.consultarPedidoAndamento(id);
//        return pedidoEntityMapper.toPedidos(pedidosEntity);
//    }
//
//    @Override
//    public List<Pedido> listarPedidosAndamento() {
//        List<PedidoEntity> pedidosEntity = pedidoRepository.listarPedidosAndamento();
//        return pedidoEntityMapper.toPedidos(pedidosEntity);
//    }


}
