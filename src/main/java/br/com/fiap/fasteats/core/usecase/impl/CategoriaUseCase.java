package br.com.fiap.fasteats.core.usecase.impl;

import br.com.fiap.fasteats.core.domain.exception.CategoriaNotFound;
import br.com.fiap.fasteats.core.domain.model.Categoria;
import br.com.fiap.fasteats.core.usecase.CategoriaInputPort;
import br.com.fiap.fasteats.core.dataprovider.CategoriaOutputPort;

import java.util.List;

public class CategoriaUseCase implements CategoriaInputPort {
    private final CategoriaOutputPort categoriaOutputPort;

    public CategoriaUseCase(CategoriaOutputPort categoriaOutputPort) {
        this.categoriaOutputPort = categoriaOutputPort;
    }



    @Override
    public Categoria consultar(Long id) {
        return categoriaOutputPort.consultar(id).orElseThrow(() -> new CategoriaNotFound("Categoria id: " + id + " não encontrada"));
    }

    @Override
    public List<Categoria> listar() {
        return categoriaOutputPort.listar().orElseThrow(() -> new CategoriaNotFound("Não foram encontrados registros na Categoria"));
    }

    @Override
    public Categoria consultarPorNome(String nome) {
        return categoriaOutputPort.consultarPorNome(nome).orElseThrow(() -> new CategoriaNotFound("Categoria com nome: " + nome + " não encontrada"));
    }
}
