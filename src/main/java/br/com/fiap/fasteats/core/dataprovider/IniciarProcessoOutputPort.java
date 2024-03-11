package br.com.fiap.fasteats.core.dataprovider;

import br.com.fiap.fasteats.core.domain.model.CozinhaPedido;

public interface IniciarProcessoOutputPort {
    CozinhaPedido pendente(CozinhaPedido cozinhaPedido);

    CozinhaPedido receber(CozinhaPedido cozinhaPedido);

    CozinhaPedido iniciarPreparo(CozinhaPedido cozinhaPedido);

    CozinhaPedido finalizarPreparo(CozinhaPedido cozinhaPedido);

    CozinhaPedido entregarPedido(CozinhaPedido cozinhaPedido);
}
