package br.com.fiap.fasteats.core.usecase.pedido;

import br.com.fiap.fasteats.core.domain.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoInputPort {
    Pedido consultar(Long id);

    List<Pedido> listar();

    Pedido atualizar(Pedido pedido);

    void atualizarValorPedido(Pedido pedido);
}