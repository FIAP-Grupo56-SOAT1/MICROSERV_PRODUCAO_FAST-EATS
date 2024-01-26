package br.com.fiap.fasteats.dataprovider.repository.mapper;

import br.com.fiap.fasteats.core.domain.model.CozinhaPedido;
import br.com.fiap.fasteats.dataprovider.repository.entity.CozinhaPedidoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CozinhaPedidoEntityMapper {
    CozinhaPedidoEntity toCozinhaPedidoEntity(CozinhaPedido cozinha);
    CozinhaPedido toCozinhaPedido(CozinhaPedidoEntity cozinhaEntity);
}
