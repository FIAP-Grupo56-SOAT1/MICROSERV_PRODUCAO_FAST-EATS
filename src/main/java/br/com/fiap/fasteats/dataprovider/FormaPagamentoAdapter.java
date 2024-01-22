package br.com.fiap.fasteats.dataprovider;

import br.com.fiap.fasteats.dataprovider.repository.mapper.FormaPagamentoEntityMapper;
import br.com.fiap.fasteats.core.domain.model.FormaPagamento;
import br.com.fiap.fasteats.core.dataprovider.FormaPagamentoOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FormaPagamentoAdapter implements FormaPagamentoOutputPort {

    private final FormaPagamentoEntityMapper formaPagamentoEntityMapper;

    @Override
    public Optional<FormaPagamento> consultar(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<FormaPagamento>> listar() {
        return Optional.empty();
    }

    @Override
    public Optional<FormaPagamento> consultarPorNome(String nome) {
        return Optional.empty();
    }


//
//    @Override
//    public Optional<FormaPagamento> consultar(Long id) {
//        return formaPagamentoRepository.findById(id).map(formaPagamentoEntityMapper::toFormaPagamento);
//    }
//
//
//    @Override
//    public Optional<List<FormaPagamento>> listar() {
//        var formaPagamentoEntities = formaPagamentoRepository.findAll();
//        var formasPagamento = formaPagamentoEntities.stream()
//                .map(formaPagamentoEntityMapper::toFormaPagamento)
//                .toList();
//        return Optional.of(formasPagamento);
//    }
//
//    @Override
//    public Optional<FormaPagamento> consultarPorNome(String nome) {
//        var formaPagamentoEntity = formaPagamentoRepository.findByNome(nome.toUpperCase());
//        var formaPagamento = formaPagamentoEntity.stream()
//                .map(formaPagamentoEntityMapper::toFormaPagamento)
//                .toList();
//        return formaPagamento.stream().findFirst();
//    }
}
