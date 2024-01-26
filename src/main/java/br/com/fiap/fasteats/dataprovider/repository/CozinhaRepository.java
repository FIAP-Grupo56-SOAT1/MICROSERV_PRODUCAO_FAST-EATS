package br.com.fiap.fasteats.dataprovider.repository;


import br.com.fiap.fasteats.dataprovider.repository.entity.CozinhaPedidoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CozinhaRepository extends MongoRepository<CozinhaPedidoEntity,String>{
    Optional<CozinhaPedidoEntity> findByIdPedido(Long pedidoId);
}
