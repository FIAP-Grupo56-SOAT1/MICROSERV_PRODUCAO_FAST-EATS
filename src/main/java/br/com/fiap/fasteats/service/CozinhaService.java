package br.com.fiap.fasteats.service;

import br.com.fiap.fasteats.collection.Cozinha;

import java.util.List;
import java.util.Optional;

public interface CozinhaService {

    String save(Cozinha cozinha);

    List<Cozinha> findAll();

    void deleteById(String id);

    List<Cozinha> findByIdPedidoWith(Long idPedido);

    Optional<Cozinha> findById(String cozinhaId);

}
