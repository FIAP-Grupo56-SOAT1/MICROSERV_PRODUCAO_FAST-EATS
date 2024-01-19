package br.com.fiap.fasteats.dataprovider;

import br.com.fiap.fasteats.dataprovider.repository.ClienteRepository;
import br.com.fiap.fasteats.dataprovider.repository.entity.ClienteEntity;
import br.com.fiap.fasteats.dataprovider.repository.mapper.ClienteEntityMapper;
import br.com.fiap.fasteats.core.domain.model.Cliente;
import br.com.fiap.fasteats.core.dataprovider.ClienteOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClienteAdapter implements ClienteOutputPort {
    private final ClienteRepository clienteRepository;
    private final ClienteEntityMapper clienteEntityMapper;

    @Override
    public Cliente salvarCliente(Cliente cliente) {
        ClienteEntity clienteEntity = clienteEntityMapper.toClienteEntity(cliente);
        ClienteEntity clienteEntitySalvo = clienteRepository.save(clienteEntity);
        return clienteEntityMapper.toCliente(clienteEntitySalvo);
    }

    @Override
    public Optional<Cliente> consultarCliente(String cpf) {
        Optional<ClienteEntity> clienteEntity = clienteRepository.findById(cpf);

        if (clienteEntity.isEmpty()) {
            return Optional.empty();
        }

        Cliente cliente = clienteEntityMapper.toCliente(clienteEntity.get());
        return Optional.of(cliente);
    }

    @Override
    public Optional<List<Cliente>> listar() {
        var clientesEntity = clienteRepository.findAll();
        var clientes = clientesEntity.stream()
                .map(clienteEntityMapper::toCliente)
                .toList();
        return Optional.of(clientes);
    }

    @Override
    public void deletar(String cpf) {
        clienteRepository.deleteById(cpf);
    }
}
