package br.com.fiap.fasteats.config.validator;

import br.com.fiap.fasteats.core.validator.impl.AlterarPedidoStatusValidatorImpl;
import br.com.fiap.fasteats.dataprovider.PedidoAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlterarPedidoStatusValidatorConfig {
    @Bean
    public AlterarPedidoStatusValidatorImpl alterarPedidoStatusValidatorImpl(PedidoAdapter pedidoAdapter) {
        return new AlterarPedidoStatusValidatorImpl(pedidoAdapter);
    }
}
