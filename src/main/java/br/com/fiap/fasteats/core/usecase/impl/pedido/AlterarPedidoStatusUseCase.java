package br.com.fiap.fasteats.core.usecase.impl.pedido;

import br.com.fiap.fasteats.core.dataprovider.PedidoOutputPort;
import br.com.fiap.fasteats.core.domain.exception.PedidoNotFound;
import br.com.fiap.fasteats.core.domain.model.Pedido;
import br.com.fiap.fasteats.core.usecase.pedido.AlterarPedidoStatusInputPort;
import br.com.fiap.fasteats.core.usecase.pedido.StatusPedidoInputPort;
import br.com.fiap.fasteats.core.validator.AlterarPedidoStatusValidator;

import java.time.LocalDateTime;

import static br.com.fiap.fasteats.core.constants.StatusPedidoConstants.*;

public class AlterarPedidoStatusUseCase implements AlterarPedidoStatusInputPort {
    private final AlterarPedidoStatusValidator alterarPedidoStatusValidator;
    private final StatusPedidoInputPort statusPedidoInputPort;
    private final PedidoOutputPort pedidoOutputPort;

    public AlterarPedidoStatusUseCase(AlterarPedidoStatusValidator alterarPedidoStatusValidator,
            StatusPedidoInputPort statusPedidoInputPort,
            PedidoOutputPort pedidoOutputPort) {
        this.alterarPedidoStatusValidator = alterarPedidoStatusValidator;
        this.statusPedidoInputPort = statusPedidoInputPort;
        this.pedidoOutputPort = pedidoOutputPort;

    }

    @Override
    public Pedido aguardandoPagamento(Long pedidoId) {
        Pedido pedido = recuperarPedido(pedidoId);
        alterarPedidoStatusValidator.validarAguardandoPagamento(pedidoId);
        Pedido pedidoAtualizado = atualizarStatusPedido(pedido, STATUS_PEDIDO_AGUARDANDO_PAGAMENTO);
        pedidoOutputPort.atualizarStatus(pedidoAtualizado);
        return recuperarPedido(pedidoId);
    }

    @Override
    public Pedido pago(Long pedidoId) {
        Pedido pedido = recuperarPedido(pedidoId);
        alterarPedidoStatusValidator.validarPago(pedidoId);
        Pedido pedidoAtualizado = atualizarStatusPedido(pedido, STATUS_PEDIDO_PAGO);
        pedidoOutputPort.atualizarStatus(pedidoAtualizado);
        return recuperarPedido(pedidoId);
    }

    @Override
    public Pedido recebido(Long pedidoId) {
        Pedido pedido = recuperarPedido(pedidoId);
        alterarPedidoStatusValidator.validarRecebido(pedidoId);
        Pedido pedidoAtualizado = atualizarStatusPedido(pedido, STATUS_PEDIDO_RECEBIDO);
        pedidoOutputPort.recebido(pedidoAtualizado);
        return recuperarPedido(pedidoId);
    }

    @Override
    public Pedido emPreparo(Long pedidoId) {
        Pedido pedido = recuperarPedido(pedidoId);
        alterarPedidoStatusValidator.validarEmPreparo(pedidoId);
        Pedido pedidoAtualizado = atualizarStatusPedido(pedido, STATUS_PEDIDO_EM_PREPARO);
        pedidoOutputPort.atualizarStatus(pedidoAtualizado);
        return recuperarPedido(pedidoId);
    }

    @Override
    public Pedido pronto(Long pedidoId) {
        Pedido pedido = recuperarPedido(pedidoId);
        alterarPedidoStatusValidator.validarPronto(pedidoId);
        Pedido pedidoAtualizado = atualizarStatusPedido(pedido, STATUS_PEDIDO_PRONTO);
        pedidoOutputPort.pronto(pedidoAtualizado);
        return recuperarPedido(pedidoId);
    }

    @Override
    public Pedido finalizado(Long pedidoId) {
        Pedido pedido = recuperarPedido(pedidoId);
        alterarPedidoStatusValidator.validarFinalizado(pedidoId);
        Pedido pedidoAtualizado = atualizarStatusPedido(pedido, STATUS_PEDIDO_FINALIZADO);
        pedidoOutputPort.finalizarPedido(pedidoAtualizado);
        return recuperarPedido(pedidoId);
    }

    @Override
    public Pedido cancelado(Long pedidoId) {
        Pedido pedido = recuperarPedido(pedidoId);
        alterarPedidoStatusValidator.validarCancelado(pedidoId);
        Pedido pedidoAtualizado = atualizarStatusPedido(pedido, STATUS_PEDIDO_CANCELADO);
        pedidoOutputPort.cancelaPedido(pedidoAtualizado);
        return recuperarPedido(pedidoId);
    }

    private Pedido recuperarPedido(Long pedidoId) {
        var pedido =  pedidoOutputPort.consultarPedido(pedidoId);
        if(pedido == null){
            new PedidoNotFound("Pedido não encontrado id " + pedidoId);
        }
        return  pedido;
    }

    private Pedido atualizarStatusPedido(Pedido pedido, String novoStatusPedido) {
        pedido.setIdStatusPedido(statusPedidoInputPort.consultarPorNome(novoStatusPedido).getId());
        pedido.setStatusPedido(novoStatusPedido);
        return pedido;
    }

}
