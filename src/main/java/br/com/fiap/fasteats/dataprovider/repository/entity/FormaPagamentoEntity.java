package br.com.fiap.fasteats.dataprovider.repository.entity;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@EqualsAndHashCode(of = "id")
public class FormaPagamentoEntity {
    private Long id;
    private String nome;
    private boolean externo = false;
    private boolean ativo = true;
}
