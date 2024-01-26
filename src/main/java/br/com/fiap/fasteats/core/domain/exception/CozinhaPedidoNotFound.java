package br.com.fiap.fasteats.core.domain.exception;

public class CozinhaPedidoNotFound extends RuntimeException{
    public CozinhaPedidoNotFound(String mensagem) {
        super(mensagem);
    }
}
