package br.com.fiap.fasteats.entrypoint.controller;

import br.com.fiap.fasteats.core.domain.model.Pedido;
import br.com.fiap.fasteats.core.usecase.pedido.CancelarPedidoInputPort;
import br.com.fiap.fasteats.core.usecase.pedido.ConfirmarPedidoInputPort;
import br.com.fiap.fasteats.core.usecase.pedido.PedidoInputPort;
import br.com.fiap.fasteats.entrypoint.controller.mapper.PedidoMapper;
import br.com.fiap.fasteats.entrypoint.controller.request.PedidoRequest;
import br.com.fiap.fasteats.entrypoint.controller.response.PedidoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pedidos")
@RequiredArgsConstructor
@Tag(name = "Pedido", description = "Controller que gerencia o processo de criação de um pedido")
public class PedidoController {
    private final PedidoInputPort pedidoInputPort;
    private final ConfirmarPedidoInputPort confirmarPedidoInputPort;
    private final CancelarPedidoInputPort cancelarPedidoInputPort;
    private final PedidoMapper pedidoMapper;

    @PostMapping
    @Operation(summary = "Identificação do cliente e criar pedido", description = "Identifica ou não o cliente e cria um novo pedido.")
    public ResponseEntity<PedidoResponse> criarPedido(@Valid @RequestBody PedidoRequest pedidoRequest) {
        Pedido pedido = pedidoMapper.toPedido(pedidoRequest);
        Pedido pedidoCriado = pedidoInputPort.criar(pedido);
        PedidoResponse pedidoResponse = pedidoMapper.toPedidoResponse(pedidoCriado);
        return new ResponseEntity<>(pedidoResponse, HttpStatus.CREATED);
    }

    @GetMapping("{idPedido}")
    @Operation(summary = "Consultar pedido por ID", description = "Consulta um pedido.")
    public ResponseEntity<PedidoResponse> consultarPedido(@PathVariable("idPedido") Long idPedido) {
        Pedido pedido = pedidoInputPort.consultar(idPedido);
        PedidoResponse pedidoResponse = pedidoMapper.toPedidoResponse(pedido);
        return new ResponseEntity<>(pedidoResponse, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Listar pedidos", description = "Lista todos os pedidos.")
    public ResponseEntity<List<PedidoResponse>> listarPedidos() {
        List<Pedido> pedidos = pedidoInputPort.listar();
        List<PedidoResponse> pedidoResponse = pedidoMapper.toPedidosResponse(pedidos);
        return new ResponseEntity<>(pedidoResponse, HttpStatus.OK);
    }

    @PatchMapping("{idPedido}")
    @Operation(summary = "Atualizar pedido", description = "Atualiza um pedido.")
    public ResponseEntity<PedidoResponse> updatePedido(@PathVariable("idPedido") Long idPedido,
                                                       @RequestBody PedidoRequest pedidoRequest) {
        Pedido pedido = pedidoMapper.toPedido(pedidoRequest);
        pedido.setId(idPedido);
        Pedido pedidoAtualizado = pedidoInputPort.atualizar(pedido);
        PedidoResponse pedidoResponse = pedidoMapper.toPedidoResponse(pedidoAtualizado);
        return new ResponseEntity<>(pedidoResponse, HttpStatus.OK);
    }

    @DeleteMapping("{idPedido}")
    @Operation(summary = "Deletar pedido", description = "Deleta um pedido.")
    public ResponseEntity<Void> deletePedido(@PathVariable("idPedido") Long idPedido) {
        pedidoInputPort.deletar(idPedido);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("{idPedido}/cancelar")
    @Operation(summary = "Cancelar pedido", description = "Cancela um pedido.")
    public ResponseEntity<PedidoResponse> cancelarProduto(@PathVariable("idPedido") Long idPedido) {
        Pedido pedidoCancelado = cancelarPedidoInputPort.cancelar(idPedido);
        PedidoResponse pedidoResponse = pedidoMapper.toPedidoResponse(pedidoCancelado);
        return new ResponseEntity<>(pedidoResponse, HttpStatus.OK);
    }

    @PatchMapping("{idPedido}/confirmar")
    @Operation(summary = "Confirmar pedido", description = "Confirma um pedido.")
    public ResponseEntity<PedidoResponse> confirmarPedido(@PathVariable("idPedido") Long idPedido,
                                                          @RequestParam(value = "tipoPagamentoId") Long tipoPagamentoId) {
        Pedido pedidoConfirmado = confirmarPedidoInputPort.confirmar(idPedido, tipoPagamentoId);
        PedidoResponse pedidoResponse = pedidoMapper.toPedidoResponse(pedidoConfirmado);
        return new ResponseEntity<>(pedidoResponse, HttpStatus.OK);
    }
}
