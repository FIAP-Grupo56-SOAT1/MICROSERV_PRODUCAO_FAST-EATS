package br.com.fiap.fasteats.dataprovider.repository.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@EqualsAndHashCode(of = "id")
public class PagamentoEntity {

    private Long id;


    private FormaPagamentoEntity formaPagamento;


    private StatusPagamentoEntity statusPagamento;


    private PedidoEntity pedido;


    private LocalDateTime dataHoraCriado;


    private LocalDateTime dataHoraProcessamento;


    private LocalDateTime dataHoraFinalizado;


    private Long idPagamentoExterno;


    private String urlPagamento;


    private String qrCode;
}
