package br.com.fiap.fasteats.dataprovider.client;

import br.com.fiap.fasteats.core.domain.model.Pedido;
import br.com.fiap.fasteats.dataprovider.client.response.PedidoResponse;
import br.com.fiap.fasteats.dataprovider.repository.entity.PedidoEntity;

import java.util.List;
import java.util.Optional;

public interface PedidoIntegration {
    PedidoResponse consultar(Long id);
    void atualizarStatus(Long id, Long idStatus);
    PedidoResponse findById(Long id);
    List<PedidoResponse> findAll();
    List<PedidoResponse> consultarPedidoAndamento(Long id);
    List<PedidoResponse> listarPedidosAndamento();
    PedidoResponse saveAndFlush(PedidoEntity pedidoEntity);

    void cancelaPedido(Long id);

    void finalizarPedido(Long id);

    void pronto(Long id);

    void recebido(Long id);
}
