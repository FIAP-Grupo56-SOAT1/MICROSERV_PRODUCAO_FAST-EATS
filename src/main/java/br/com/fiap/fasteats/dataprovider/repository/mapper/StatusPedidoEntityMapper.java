package br.com.fiap.fasteats.dataprovider.repository.mapper;

import br.com.fiap.fasteats.dataprovider.client.response.StatusPedidoResponse;
import br.com.fiap.fasteats.dataprovider.repository.entity.StatusPedidoEntity;
import br.com.fiap.fasteats.core.domain.model.StatusPedido;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatusPedidoEntityMapper {
    StatusPedido toStatusPedido(StatusPedidoResponse statusPedidoResponse);
    List<StatusPedido> toStatusPedidoList(List<StatusPedidoResponse> statusPedidoResponseList);
}
