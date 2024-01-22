package br.com.fiap.fasteats.core.usecase;

import br.com.fiap.fasteats.core.domain.model.Produto;

import java.util.List;

public interface ProdutoInputPort {

    Produto consultar(Long id);
    List<Produto> listar();
    Produto consultarPorNome(String nome);

    List<Produto> consultarPorCategoria(Long categoriaId);
}
