package br.com.fiap.fasteats.dataprovider.client.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoResponse {
    private Long id;
    private ClienteResponse cliente;
    private String statusPedido;
    private LocalDateTime dataHoraCriado;
    private LocalDateTime dataHoraRecebimento;
    private LocalDateTime dataHoraFinalizado;
    private double valor;
    private String qrCode;
    private String urlPagamento;
    private List<ProdutoPedidoResponse> produtos = new ArrayList<>();
}