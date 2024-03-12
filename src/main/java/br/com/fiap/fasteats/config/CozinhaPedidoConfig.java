package br.com.fiap.fasteats.config;

import br.com.fiap.fasteats.core.usecase.impl.CozinhaPedidoUseCase;
import br.com.fiap.fasteats.dataprovider.CozinhaPedidoAdapter;
import br.com.fiap.fasteats.dataprovider.IniciarProcessoAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CozinhaPedidoConfig {
    @Bean
    public CozinhaPedidoUseCase cozinhaPedidoUseCase(CozinhaPedidoAdapter cozinhaAdapter) {
        return new CozinhaPedidoUseCase(cozinhaAdapter);
    }
}
