package fasteats.core.usecase.impl.pedido;

import br.com.fiap.fasteats.core.domain.model.Pedido;
import br.com.fiap.fasteats.core.usecase.pedido.CozinhaPedidoInputPort;
import br.com.fiap.fasteats.core.usecase.pedido.AlterarPedidoStatusInputPort;


public class CozinhaPedidoUseCase implements CozinhaPedidoInputPort {

     private final AlterarPedidoStatusInputPort alterarPedidoStatusInputPort;

    public CozinhaPedidoUseCase(AlterarPedidoStatusInputPort alterarPedidoStatusInputPort) {
        this.alterarPedidoStatusInputPort = alterarPedidoStatusInputPort;
    }

    @Override
    public Pedido receberPedido(Long pedidoId) {
        return null;
    }

    @Override
    public Pedido iniciarPreparoPedido(Long pedidoId) {
        return null;
    }

    @Override
    public Pedido finalizarPreparoPedido(Long pedidoId) {
        return null;
    }

    @Override
    public Pedido retirarPedido(Long pedidoId) {
        return null;
    }



//
//    public CozinhaPedidoUseCase(AlterarPedidoStatusInputPort alterarPedidoStatusInputPort) {
//        this.alterarPedidoStatusInputPort = alterarPedidoStatusInputPort;
//    }
//
//    @Override
//    public Pedido receberPedido(Long pedidoId) {
//        return alterarPedidoStatusInputPort.recebido(pedidoId);
//    }
//
//    @Override
//    public Pedido iniciarPreparoPedido(Long pedidoId) {
//        return alterarPedidoStatusInputPort.emPreparo(pedidoId);
//    }
//
//    @Override
//    public Pedido finalizarPreparoPedido(Long pedidoId) {
//        return alterarPedidoStatusInputPort.pronto(pedidoId);
//    }
//
//    @Override
//    public Pedido retirarPedido(Long pedidoId) {
//        return alterarPedidoStatusInputPort.finalizado(pedidoId);
//    }



}
