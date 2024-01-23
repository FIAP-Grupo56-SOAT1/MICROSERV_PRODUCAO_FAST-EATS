package br.com.fiap.fasteats.dataprovider.client.mapper;

import br.com.fiap.fasteats.core.domain.model.StatusPagamento;
import br.com.fiap.fasteats.dataprovider.client.response.StatusPagamentoResponse;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface StatusPagamentoResponseMapper {
    StatusPagamento toStatusPedido(StatusPagamentoResponse statusPedidoResponse);
    List<StatusPagamento> toStatusPagamentoList(List<StatusPagamentoResponse> statusPedidoResponseList);
}
