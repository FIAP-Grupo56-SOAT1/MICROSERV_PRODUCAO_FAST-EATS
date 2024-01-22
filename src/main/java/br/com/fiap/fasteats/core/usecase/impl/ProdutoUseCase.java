package br.com.fiap.fasteats.core.usecase.impl;

import br.com.fiap.fasteats.core.domain.exception.CategoriaNotFound;
import br.com.fiap.fasteats.core.domain.exception.ProdutoNotFound;
import br.com.fiap.fasteats.core.domain.exception.ValidarValorException;
import br.com.fiap.fasteats.core.domain.model.Categoria;
import br.com.fiap.fasteats.core.domain.model.Produto;
import br.com.fiap.fasteats.core.usecase.ProdutoInputPort;
import br.com.fiap.fasteats.core.dataprovider.CategoriaOutputPort;
import br.com.fiap.fasteats.core.dataprovider.ProdutoOutputPort;

import java.util.List;
import java.util.Optional;

public class ProdutoUseCase implements ProdutoInputPort {
    private final ProdutoOutputPort produtoOutputPort;


    public ProdutoUseCase(ProdutoOutputPort produtoOutputPort) {
        this.produtoOutputPort = produtoOutputPort;

    }



    @Override
    public Produto consultar(Long id) {
        return produtoOutputPort.consultar(id).orElseThrow(() -> new ProdutoNotFound("Produto id: %d não encontrado".formatted(id)));
    }


    @Override
    public List<Produto> listar() {
        return produtoOutputPort.listar().orElseThrow(() -> new ProdutoNotFound("Não foram encontrados registros de Produto"));
    }

    @Override
    public Produto consultarPorNome(String nome) {
        return produtoOutputPort.consultarPorNome(nome).orElseThrow(() -> new ProdutoNotFound("Produto com nome: %s não encontrado".formatted(nome)));
    }

    @Override
    public List<Produto> consultarPorCategoria(Long categoriaId) {
        return produtoOutputPort.consultarPorCategoria(categoriaId).orElseThrow(() -> new ProdutoNotFound("Produto com categoria: %d não encontrado".formatted(categoriaId)));
    }



    private Boolean validarValor(Double valor){
        return valor >= 0;
    }
}
