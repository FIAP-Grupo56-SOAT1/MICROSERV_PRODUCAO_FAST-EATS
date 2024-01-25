package br.com.fiap.fasteats.dataprovider.client.service;

import br.com.fiap.fasteats.core.domain.exception.PedidoNotFound;
import br.com.fiap.fasteats.core.domain.exception.StatusPedidoNotFound;
import br.com.fiap.fasteats.dataprovider.client.PedidoIntegration;
import br.com.fiap.fasteats.dataprovider.client.response.PedidoResponse;
import br.com.fiap.fasteats.dataprovider.repository.entity.PedidoEntity;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PedidoIntegrationImpl implements PedidoIntegration {
    private final Logger logger = LoggerFactory.getLogger(PedidoIntegrationImpl.class);

    private final RestTemplate restTemplate;

    @Value("${URL_PEDIDO_SERVICE}")
    private String URL_BASE;

    private final String URI = "/pedidos";

    @Override
    public PedidoResponse consultar(Long id) {
        try {
            PedidoResponse pedidoResponse =
                    restTemplate.getForObject(URL_BASE + URI + "/{id}", PedidoResponse.class, id);

            return pedidoResponse;
        } catch (Exception ex) {
            logger.error("Erro retorno microservice pedido ", ex.getCause());
            throw new PedidoNotFound("Erro retorno microservice pedido " + ex.getMessage());
        }
    }

    @Override
    public void atualizarStatus(Long id, Long idStatus) {
        try {
            restTemplate.put(URL_BASE + URI + "/{id}/status/{idStatus}", null, id, idStatus);
        } catch (Exception ex) {
            logger.error("Erro atualizar status microservice pedido ", ex.getCause());
            throw new PedidoNotFound("Erro atualizar status microservice pedido " + ex.getMessage());
        }
    }

    @Override
    public PedidoResponse findById(Long id) {
        try {
            PedidoResponse pedidoResponse =
                    restTemplate.getForObject(URL_BASE + URI + "/{id}", PedidoResponse.class, id);

            return pedidoResponse;
        } catch (Exception ex) {
            logger.error("Erro retorno microservice pedido ", ex.getCause());
            throw new StatusPedidoNotFound("Erro retorno microservice pedido " + ex.getMessage());
        }
    }

    @Override
    public List<PedidoResponse> findAll() {
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    URL_BASE + URI + "/",
                    HttpMethod.GET,
                    null,
                    String.class);

            String jsonResponse = responseEntity.getBody();

            if (jsonResponse != null && !jsonResponse.isEmpty()) {
                List<PedidoResponse> pedidoResponseList = restTemplate.getForObject(
                        URL_BASE + URI + "/",
                        List.class);

                return  new ArrayList<PedidoResponse>();
            } else {
                // Log para identificar se a resposta está vazia
                logger.warn("A resposta do microservice de pedido está vazia ou nula.");
                return  new ArrayList<PedidoResponse>();
            }
        } catch (Exception ex) {
            // Log detalhado para investigar o problema
            logger.error("Erro ao obter dados do microservice de pedido ", ex);
            throw new StatusPedidoNotFound("Erro ao obter dados do microservice de pedido: " + ex.getMessage());
        }
    }

    @Override
    public List<PedidoResponse> consultarPedidoAndamento(Long id) {
        return null;
    }

    @Override
    public List<PedidoResponse> listarPedidosAndamento() {
        return null;
    }

    @Override
    public PedidoResponse saveAndFlush(PedidoEntity pedidoEntity) {
        try {
            PedidoResponse responseEntity = restTemplate.postForObject(URL_BASE + URI + "/{idPedido}/saveAndFlush",
                    pedidoEntity, PedidoResponse.class, pedidoEntity.getId());
            System.out.println("Resposta da requisição POST com path param: " + responseEntity);

            return responseEntity;

        } catch (Exception ex) {
            logger.error("Erro retorno microservice cozinha ", ex.getCause());
            throw new PedidoNotFound("Erro retorno microservice cozinha " + ex.getMessage());
        }
    }

    @Override
    public void cancelaPedido(Long id) {
        try {
            restTemplate.put(URL_BASE + URI + "/{id}/cancelar",null, id);
        } catch (Exception ex) {
            logger.error("Erro atualizar status microservice pedido ", ex.getCause());
            throw new PedidoNotFound("Erro atualizar status microservice pedido " + ex.getMessage());
        }
    }

    @Override
    public void finalizarPedido(Long id) {
        try {
            restTemplate.put(URL_BASE + URI + "/{id}/finalizar",null, id);
        } catch (Exception ex) {
            logger.error("Erro atualizar status microservice pedido ", ex.getCause());
            throw new PedidoNotFound("Erro atualizar status microservice pedido " + ex.getMessage());
        }
    }

    @Override
    public void pronto(Long id) {
        try {
            restTemplate.put(URL_BASE + URI + "/{id}/pronto",null, id);
        } catch (Exception ex) {
            logger.error("Erro atualizar status microservice pedido ", ex.getCause());
            throw new PedidoNotFound("Erro atualizar status microservice pedido " + ex.getMessage());
        }
    }

    @Override
    public void recebido(Long id) {
        try {
            restTemplate.put(URL_BASE + URI + "/{id}/recebido",null, id);
        } catch (Exception ex) {
            logger.error("Erro atualizar status microservice pedido ", ex.getCause());
            throw new PedidoNotFound("Erro atualizar status microservice pedido " + ex.getMessage());
        }
    }
}
