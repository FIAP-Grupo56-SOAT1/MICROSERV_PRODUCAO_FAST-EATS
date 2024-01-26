package br.com.fiap.fasteats.config;

import br.com.fiap.fasteats.core.usecase.impl.AlterarPedidoStatusUseCase;
import br.com.fiap.fasteats.core.validator.impl.AlterarPedidoStatusValidatorImpl;
import br.com.fiap.fasteats.dataprovider.AlterarPedidoStatusAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlterarPedidoStatusConfig {
    @Bean
    public AlterarPedidoStatusUseCase alterarPedidoStatusUseCase(AlterarPedidoStatusAdapter alterarPedidoStatusAdapter,
                                                                 AlterarPedidoStatusValidatorImpl alterarPedidoStatusValidator) {
        return new AlterarPedidoStatusUseCase(alterarPedidoStatusAdapter, alterarPedidoStatusValidator);
    }
}
