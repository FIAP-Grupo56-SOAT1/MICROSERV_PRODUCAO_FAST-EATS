package br.com.fiap.fasteats.dataprovider.repository.entity;



import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class StatusPagamentoEntity {
    private Long id;
    private String nome;
    private Boolean ativo = true;

}
