package br.com.fiap.fasteats.dataprovider.client.service;

import br.com.fiap.fasteats.core.domain.exception.StatusPedidoNotFound;
import br.com.fiap.fasteats.dataprovider.client.StatusPagamentoIntegration;
import br.com.fiap.fasteats.dataprovider.client.response.StatusPagamentoResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StatusPagamentoIntegrationImpl implements StatusPagamentoIntegration {
    private final Logger logger = LoggerFactory.getLogger(StatusPagamentoIntegrationImpl.class);

    private final RestTemplate restTemplate;

    @Value("${URL_PAGAMENTO_SERVICE}")
    private String URL_BASE;

    private final String PATH = "/status-pagamento";

    @Override
    public Optional<StatusPagamentoResponse> consultar(Long id) {
        try {
            StatusPagamentoResponse statusPagamentoResponse =
                    restTemplate.getForObject(URL_BASE + PATH + "/{id}", StatusPagamentoResponse.class, id);

            return Optional.ofNullable(statusPagamentoResponse);
        } catch (Exception ex) {
            logger.error("Erro retorno microservice statusPagamento ", ex.getCause());
            throw new StatusPedidoNotFound("Erro retorno microservice statusPagamento " + ex.getMessage());
        }
    }

    @Override
    public Optional<List<StatusPagamentoResponse>> listar() {
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    URL_BASE + PATH + "/",
                    HttpMethod.GET,
                    null,
                    String.class);

            String jsonResponse = responseEntity.getBody();

            if (jsonResponse != null && !jsonResponse.isEmpty()) {
                List<StatusPagamentoResponse> statusPagamentoResponseList = restTemplate.getForObject(
                        URL_BASE + PATH + "/",
                        List.class);  // Aqui você obtém diretamente uma lista de objetos

                return Optional.of(statusPagamentoResponseList);
            } else {
                // Log para identificar se a resposta está vazia
                logger.warn("A resposta do microservice de statusPagamento está vazia ou nula.");
                return Optional.empty();
            }
        } catch (Exception ex) {
            // Log detalhado para investigar o problema
            logger.error("Erro ao obter dados do microservice de statusPagamento ", ex);
            throw new StatusPedidoNotFound("Erro ao obter dados do microservice de statusPagamento: " + ex.getMessage());
        }
    }

    @Override
    public Optional<StatusPagamentoResponse> consultarPorNome(String nome) {
        try {
            StatusPagamentoResponse statusPagamentoResponse =
                    restTemplate.getForObject(URL_BASE + PATH + "/consultar-por-nome/{nome}", StatusPagamentoResponse.class, nome);

            return Optional.ofNullable(statusPagamentoResponse);
        } catch (Exception ex) {
            logger.error("Erro retorno microservice statusPagamento ", ex.getCause());
            throw new StatusPedidoNotFound("Erro retorno microservice statusPagamento " + ex.getMessage());
        }
    }
}
