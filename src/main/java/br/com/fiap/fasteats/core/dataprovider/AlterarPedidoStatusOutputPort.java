package br.com.fiap.fasteats.core.dataprovider;

import br.com.fiap.fasteats.core.domain.model.Pedido;

import java.util.Optional;

public interface AlterarPedidoStatusOutputPort {
    Optional<Pedido> recebido(Long pedidoId);

    Optional<Pedido> emPreparo(Long pedidoId);

    Optional<Pedido> pronto(Long pedidoId);

    Optional<Pedido> finalizado(Long pedidoId);
}
