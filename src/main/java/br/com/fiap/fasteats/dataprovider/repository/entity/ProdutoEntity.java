package br.com.fiap.fasteats.dataprovider.repository.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProdutoEntity {
    private Long id;
    private String nome;
    private String descricao;
    private Double valor;
    private Long categoriaId;
    private CategoriaEntity categoriaEntity;
    private Boolean ativo = true;
    private String imagemBase64;
    private String imagemUrl;
}
