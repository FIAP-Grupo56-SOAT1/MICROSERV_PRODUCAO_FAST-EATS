package br.com.fiap.fasteats.dataprovider.repository.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@EqualsAndHashCode(of = "id")
public class CategoriaEntity {


    private Long id;


    private String nome;


    private String descricao;


    private Boolean ativo;
}
