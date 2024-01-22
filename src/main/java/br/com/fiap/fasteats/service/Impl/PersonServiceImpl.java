package br.com.fiap.fasteats.service.Impl;

import br.com.fiap.fasteats.collection.Cozinha;
import br.com.fiap.fasteats.dataprovider.repository.CozinhaRepository;
import br.com.fiap.fasteats.service.CozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;


    @Override
    public String save(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha).getCozinhaId();
    }
}
