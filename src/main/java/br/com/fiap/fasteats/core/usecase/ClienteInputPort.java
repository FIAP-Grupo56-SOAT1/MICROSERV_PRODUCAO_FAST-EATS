package br.com.fiap.fasteats.core.usecase;

import br.com.fiap.fasteats.core.domain.model.Cliente;

import java.util.List;

public interface ClienteInputPort {

    Cliente consultar(String cpf);
    List<Cliente> listar();
    Boolean clienteExiste(String cpf);

    void validarCliente(Cliente cliente);
}
