package br.com.fiap.fasteats.dataprovider.client;

public interface AlterarStatusPedidoErroIntegration {
    void erroRecebido(String mensagem);
    void erroEmPreparo(String mensagem);
    void erroPronto(String mensagem);
    void erroFinalizado(String mensagem);
}
