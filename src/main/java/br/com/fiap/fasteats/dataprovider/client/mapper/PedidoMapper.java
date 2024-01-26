package br.com.fiap.fasteats.dataprovider.client.mapper;

import br.com.fiap.fasteats.core.domain.model.Pedido;
import br.com.fiap.fasteats.dataprovider.client.response.PedidoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    Pedido toPedido(PedidoResponse pedidoResponse);
}
