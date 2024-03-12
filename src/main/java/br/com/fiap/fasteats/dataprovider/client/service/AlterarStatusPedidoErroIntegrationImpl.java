package br.com.fiap.fasteats.dataprovider.client.service;

import br.com.fiap.fasteats.core.usecase.CompensarErroAlterarStatusPedidoInputPort;
import br.com.fiap.fasteats.dataprovider.client.AlterarStatusPedidoErroIntegration;
import br.com.fiap.fasteats.dataprovider.client.response.AlterarStatusPedidoErroResponse;
import com.google.gson.Gson;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import static br.com.fiap.fasteats.core.constants.ProcessoCozinhaConstants.*;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(value = "spring.cloud.aws.sqs.enabled", havingValue = "true", matchIfMissing = true)
public class AlterarStatusPedidoErroIntegrationImpl implements AlterarStatusPedidoErroIntegration {
    @Value("${sqs.queue.cozinha.erro.pedido.recebido}")
    private String filaCozinhaErroRecebido;
    @Value("${sqs.queue.cozinha.erro.pedido.em-preparo}")
    private String filaCozinhaErroEmPreparo;
    @Value("${sqs.queue.cozinha.erro.pedido.pronto}")
    private String filaCozinhaErroPronto;
    @Value("${sqs.queue.cozinha.erro.pedido.finalizado}")
    private String filaCozinhaErroFinalizado;
    private final CompensarErroAlterarStatusPedidoInputPort compensarErroAlterarStatusPedidoInputPort;
    private static final String MENSAGEM_SUCESSO = "Pedido %d retornado para o status %s, mensagem da fila %s!";
    private static final String MENSAGEM_ERRO = "Erro ao processar mensagem do pedido %d da fila %s : {}";
    @Override
    @SqsListener("${sqs.queue.cozinha.erro.pedido.recebido}")
    public void erroRecebido(String mensagem) {
        Long pedidoId = pedidoIdFromJson(mensagem);
        try {
            compensarErroAlterarStatusPedidoInputPort.pendente(pedidoId);
            log.info(String.format(MENSAGEM_SUCESSO, pedidoId, STATUS_COZINHA_PENDENTE, filaCozinhaErroRecebido));
        } catch (Exception ex) {
            String mensagemErro = String.format(MENSAGEM_ERRO, pedidoId, filaCozinhaErroRecebido);
            log.error(mensagemErro, ex.getMessage());
            throw ex;
        }
    }

    @Override
    @SqsListener("${sqs.queue.cozinha.erro.pedido.em-preparo}")
    public void erroEmPreparo(String mensagem) {
        Long pedidoId = pedidoIdFromJson(mensagem);
        try {
            compensarErroAlterarStatusPedidoInputPort.recebido(pedidoId);
            log.info(String.format(MENSAGEM_SUCESSO, pedidoId, STATUS_COZINHA_RECEBIDO, filaCozinhaErroEmPreparo));
        } catch (Exception ex) {
            String mensagemErro = String.format(MENSAGEM_ERRO, pedidoId, filaCozinhaErroEmPreparo);
            log.error(mensagemErro, ex.getMessage());
            throw ex;
        }
    }

    @Override
    @SqsListener("${sqs.queue.cozinha.erro.pedido.pronto}")
    public void erroPronto(String mensagem) {
        Long pedidoId = pedidoIdFromJson(mensagem);
        try {
            compensarErroAlterarStatusPedidoInputPort.emPreparo(pedidoId);
            log.info(String.format(MENSAGEM_SUCESSO, pedidoId, STATUS_COZINHA_INICIO_PREPARO, filaCozinhaErroPronto));
        } catch (Exception ex) {
            String mensagemErro = String.format(MENSAGEM_ERRO, pedidoId, filaCozinhaErroPronto);
            log.error(mensagemErro, ex.getMessage());
            throw ex;
        }
    }

    @Override
    @SqsListener("${sqs.queue.cozinha.erro.pedido.finalizado}")
    public void erroFinalizado(String mensagem) {
        Long pedidoId = pedidoIdFromJson(mensagem);
        try {
            compensarErroAlterarStatusPedidoInputPort.pronto(pedidoId);
            log.info(String.format(MENSAGEM_SUCESSO, pedidoId, STATUS_COZINHA_FINALIZANDO_PREPARO, filaCozinhaErroFinalizado));
        } catch (Exception ex) {
            String mensagemErro = String.format(MENSAGEM_ERRO, pedidoId, filaCozinhaErroFinalizado);
            log.error(mensagemErro, ex.getMessage());
            throw ex;
        }
    }

    private Long pedidoIdFromJson(String mensagem) {
        AlterarStatusPedidoErroResponse alterarStatusPedidoErroResponse = new Gson().fromJson(mensagem, AlterarStatusPedidoErroResponse.class);
        return alterarStatusPedidoErroResponse.getPedidoId();
    }
}
