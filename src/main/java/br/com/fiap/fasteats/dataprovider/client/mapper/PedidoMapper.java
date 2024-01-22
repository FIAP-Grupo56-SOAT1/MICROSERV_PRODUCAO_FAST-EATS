package br.com.fiap.fasteats.dataprovider.client.mapper;

import br.com.fiap.fasteats.core.domain.model.Pedido;
import br.com.fiap.fasteats.dataprovider.client.response.PedidoResponse;
import br.com.fiap.fasteats.dataprovider.repository.entity.PedidoEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    Pedido toPedido(PedidoResponse pedidoResponse);
    List<Pedido> toPedido(List<PedidoResponse> pedidoResponseList);

    PedidoEntity toPedidoEntity(Pedido pedido);
}
