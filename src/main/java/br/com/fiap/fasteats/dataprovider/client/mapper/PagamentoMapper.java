package br.com.fiap.fasteats.dataprovider.client.mapper;

import br.com.fiap.fasteats.core.domain.model.Pagamento;
import br.com.fiap.fasteats.dataprovider.client.response.PagamentoResponse;
import br.com.fiap.fasteats.dataprovider.repository.entity.PagamentoEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PagamentoMapper {

    Pagamento toPagamento(PagamentoResponse pedidoResponse);
    List<Pagamento> toPagamento(List<PagamentoResponse> pedidoResponseList);
    PagamentoEntity toPagamentoEntity(Pagamento pedido);
}
