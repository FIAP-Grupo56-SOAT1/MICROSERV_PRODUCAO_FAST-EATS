package br.com.fiap.fasteats.dataprovider;

import br.com.fiap.fasteats.core.dataprovider.CozinhaPedidoOutputPort;
import br.com.fiap.fasteats.core.dataprovider.IniciarProcessoOutputPort;
import br.com.fiap.fasteats.core.domain.model.CozinhaPedido;
import br.com.fiap.fasteats.core.usecase.AlterarPedidoStatusInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class IniciarProcessoAdapter implements IniciarProcessoOutputPort {
    private final CozinhaPedidoOutputPort cozinhaPedidoOutputPort;
    private final AlterarPedidoStatusInputPort alterarPedidoStatusInputPort;

    @Override
    public CozinhaPedido pendente(CozinhaPedido cozinhaPedido) {
        return cozinhaPedidoOutputPort.salvar(cozinhaPedido);
    }

    @Override
    @Transactional
    public CozinhaPedido receber(CozinhaPedido cozinhaPedido) {
        alterarPedidoStatusInputPort.recebido(cozinhaPedido.getIdPedido());
        return cozinhaPedidoOutputPort.salvar(cozinhaPedido);
    }

    @Override
    @Transactional
    public CozinhaPedido iniciarPreparo(CozinhaPedido cozinhaPedido) {
        alterarPedidoStatusInputPort.emPreparo(cozinhaPedido.getIdPedido());
        return cozinhaPedidoOutputPort.salvar(cozinhaPedido);
    }

    @Override
    @Transactional
    public CozinhaPedido finalizarPreparo(CozinhaPedido cozinhaPedido) {
        alterarPedidoStatusInputPort.pronto(cozinhaPedido.getIdPedido());
        return cozinhaPedidoOutputPort.salvar(cozinhaPedido);
    }

    @Override
    @Transactional
    public CozinhaPedido entregarPedido(CozinhaPedido cozinhaPedido) {
        alterarPedidoStatusInputPort.finalizado(cozinhaPedido.getIdPedido());
        return cozinhaPedidoOutputPort.salvar(cozinhaPedido);
    }
}
