package br.com.fiap.fasteats.dataprovider;

import br.com.fiap.fasteats.core.dataprovider.AlterarPedidoStatusOutputPort;
import br.com.fiap.fasteats.dataprovider.client.PedidoIntegration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlterarPedidoStatusAdapter implements AlterarPedidoStatusOutputPort {
    private final PedidoIntegration pedidoIntegration;

    @Override
    public void recebido(Long pedidoId) {
        pedidoIntegration.pedidoRecebido(pedidoId);
    }

    @Override
    public void emPreparo(Long pedidoId) {
        pedidoIntegration.pedidoEmPreparo(pedidoId);
    }

    @Override
    public void pronto(Long pedidoId) {
        pedidoIntegration.pedidoPronto(pedidoId);
    }

    @Override
    public void finalizado(Long pedidoId) {
        pedidoIntegration.pedidoFinalizado(pedidoId);
    }
}
