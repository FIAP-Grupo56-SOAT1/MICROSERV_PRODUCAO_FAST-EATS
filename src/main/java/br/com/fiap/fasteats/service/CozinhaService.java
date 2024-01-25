package br.com.fiap.fasteats.service;

import br.com.fiap.fasteats.collection.Cozinha;
import br.com.fiap.fasteats.entrypoint.controller.response.CozinhaPedidoResponse;

import java.util.List;
import java.util.Optional;

public interface CozinhaService {

    String save(Cozinha cozinha);

    List<Cozinha> findAll();

    void deleteById(String id);

    Cozinha findByIdPedidoId(Long idPedido);
    Optional<Cozinha> findById(String cozinhaId);

    void receberPedido(Long idPedido, CozinhaPedidoResponse cozinhaPedidoResponse);

    void iniciarPreparo(Long idPedido, CozinhaPedidoResponse cozinhaPedidoResponse);

    void finalizarPreparo(Long idPedido, CozinhaPedidoResponse cozinhaPedidoResponse);

    void entregarPedido(Long idPedido, CozinhaPedidoResponse cozinhaPedidoResponse);


}
