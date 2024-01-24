package br.com.fiap.fasteats.dataprovider.repository;


import br.com.fiap.fasteats.collection.Cozinha;
import lombok.extern.java.Log;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CozinhaRepository extends MongoRepository<Cozinha,String>{

}
