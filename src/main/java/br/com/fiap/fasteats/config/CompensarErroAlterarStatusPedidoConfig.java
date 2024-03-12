package br.com.fiap.fasteats.config;

import br.com.fiap.fasteats.core.usecase.impl.CompensarErroAlterarStatusPedidoUseCase;
import br.com.fiap.fasteats.dataprovider.CozinhaPedidoAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CompensarErroAlterarStatusPedidoConfig {
    @Bean
    public CompensarErroAlterarStatusPedidoUseCase compensarErroAlterarStatusPedidoUseCase(CozinhaPedidoAdapter cozinhaPedidoAdapter) {
        return new CompensarErroAlterarStatusPedidoUseCase(cozinhaPedidoAdapter);
    }
}
