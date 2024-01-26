package br.com.fiap.fasteats.core.dataprovider;

import br.com.fiap.fasteats.core.domain.model.CozinhaPedido;

import java.util.List;
import java.util.Optional;

public interface CozinhaPedidoOutputPort {
    CozinhaPedido salvar(CozinhaPedido cozinhaPedidoId);

    List<CozinhaPedido> listar();

    Optional<CozinhaPedido> consultar(String cozinhaId);

    Optional<CozinhaPedido> consultarPorIdPedido(Long pedidoId);
}
