package br.com.fiap.fasteats.service;

import br.com.fiap.fasteats.collection.Cozinha;

import java.util.List;

public interface CozinhaService {

    String save(Cozinha cozinha);

    List<Cozinha> findAll();

    void deleteById(String id);

    List<Cozinha> findAllByPedidoId(Long idPedido);

}
