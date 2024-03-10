package br.com.fiap.fasteats.core.usecase;

import br.com.fiap.fasteats.core.domain.model.CozinhaPedido;

public interface ProducaoPedidoInputPort {
    CozinhaPedido pendente(Long pedidoId);
    CozinhaPedido receber(Long pedidoId);

    CozinhaPedido iniciarPreparo(Long pedidoId);

    CozinhaPedido finalizarPreparo(Long pedidoId);

    CozinhaPedido retirar(Long pedidoId);
}
