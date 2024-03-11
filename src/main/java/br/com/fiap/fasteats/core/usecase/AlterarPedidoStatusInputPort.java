package br.com.fiap.fasteats.core.usecase;

public interface AlterarPedidoStatusInputPort {
    void recebido(Long pedidoId);

    void emPreparo(Long pedidoId);

    void pronto(Long pedidoId);

    void finalizado(Long pedidoId);
}
