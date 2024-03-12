package br.com.fiap.fasteats.core.usecase;

import br.com.fiap.fasteats.core.domain.model.CozinhaPedido;

import java.util.List;

public interface CozinhaPedidoInputPort {
    List<CozinhaPedido> listar();

    CozinhaPedido consultar(String cozinhaPedidoId);

    CozinhaPedido consultarPorIdPedido(Long pedidoId);
}
