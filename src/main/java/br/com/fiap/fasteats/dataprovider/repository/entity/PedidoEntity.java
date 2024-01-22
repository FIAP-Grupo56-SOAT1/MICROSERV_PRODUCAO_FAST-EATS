package br.com.fiap.fasteats.dataprovider.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoEntity {

    private Long id;
    private ClienteEntity cliente;
    private StatusPedidoEntity statusEntity;
    private LocalDateTime dataHoraCriado;
    private LocalDateTime dataHoraRecebimento;
    private LocalDateTime dataHoraFinalizado;
    private Boolean ativo = true;
    private Double valor;
    private List<ProdutoPedidoEntity> listaProdutos = new ArrayList<>();
}
