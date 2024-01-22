package br.com.fiap.fasteats.core.usecase.impl.pedido;

import br.com.fiap.fasteats.core.dataprovider.PagamentoOutputPort;
import br.com.fiap.fasteats.core.dataprovider.PedidoOutputPort;
import br.com.fiap.fasteats.core.domain.exception.PedidoNotFound;
import br.com.fiap.fasteats.core.domain.model.*;
import br.com.fiap.fasteats.core.usecase.ClienteInputPort;
import br.com.fiap.fasteats.core.usecase.pedido.PedidoInputPort;
import br.com.fiap.fasteats.core.usecase.pedido.StatusPedidoInputPort;
import br.com.fiap.fasteats.core.validator.PedidoValidator;

import java.time.LocalDateTime;
import java.util.List;

import static br.com.fiap.fasteats.core.constants.ClienteConstants.CLIENTE_SEM_IDENTIFICAR;
import static br.com.fiap.fasteats.core.constants.StatusPedidoConstants.STATUS_PEDIDO_CRIADO;

public class PedidoUseCase implements PedidoInputPort {
    private final PedidoOutputPort pedidoOutputPort;
    private final PagamentoOutputPort pagamentoOutputPort;
    private final PedidoValidator pedidoValidator;

    public PedidoUseCase(PedidoOutputPort pedidoOutputPort, PagamentoOutputPort pagamentoOutputPort, PedidoValidator pedidoValidator) {
        this.pedidoOutputPort = pedidoOutputPort;

        this.pagamentoOutputPort = pagamentoOutputPort;
        this.pedidoValidator = pedidoValidator;
    }



    @Override
    public Pedido consultar(Long id) {
        return formatarPedido(pedidoOutputPort.consultarPedido(id)
                .orElseThrow(() -> new PedidoNotFound("Pedido não encontrado id " + id)));
    }

    @Override
    public List<Pedido> listar() {
        List<Pedido> pedidos = pedidoOutputPort.listar();
        pedidos.forEach(this::formatarPedido);
        return pedidos;
    }

    @Override
    public Pedido atualizar(Pedido pedido) {
        pedidoValidator.validarAlterarPedido(pedido);
        return formatarPedido(pedidoOutputPort.salvarPedido(pedido));
    }

        @Override
    public void atualizarValorPedido(Pedido pedido) {
        pedido.setValor(pedido.getProdutos().stream().mapToDouble(p -> p.getValor() * p.getQuantidade()).sum());
    }

    private Pedido formatarPedido(Pedido pedido) {
        return formatarPedido(pedido, pagamentoOutputPort);
    }

    public static Pedido formatarPedido(Pedido pedido, PagamentoOutputPort pagamentoInputPort) {
        Pagamento pagamento = pagamentoInputPort.consultarPorPedidoId(pedido.getId()).orElse(null);
        if (pagamento != null) {
            pedido.setIdPagamentoExterno(pagamento.getIdPagamentoExterno());
            pedido.setUrlPagamento(pagamento.getUrlPagamento());
            pedido.setQrCode(pagamento.getQrCode());
        }
        return pedido;
    }
}