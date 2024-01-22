package br.com.fiap.fasteats.entrypoint.controller;

import br.com.fiap.fasteats.entrypoint.controller.response.StatusPagamentoResponse;
import br.com.fiap.fasteats.core.domain.model.StatusPagamento;
import br.com.fiap.fasteats.core.usecase.pagamento.StatusPagamentoInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("status-pagamentos")
@RequiredArgsConstructor
@Tag(name = "Status de Pagamento", description = "Controller que gerencia os status de pagemento de um produto")
public class StatusPagamentoController {
    private final StatusPagamentoInputPort statusPagamentoInputPort;

    @GetMapping
    @Operation(summary = "Listar status pedido", description = "Retorna o status do pedido.")
    public ResponseEntity<List<StatusPagamentoResponse>> listarStatusPedidos() {
        List<StatusPagamento> listStatusPagamento = statusPagamentoInputPort.listar();
        List<StatusPagamentoResponse> statusPagamentoResponses = new ArrayList<>();

        for (StatusPagamento statusPagamento : listStatusPagamento) {
            StatusPagamentoResponse statusPagamentoResponse = new StatusPagamentoResponse(statusPagamento);
            statusPagamentoResponses.add(statusPagamentoResponse);
        }

        return ResponseEntity.ok().body(statusPagamentoResponses);
    }
}
