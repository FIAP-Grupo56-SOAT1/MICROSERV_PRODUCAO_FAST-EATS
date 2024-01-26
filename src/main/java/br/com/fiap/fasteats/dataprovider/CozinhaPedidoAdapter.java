package br.com.fiap.fasteats.dataprovider;

import br.com.fiap.fasteats.core.dataprovider.CozinhaPedidoOutputPort;
import br.com.fiap.fasteats.core.domain.model.CozinhaPedido;
import br.com.fiap.fasteats.dataprovider.repository.CozinhaRepository;
import br.com.fiap.fasteats.dataprovider.repository.entity.CozinhaPedidoEntity;
import br.com.fiap.fasteats.dataprovider.repository.mapper.CozinhaPedidoEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CozinhaPedidoAdapter implements CozinhaPedidoOutputPort {
    private final CozinhaRepository cozinhaRepository;
    private final CozinhaPedidoEntityMapper cozinhaPedidoEntityMapper;

    @Override
    public CozinhaPedido salvar(CozinhaPedido cozinhaPedido) {
        CozinhaPedidoEntity cozinhaPedidoEntity = cozinhaPedidoEntityMapper.toCozinhaPedidoEntity(cozinhaPedido);
        return cozinhaPedidoEntityMapper.toCozinhaPedido(cozinhaRepository.save(cozinhaPedidoEntity));
    }

    @Override
    public List<CozinhaPedido> listar() {
        return cozinhaRepository.findAll().stream().map(cozinhaPedidoEntityMapper::toCozinhaPedido).toList();
    }

    @Override
    public Optional<CozinhaPedido> consultar(String cozinhaId) {
        return cozinhaRepository.findById(cozinhaId).map(cozinhaPedidoEntityMapper::toCozinhaPedido);
    }

    @Override
    public Optional<CozinhaPedido> consultarPorIdPedido(Long idPedido) {
        return cozinhaRepository.findByIdPedido(idPedido).map(cozinhaPedidoEntityMapper::toCozinhaPedido);
    }
}
