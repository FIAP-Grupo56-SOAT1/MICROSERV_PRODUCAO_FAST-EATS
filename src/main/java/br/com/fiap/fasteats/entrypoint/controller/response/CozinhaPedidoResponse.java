package br.com.fiap.fasteats.entrypoint.controller.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CozinhaPedidoResponse {
    private String cozinhaId;
    private LocalDateTime dataRecebimentoDoPedido;
    private LocalDateTime dataInicioPreparo;
    private LocalDateTime dataFinalizacaoPreparo;
    private LocalDateTime dataEntregaPedido;
    private Long idPedido;
    private String statusPedido;
    public String processoAtual;
}
