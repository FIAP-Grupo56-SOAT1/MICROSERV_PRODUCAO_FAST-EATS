package br.com.fiap.fasteats.config;

import br.com.fiap.fasteats.core.usecase.impl.pagamento.CancelarPagamentoExternoUseCase;
import br.com.fiap.fasteats.core.usecase.impl.pagamento.PagamentoUseCase;
import br.com.fiap.fasteats.core.validator.impl.CancelarPagamentoValidatorImpl;
import br.com.fiap.fasteats.dataprovider.MercadoPagoAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CancelarPagamentoExternoConfig {
    @Bean
    public CancelarPagamentoExternoUseCase cancelarPagamentoExternoUseCase(PagamentoUseCase pagamentoUseCase,
                                                                           MercadoPagoAdapter mercadoPagoAdapter,
                                                                           CancelarPagamentoValidatorImpl cancelarPagamentoValidatorImpl) {
        return new CancelarPagamentoExternoUseCase(pagamentoUseCase, mercadoPagoAdapter, cancelarPagamentoValidatorImpl);
    }
}
