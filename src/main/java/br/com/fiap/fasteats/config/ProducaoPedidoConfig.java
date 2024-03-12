package br.com.fiap.fasteats.config;

import br.com.fiap.fasteats.core.usecase.impl.ProducaoPedidoUseCase;
import br.com.fiap.fasteats.dataprovider.CozinhaPedidoAdapter;
import br.com.fiap.fasteats.dataprovider.IniciarProcessoAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducaoPedidoConfig {
    @Bean
    public ProducaoPedidoUseCase producaoPedidoUseCase(IniciarProcessoAdapter iniciarProcessoAdapter,
                                                       CozinhaPedidoAdapter cozinhaPedidoAdapter) {
        return new ProducaoPedidoUseCase(iniciarProcessoAdapter, cozinhaPedidoAdapter);
    }
}
