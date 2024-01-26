package br.com.fiap.fasteats.core.validator;


public interface AlterarPedidoStatusValidator {
    void validarRecebido(Long pedidoId);

    void validarEmPreparo(Long pedidoId);

    void validarPronto(Long pedidoId);

    void validarFinalizado(Long pedidoId);
}
