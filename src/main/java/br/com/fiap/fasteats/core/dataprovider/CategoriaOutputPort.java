package br.com.fiap.fasteats.core.dataprovider;

import br.com.fiap.fasteats.core.domain.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaOutputPort {
    Optional<Categoria> consultar(Long id);
    Optional<List<Categoria>> listar();
    Optional<Categoria> consultarPorNome(String nome);
}
