package br.com.fiap.fasteats.dataprovider.client.service;

import br.com.fiap.fasteats.dataprovider.client.PedidoIntegration;
import br.com.fiap.fasteats.dataprovider.client.exception.AwsSQSException;
import br.com.fiap.fasteats.dataprovider.client.exception.MicroservicoPedidoException;
import br.com.fiap.fasteats.dataprovider.client.request.AtualizarStatusPedidoRequest;
import br.com.fiap.fasteats.dataprovider.client.response.PedidoResponse;
import com.google.gson.Gson;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static br.com.fiap.fasteats.core.constants.StatusPedidoConstants.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class PedidoIntegrationImpl implements PedidoIntegration {
    @Value("${sqs.queue.pedido.recebido}")
    private String filaPedidoRecebido;
    @Value("${sqs.queue.pedido.em-preparo}")
    private String filaPedidoEmPreparo;
    @Value("${sqs.queue.pedido.pronto}")
    private String filaPedidoPronto;
    @Value("${sqs.queue.pedido.finalizado}")
    private String filaPedidoFinalizado;
    @Value("${URL_PEDIDO_SERVICE}")
    private String URL_BASE;
    private final String URI = "/pedidos";
    private final RestTemplate restTemplate;
    private final SqsTemplate sqsTemplate;

    @Override
    public Optional<PedidoResponse> consultar(Long id) {
        try {
            String url = String.format("%s%s/%d", URL_BASE, URI, id);
            PedidoResponse pedidoResponse = restTemplate.getForObject(url, PedidoResponse.class, id);
            return Optional.ofNullable(pedidoResponse);
        } catch (Exception ex) {
            String resposta = String.format("Erro na comunicação com o microserviço Pedido: %s", ex.getMessage());
            log.error(resposta);
            throw new MicroservicoPedidoException(resposta);
        }
    }

    @Override
    public void pedidoRecebido(Long pedidoId) {
        enviarParaFilaPedidoStatus(pedidoId, filaPedidoRecebido, STATUS_PEDIDO_RECEBIDO);
    }

    @Override
    public void pedidoEmPreparo(Long pedidoId) {
        enviarParaFilaPedidoStatus(pedidoId, filaPedidoEmPreparo, STATUS_PEDIDO_EM_PREPARO);
    }

    @Override
    public void pedidoPronto(Long pedidoId) {
        enviarParaFilaPedidoStatus(pedidoId, filaPedidoPronto, STATUS_PEDIDO_PRONTO);
    }

    @Override
    public void pedidoFinalizado(Long pedidoId) {
        enviarParaFilaPedidoStatus(pedidoId, filaPedidoFinalizado, STATUS_PEDIDO_FINALIZADO);
    }

    private void enviarParaFilaPedidoStatus(Long pedidoId, String nomeFila, String status) {
        try {
            AtualizarStatusPedidoRequest request = new AtualizarStatusPedidoRequest(pedidoId);
            String mensagem = new Gson().toJson(request);
            sqsTemplate.send(nomeFila, mensagem);
            String mensagemLog = String.format("Atualizar Status do Pedido %d para %s enviado para fila de %s com sucesso!", pedidoId, status, nomeFila);
            log.info(mensagemLog);
        } catch (Exception ex) {
            String resposta = String.format("Erro na comunicação com a fila %s ao tentar altera o status do pedido %d: %s", nomeFila, pedidoId, ex.getMessage());
            log.error(resposta);
            throw new AwsSQSException(resposta);
        }
    }
}
