package br.com.fiap.fasteats.dataprovider.client.mapper;

import br.com.fiap.fasteats.core.domain.model.Pedido;
import br.com.fiap.fasteats.dataprovider.client.response.PedidoResponse;
import br.com.fiap.fasteats.dataprovider.repository.entity.PedidoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoResponseMapper {
    Pedido toPedido(PedidoResponse pedidoResponse);
    PedidoEntity toPedidoEntity(Pedido pedido);
}
