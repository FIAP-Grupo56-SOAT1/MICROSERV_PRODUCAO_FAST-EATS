package br.com.fiap.fasteats.service.Impl;

import br.com.fiap.fasteats.collection.Cozinha;
import br.com.fiap.fasteats.dataprovider.repository.CozinhaRepository;
import br.com.fiap.fasteats.service.CozinhaService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CozinhaServiceImpl implements CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;


    @Override
    public String save(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha).getCozinhaId();
    }

    @Override
    public List<Cozinha> findAll() {
        return cozinhaRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        cozinhaRepository.deleteById(id);
    }

    @Override
    public List<Cozinha> findAllByPedidoId(Long idPedido) {
        return null;
    }


}
