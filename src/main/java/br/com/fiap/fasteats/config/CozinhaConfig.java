package br.com.fiap.fasteats.config;

import br.com.fiap.fasteats.core.usecase.impl.AlterarPedidoStatusUseCase;
import br.com.fiap.fasteats.core.usecase.impl.CozinhaPedidoUseCase;
import br.com.fiap.fasteats.dataprovider.CozinhaPedidoAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CozinhaConfig {
    @Bean
    public CozinhaPedidoUseCase cozinhaPedidoUseCase(CozinhaPedidoAdapter cozinhaAdapter,
                                                     AlterarPedidoStatusUseCase alterarPedidoStatusUseCase) {
        return new CozinhaPedidoUseCase(cozinhaAdapter, alterarPedidoStatusUseCase);
    }
}
