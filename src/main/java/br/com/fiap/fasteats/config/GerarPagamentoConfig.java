package br.com.fiap.fasteats.config;


import br.com.fiap.fasteats.core.usecase.impl.pagamento.FormaPagamentoUseCase;
import br.com.fiap.fasteats.core.usecase.impl.pagamento.GerarPagamentoUseCase;
import br.com.fiap.fasteats.core.usecase.impl.pagamento.MetodoPagamentoUseCase;
import br.com.fiap.fasteats.core.validator.impl.GerarPagamentoValidatorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GerarPagamentoConfig {
    @Bean
    public GerarPagamentoUseCase gerarPagamentoUseCase(FormaPagamentoUseCase formaPagamentoUseCase,
                                                       GerarPagamentoValidatorImpl gerarPagamentoValidatorImpl,
                                                       MetodoPagamentoUseCase metodoPagamentoUseCase) {
        return new GerarPagamentoUseCase(formaPagamentoUseCase, gerarPagamentoValidatorImpl, metodoPagamentoUseCase);
    }
}
