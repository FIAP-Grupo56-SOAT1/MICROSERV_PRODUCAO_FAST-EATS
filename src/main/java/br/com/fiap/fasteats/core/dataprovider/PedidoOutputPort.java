package br.com.fiap.fasteats.core.dataprovider;

import br.com.fiap.fasteats.core.domain.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoOutputPort {

    Pedido salvarPedido(Pedido pedido);

    Pedido consultarPedido(Long id);

    List<Pedido> listar();

    List<Pedido> listarPedidosAndamento();

    List<Pedido> consultarPedidoAndamento(Long id);

    Pedido atualizarStatus(Pedido pedido);

    Pedido cancelaPedido(Pedido pedidoAtualizado);

    Pedido finalizarPedido(Pedido pedidoAtualizado);

    Pedido pronto(Pedido pedidoAtualizado);

    Pedido recebido(Pedido pedidoAtualizado);
}
