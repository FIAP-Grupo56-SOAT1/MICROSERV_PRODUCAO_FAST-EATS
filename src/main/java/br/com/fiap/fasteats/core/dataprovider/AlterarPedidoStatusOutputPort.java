package br.com.fiap.fasteats.core.dataprovider;

public interface AlterarPedidoStatusOutputPort {
    void recebido(Long pedidoId);

    void emPreparo(Long pedidoId);

    void pronto(Long pedidoId);

    void finalizado(Long pedidoId);
}
