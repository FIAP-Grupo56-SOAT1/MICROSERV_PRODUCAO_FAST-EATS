package br.com.fiap.fasteats.entrypoint.controller;

import br.com.fiap.fasteats.core.domain.model.CozinhaPedido;
import br.com.fiap.fasteats.core.usecase.CozinhaPedidoInputPort;
import br.com.fiap.fasteats.entrypoint.controller.mapper.CozinhaPedidoResponseMapper;
import br.com.fiap.fasteats.entrypoint.controller.response.CozinhaPedidoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cozinha-pedido")
@RequiredArgsConstructor
@Tag(name = "CozinhaPedidoEntity", description = "Controller que gerencia o preparo de pedidos na cozinha")
public class CozinhaPedidoController {
    private final CozinhaPedidoInputPort cozinhaPedidoInputPort;
    private final CozinhaPedidoResponseMapper cozinhaPedidoResponseMapper;

    @PostMapping("{pedidoId}/receber-pedido")
    @Operation(summary = "Receber pedido", description = "Receber o pedido.")
    public ResponseEntity<Void> receberPedido(@PathVariable final Long pedidoId) {
        cozinhaPedidoInputPort.receber(pedidoId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{pedidoId}/iniciar-preparo")
    @Operation(summary = "Iniciar preparo", description = "Inicia o preparo de um pedido.")
    public ResponseEntity<CozinhaPedidoResponse> iniciarPreparo(@PathVariable final Long pedidoId) {
        CozinhaPedido cozinhaPedido = cozinhaPedidoInputPort.iniciarPreparo(pedidoId);
        CozinhaPedidoResponse cozinhaPedidoResponse = cozinhaPedidoResponseMapper.toCozinhaPedidoResponse(cozinhaPedido);
        return ResponseEntity.ok().body(cozinhaPedidoResponse);
    }

    @PatchMapping("{pedidoId}/finalizar-preparo")
    @Operation(summary = "Finalizar preparo", description = "Finaliza o preparo de pedido.")
    public ResponseEntity<CozinhaPedidoResponse> finalizarPreparo(@PathVariable final Long pedidoId) {
        CozinhaPedido cozinhaPedido = cozinhaPedidoInputPort.finalizarPreparo(pedidoId);
        CozinhaPedidoResponse cozinhaPedidoResponse = cozinhaPedidoResponseMapper.toCozinhaPedidoResponse(cozinhaPedido);
        return ResponseEntity.ok().body(cozinhaPedidoResponse);
    }

    @PatchMapping("{pedidoId}/retirar")
    @Operation(summary = "Entregar pedido", description = "Realiza a entrega do pedido.")
    public ResponseEntity<CozinhaPedidoResponse> retirarPedido(@PathVariable final Long pedidoId) {
        CozinhaPedido cozinhaPedido = cozinhaPedidoInputPort.retirar(pedidoId);
        CozinhaPedidoResponse cozinhaPedidoResponse = cozinhaPedidoResponseMapper.toCozinhaPedidoResponse(cozinhaPedido);
        return ResponseEntity.ok().body(cozinhaPedidoResponse);
    }

    @GetMapping("listar")
    public ResponseEntity<List<CozinhaPedidoResponse>> listar() {
        List<CozinhaPedido> cozinhaPedidoList = cozinhaPedidoInputPort.listar();
        List<CozinhaPedidoResponse> cozinhaPedidoResponseList = cozinhaPedidoResponseMapper.toCozinhaPedidoResponseList(cozinhaPedidoList);
        return ResponseEntity.ok().body(cozinhaPedidoResponseList);
    }

    @GetMapping("{pedidoId}")
    public ResponseEntity<CozinhaPedidoResponse> consultarPorIdPedido(@PathVariable("pedidoId") Long pedidoId) {
        CozinhaPedido cozinhaPedido = cozinhaPedidoInputPort.consultarPorIdPedido(pedidoId);
        CozinhaPedidoResponse cozinhaPedidoResponse = cozinhaPedidoResponseMapper.toCozinhaPedidoResponse(cozinhaPedido);
        return ResponseEntity.ok().body(cozinhaPedidoResponse);
    }
}
