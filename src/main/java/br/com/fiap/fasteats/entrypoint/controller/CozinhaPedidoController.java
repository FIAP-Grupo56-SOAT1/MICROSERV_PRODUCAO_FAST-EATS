package br.com.fiap.fasteats.entrypoint.controller;

import br.com.fiap.fasteats.core.domain.model.CozinhaPedido;
import br.com.fiap.fasteats.core.usecase.CozinhaPedidoInputPort;
import br.com.fiap.fasteats.entrypoint.controller.mapper.CozinhaPedidoResponseMapper;
import br.com.fiap.fasteats.entrypoint.controller.response.CozinhaPedidoResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cozinha-pedido")
@RequiredArgsConstructor
@Tag(name = "Cozinha Pedido", description = "Controller que gerencia os pedidos na cozinha")
public class CozinhaPedidoController {
    private final CozinhaPedidoInputPort cozinhaPedidoInputPort;
    private final CozinhaPedidoResponseMapper cozinhaPedidoResponseMapper;

    @GetMapping("listar")
    public ResponseEntity<List<CozinhaPedidoResponse>> listar() {
        List<CozinhaPedido> cozinhaPedidoList = cozinhaPedidoInputPort.listar();
        List<CozinhaPedidoResponse> cozinhaPedidoResponseList = cozinhaPedidoResponseMapper.toCozinhaPedidoResponseList(cozinhaPedidoList);
        return ResponseEntity.ok().body(cozinhaPedidoResponseList);
    }
    @GetMapping("{cozinhaId}")
    public ResponseEntity<CozinhaPedidoResponse> consultarPorIdPedido(@PathVariable("cozinhaId") String cozinhaId) {
        CozinhaPedido cozinhaPedido = cozinhaPedidoInputPort.consultar(cozinhaId);
        CozinhaPedidoResponse cozinhaPedidoResponse = cozinhaPedidoResponseMapper.toCozinhaPedidoResponse(cozinhaPedido);
        return ResponseEntity.ok().body(cozinhaPedidoResponse);
    }

    @GetMapping("pedido/{pedidoId}")
    public ResponseEntity<CozinhaPedidoResponse> consultarPorIdPedido(@PathVariable("pedidoId") Long pedidoId) {
        CozinhaPedido cozinhaPedido = cozinhaPedidoInputPort.consultarPorIdPedido(pedidoId);
        CozinhaPedidoResponse cozinhaPedidoResponse = cozinhaPedidoResponseMapper.toCozinhaPedidoResponse(cozinhaPedido);
        return ResponseEntity.ok().body(cozinhaPedidoResponse);
    }
}
