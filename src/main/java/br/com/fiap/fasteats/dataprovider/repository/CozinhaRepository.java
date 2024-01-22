package br.com.fiap.fasteats.dataprovider.repository;


import br.com.fiap.fasteats.collection.Cozinha;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CozinhaRepository extends MongoRepository<Cozinha,String>{
}
