package br.com.fiap.fasteats.entrypoint.controller.mapper;

import br.com.fiap.fasteats.entrypoint.controller.response.StatusPedidoResponse;
import br.com.fiap.fasteats.core.domain.model.StatusPedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatusPedidoMapper {

    StatusPedidoResponse toStatusPedidoResponse(StatusPedido statusPedido);
    List<StatusPedidoResponse> toStatusPedidoResponseList(List<StatusPedido> statusPedidoList);
}
