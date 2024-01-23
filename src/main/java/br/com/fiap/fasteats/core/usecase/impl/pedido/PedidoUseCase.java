package br.com.fiap.fasteats.core.usecase.impl.pedido;

import br.com.fiap.fasteats.core.dataprovider.PedidoOutputPort;
import br.com.fiap.fasteats.core.domain.exception.PedidoNotFound;
import br.com.fiap.fasteats.core.domain.model.*;
import br.com.fiap.fasteats.core.usecase.pedido.PedidoInputPort;
import br.com.fiap.fasteats.core.validator.PedidoValidator;

import java.util.ArrayList;
import java.util.List;

public class PedidoUseCase implements PedidoInputPort {
    private final PedidoOutputPort pedidoOutputPort;
    private final PedidoValidator pedidoValidator;

    public PedidoUseCase(PedidoOutputPort pedidoOutputPort, PedidoValidator pedidoValidator) {
        this.pedidoOutputPort = pedidoOutputPort;
        this.pedidoValidator = pedidoValidator;
    }



    @Override
    public Pedido consultar(Long id) {
        var pedido =  formatarPedido(pedidoOutputPort.consultarPedido(id));
        if(pedido == null){
            new PedidoNotFound("Pedido não encontrado id " + id);
        }
        return  pedido;
    }

    @Override
    public List<Pedido> listar() {
        List<Pedido> pedidos = pedidoOutputPort.listar();
        if(pedidos.isEmpty()){
            new ArrayList<Pedido>();
        }

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
        return pedido;
    }


}