package br.com.fiap.fasteats.dataprovider.client.mapper;

import br.com.fiap.fasteats.core.domain.model.StatusPedido;
import br.com.fiap.fasteats.dataprovider.client.response.StatusPedidoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatusPedidoMapper {
    StatusPedido toStatusPedido(StatusPedidoResponse statusPedidoResponse);
}
