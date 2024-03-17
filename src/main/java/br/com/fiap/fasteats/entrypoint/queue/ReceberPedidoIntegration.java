package br.com.fiap.fasteats.entrypoint.queue;

import br.com.fiap.fasteats.core.usecase.ProducaoPedidoInputPort;
import br.com.fiap.fasteats.dataprovider.client.request.CozinhaErroReceberPedidoRequest;
import br.com.fiap.fasteats.dataprovider.client.response.ReceberPedidoResponse;
import com.google.gson.Gson;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceberPedidoIntegration {
    @Value("${sqs.queue.cozinha.receber.pedido}")
    private String filaCozinhaReceberPedido;
    @Value("${sqs.queue.cozinha.erro.receber.pedido}")
    private String filaCozinhaErroReceberPedido;
    private final SqsTemplate sqsTemplate;
    private final ProducaoPedidoInputPort producaoPedidoInputPort;

    @SqsListener("${sqs.queue.cozinha.receber.pedido}")
    public void receber(String mensagem) {
        ReceberPedidoResponse pedidoResponse = new Gson().fromJson(mensagem, ReceberPedidoResponse.class);
        Long pedidoId = pedidoResponse.getPedidoId();
        try {
            producaoPedidoInputPort.pendente(pedidoId);
            producaoPedidoInputPort.receber(pedidoId);
            log.info(String.format("Pedido %d recebido da fila %s com sucesso!", pedidoId, filaCozinhaReceberPedido));
        } catch (Exception ex) {
            String logErro = String.format("Erro ao processar receber pedido para o pedido %d, mensagem da fila %s: {}", pedidoId, filaCozinhaReceberPedido);
            log.error(logErro, ex.getMessage());
            CozinhaErroReceberPedidoRequest cozinhaErroReceberPedidoRequest = new CozinhaErroReceberPedidoRequest(pedidoId);
            String mensagemErro = new Gson().toJson(cozinhaErroReceberPedidoRequest);
            sqsTemplate.send(filaCozinhaErroReceberPedido, mensagemErro);
        }
    }
}
