package br.com.fiap.fasteats.service.Impl;

import br.com.fiap.fasteats.collection.Cozinha;
import br.com.fiap.fasteats.core.constants.ProcessoCozinhaConstants;
import br.com.fiap.fasteats.dataprovider.repository.CozinhaRepository;
import br.com.fiap.fasteats.entrypoint.controller.response.CozinhaPedidoResponse;
import br.com.fiap.fasteats.service.CozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    public Optional<Cozinha> findById(String cozinhaId) {

        return cozinhaRepository.findById(cozinhaId);
    }

    @Override
    public void receberPedido(Long idPedido, CozinhaPedidoResponse cozinhaPedidoResponse) {
        var cozinha = objterCozinhaByPedidoId(idPedido);

        cozinha.setDataRecebimentoDoPedido(LocalDateTime.now());
        cozinha.setStatusPedido(cozinhaPedidoResponse.getStatusNome());
        cozinha.setStatusPedidoId(cozinhaPedidoResponse.getStatusId());
        cozinha.setProcessoAtual(ProcessoCozinhaConstants.RECEBIDO);

        cozinhaRepository.save(cozinha);
    }

    @Override
    public void iniciarPreparo(Long idPedido, CozinhaPedidoResponse cozinhaPedidoResponse) {
        var cozinha = objterCozinhaByPedidoId(idPedido);

        cozinha.setDataInicioPreparo(LocalDateTime.now());
        cozinha.setStatusPedido(cozinhaPedidoResponse.getStatusNome());
        cozinha.setStatusPedidoId(cozinhaPedidoResponse.getStatusId());
        cozinha.setProcessoAtual(ProcessoCozinhaConstants.INICIO_PROCESSO);

        cozinhaRepository.save(cozinha);
    }

    @Override
    public void finalizarPreparo(Long idPedido, CozinhaPedidoResponse cozinhaPedidoResponse) {
        var cozinha = objterCozinhaByPedidoId(idPedido);

        cozinha.setDataFinalizacaoPreparo(LocalDateTime.now());
        cozinha.setStatusPedido(cozinhaPedidoResponse.getStatusNome());
        cozinha.setStatusPedidoId(cozinhaPedidoResponse.getStatusId());
        cozinha.setProcessoAtual(ProcessoCozinhaConstants.FINALIZAR_PREPARO);

        cozinhaRepository.save(cozinha);
    }

    @Override
    public void entregarPedido(Long idPedido, CozinhaPedidoResponse cozinhaPedidoResponse) {
        var cozinha = objterCozinhaByPedidoId(idPedido);

        cozinha.setDataEntregaPedido(LocalDateTime.now());
        cozinha.setStatusPedido(cozinhaPedidoResponse.getStatusNome());
        cozinha.setStatusPedidoId(cozinhaPedidoResponse.getStatusId());
        cozinha.setProcessoAtual(ProcessoCozinhaConstants.ENTREGAR_PEDIDO);

        cozinhaRepository.save(cozinha);
    }

    @Override
    public Cozinha findByIdPedidoId(Long idPedido) {
        return cozinhaRepository.findByIdPedido(idPedido);
    }

    private Cozinha objterCozinhaByPedidoId(Long pedidoId){
        var cozinha = cozinhaRepository.findByIdPedido(pedidoId);
        if(cozinha == null){
            cozinha =  new Cozinha();
            cozinha.setIdPedido(pedidoId);
        }
        return  cozinha;
    }




}
