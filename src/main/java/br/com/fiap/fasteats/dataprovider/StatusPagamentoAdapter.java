package br.com.fiap.fasteats.dataprovider;


import br.com.fiap.fasteats.core.domain.model.StatusPagamento;
import br.com.fiap.fasteats.core.dataprovider.StatusPagamentoOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StatusPagamentoAdapter implements StatusPagamentoOutputPort {

    @Override
    public List<StatusPagamento> listar() {
        return null;
    }

    @Override
    public Optional<StatusPagamento> consultarPorNome(String nome) {
        return Optional.empty();
    }

    @Override
    public Optional<StatusPagamento> consultar(Long id) {
        return Optional.empty();
    }
}
