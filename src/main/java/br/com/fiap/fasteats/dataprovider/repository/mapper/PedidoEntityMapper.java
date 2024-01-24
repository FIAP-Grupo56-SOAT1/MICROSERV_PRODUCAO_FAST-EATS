package br.com.fiap.fasteats.dataprovider.repository.mapper;

import br.com.fiap.fasteats.core.domain.model.Pedido;
import br.com.fiap.fasteats.dataprovider.repository.entity.PedidoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoEntityMapper {
    PedidoEntity toPedidoEntity(Pedido pedido);
    Pedido toPedido(PedidoEntity pedidoEntity);
    List<Pedido> toPedidos(List<PedidoEntity> pedidosEntity);
}
