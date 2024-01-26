package br.com.fiap.fasteats.entrypoint.controller.mapper;

import br.com.fiap.fasteats.core.domain.model.CozinhaPedido;
import br.com.fiap.fasteats.entrypoint.controller.response.CozinhaPedidoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CozinhaPedidoResponseMapper {
    CozinhaPedidoResponse toCozinhaPedidoResponse(CozinhaPedido cozinhaPedido);
    List<CozinhaPedidoResponse> toCozinhaPedidoResponseList(List<CozinhaPedido> cozinhaPedidos);
}
