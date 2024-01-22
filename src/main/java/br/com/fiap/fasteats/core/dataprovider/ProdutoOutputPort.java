package br.com.fiap.fasteats.core.dataprovider;

import br.com.fiap.fasteats.core.domain.model.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoOutputPort {
    Optional<Produto> consultar(Long id);
    Optional<List<Produto>> listar();
    Optional<Produto> consultarPorNome(String nome);
    Optional<List<Produto>> consultarPorCategoria(Long categoriaId);
}
