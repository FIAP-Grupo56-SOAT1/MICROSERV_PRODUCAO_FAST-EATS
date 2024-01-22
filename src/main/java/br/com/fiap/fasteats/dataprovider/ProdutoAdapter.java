package br.com.fiap.fasteats.dataprovider;

import br.com.fiap.fasteats.dataprovider.repository.entity.ProdutoEntity;
import br.com.fiap.fasteats.dataprovider.repository.mapper.ProdutoEntityMapper;
import br.com.fiap.fasteats.core.domain.model.Produto;
import br.com.fiap.fasteats.core.dataprovider.ProdutoOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class ProdutoAdapter implements ProdutoOutputPort {
    //private final ProdutoRepository produtoRepository;
    private final ProdutoEntityMapper produtoEntityMapper;

    @Override
    public Optional<Produto> consultar(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Produto>> listar() {
        return Optional.empty();
    }

    @Override
    public Optional<Produto> consultarPorNome(String nome) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Produto>> consultarPorCategoria(Long categoriaId) {
        return Optional.empty();
    }


//    @Override
//    public Optional<Produto> consultar(Long id) {
//        Optional<ProdutoEntity> produtoEntity = produtoRepository.findById(id);
//
//        if (produtoEntity.isEmpty()) {
//            return Optional.empty();
//        }
//
//        Produto produto = produtoEntityMapper.toProduto(produtoEntity.get());
//        return Optional.of(produto);
//    }
//
//    @Override
//    public Optional<List<Produto>> listar() {
//        var produtosEntity = produtoRepository.findAll();
//        var produtos = produtosEntity.stream()
//                .map(produtoEntityMapper::toProduto)
//                .toList();
//        return Optional.of(produtos);
//    }
//
//
//    @Override
//    public Optional<Produto> consultarPorNome(String nome) {
//        var produtosEntity = produtoRepository.findByNome(nome);
//        var produtos = produtosEntity.stream()
//                .map(produtoEntityMapper::toProduto)
//                .toList();
//        return produtos.stream().findFirst();
//    }
//
//    @Override
//    public Optional<List<Produto>> consultarPorCategoria(Long categoriaId) {
//        var produtosEntity = produtoRepository.findByCategoria(categoriaId);
//        var produtos = produtosEntity.stream()
//                .map(produtoEntityMapper::toProduto)
//                .toList();
//        return Optional.of(produtos);
//    }
}
