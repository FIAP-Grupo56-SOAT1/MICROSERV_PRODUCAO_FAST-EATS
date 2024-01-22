package br.com.fiap.fasteats.dataprovider.repository.entity;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProdutoPedidoEntity {

    private ProdutoPedidoIdEntity id;

    public ProdutoPedidoIdEntity getId() {
        return this.id;
    }
    public void setId(ProdutoPedidoIdEntity id) {
        this.id = id;
    }
    private PedidoEntity pedido;
    private ProdutoEntity produto;
    private Integer quantidade;
    private Double valor;

    public String getNome() {
        return produto.getNome();
    }
}
