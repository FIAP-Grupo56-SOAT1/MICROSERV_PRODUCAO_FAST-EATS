package br.com.fiap.fasteats.dataprovider.client.service;

import br.com.fiap.fasteats.core.domain.exception.StatusPedidoNotFound;
import br.com.fiap.fasteats.dataprovider.client.StatusPedidoIntegration;
import br.com.fiap.fasteats.dataprovider.client.response.StatusPedidoResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StatusPedidoIntegrationImpl implements StatusPedidoIntegration {
    private final Logger logger = LoggerFactory.getLogger(StatusPedidoIntegrationImpl.class);
    private final RestTemplate restTemplate;
    @Value("${URL_PEDIDO_SERVICE}")
    private String URL_BASE;
    private final String PATH = "/status-pedidos";


    @Override
    public Optional<StatusPedidoResponse> consultarPorNome(String nome) {
        try {
            StatusPedidoResponse statusPedidoResponse =
                    restTemplate.getForObject(URL_BASE + PATH + "/consultar-por-nome/{nome}", StatusPedidoResponse.class, nome);

            return Optional.ofNullable(statusPedidoResponse);
        } catch (Exception ex) {
            logger.error("Erro retorno microservice pedido ", ex.getCause());
            throw new StatusPedidoNotFound("Erro retorno microservice status pedido " + ex.getMessage());
        }
    }
}
