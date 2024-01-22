package br.com.fiap.fasteats.entrypoint.controller;

import br.com.fiap.fasteats.collection.Cozinha;
import br.com.fiap.fasteats.core.domain.model.Pedido;
import br.com.fiap.fasteats.core.domain.model.StatusPedido;
import br.com.fiap.fasteats.core.usecase.pedido.CozinhaPedidoInputPort;
import br.com.fiap.fasteats.core.usecase.pedido.StatusPedidoInputPort;
import br.com.fiap.fasteats.entrypoint.controller.response.CozinhaPedidoResponse;
import br.com.fiap.fasteats.service.CozinhaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("cozinha-pedido")
@RequiredArgsConstructor
@Tag(name = "Cozinha", description = "Controller que gerencia o preparo de pedidos na cozinha")
public class CozinhaPedidoController {
    private final StatusPedidoInputPort statusPedidoInputPort;
    private final CozinhaPedidoInputPort cozinhaPedidoInputPort;

    @Autowired
    private CozinhaService cozinhaService;

    @PatchMapping("{idPedido}/iniciar-preparo")
    @Operation(summary = "Iniciar preparo", description = "Inicia o preparo de um pedido.")
    public ResponseEntity<Cozinha> iniciarPreparo(@PathVariable final Long idPedido) {
        Pedido pedidoAtualizado = cozinhaPedidoInputPort.iniciarPreparoPedido(idPedido);
        StatusPedido statusPedido = statusPedidoInputPort.consultar(pedidoAtualizado.getStatusPedido());
        CozinhaPedidoResponse cozinhaPedidoResponse = new CozinhaPedidoResponse(idPedido, pedidoAtualizado.getStatusPedido(), statusPedido.getNome());
        Cozinha cozinha = new Cozinha(LocalDate.now(),idPedido,cozinhaPedidoResponse.getStatusId(),cozinhaPedidoResponse.getStatusNome());
        return ResponseEntity.ok().body(cozinha);
    }

    @PatchMapping("{idPedido}/finalizar-preparo")
    @Operation(summary = "Finalizar preparo", description = "Finaliza o preparo de pedido.")
    public ResponseEntity<CozinhaPedidoResponse> finalizarPreparo(@PathVariable final Long idPedido) {
        Pedido pedidoAtualizado = cozinhaPedidoInputPort.finalizarPreparoPedido(idPedido);
        StatusPedido statusPedido = statusPedidoInputPort.consultar(pedidoAtualizado.getStatusPedido());
        CozinhaPedidoResponse cozinhaPedidoResponse = new CozinhaPedidoResponse(idPedido, pedidoAtualizado.getStatusPedido(), statusPedido.getNome());
        return ResponseEntity.ok().body(cozinhaPedidoResponse);
    }

    @PatchMapping("{idPedido}/retirar")
    @Operation(summary = "Entregar pedido", description = "Realiza a entrega do pedido.")
    public ResponseEntity<CozinhaPedidoResponse> retirarPedido(@PathVariable final Long idPedido) {
        Pedido pedidoAtualizado = cozinhaPedidoInputPort.retirarPedido(idPedido);
        StatusPedido statusPedido = statusPedidoInputPort.consultar(pedidoAtualizado.getStatusPedido());
        CozinhaPedidoResponse cozinhaPedidoResponse = new CozinhaPedidoResponse(idPedido, pedidoAtualizado.getStatusPedido(), statusPedido.getNome());
        return ResponseEntity.ok().body(cozinhaPedidoResponse);
    }
}
