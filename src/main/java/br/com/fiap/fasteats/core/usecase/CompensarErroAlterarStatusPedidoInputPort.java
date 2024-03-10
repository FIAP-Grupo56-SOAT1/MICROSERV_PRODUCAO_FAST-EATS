package br.com.fiap.fasteats.core.usecase;

public interface CompensarErroAlterarStatusPedidoInputPort {
    void pendente(Long pedidoId);

    void recebido(Long pedidoId);

    void emPreparo(Long pedidoId);

    void pronto(Long pedidoId);
}
