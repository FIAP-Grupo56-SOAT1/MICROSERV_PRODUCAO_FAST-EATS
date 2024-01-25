package br.com.fiap.fasteats.core.domain.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;



public class Pedido {
    private Long id;
    private String statusPedido;
    private Long idStatusPedido;
    private Long idPedido;
    private LocalDateTime dataHoraCriado;
    private LocalDateTime dataHoraRecebimento;
    private LocalDateTime dataHoraFinalizado;
    private double valor;
    private String qrCode;

    public Pedido(){

    }

    public Pedido(Long id,
                  String statusPedido,
                  Long statusPedidoId,
                  Long idPedido,
                  LocalDateTime dataHoraCriado,
                  LocalDateTime dataHoraRecebimento,
                  LocalDateTime dataHoraFinalizado,
                  double valor,
                  String qrCode,
                  String urlPagamento) {
        this.id = id;
        this.statusPedido = statusPedido;
        this.idStatusPedido = statusPedidoId;
        this.idPedido = idPedido;
        this.dataHoraCriado = dataHoraCriado;
        this.dataHoraRecebimento = dataHoraRecebimento;
        this.dataHoraFinalizado = dataHoraFinalizado;
        this.valor = valor;
        this.qrCode = qrCode;
        this.urlPagamento = urlPagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }

    public Long getStatusPedidoId() {
        return idStatusPedido;
    }

    public Long getIdStatusPedido() {
        return idStatusPedido;
    }

    public void setIdStatusPedido(Long idStatusPedido) {
        this.idStatusPedido = idStatusPedido;
    }

    public void setStatusPedidoId(Long statusPedidoId) {
        this.idStatusPedido = statusPedidoId;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public LocalDateTime getDataHoraCriado() {
        return dataHoraCriado;
    }

    public void setDataHoraCriado(LocalDateTime dataHoraCriado) {
        this.dataHoraCriado = dataHoraCriado;
    }

    public void setDataHoraRecebimento(LocalDateTime dataHoraRecebimento) {
        this.dataHoraRecebimento = dataHoraRecebimento;
    }

    public LocalDateTime getDataHoraFinalizado() {
        return dataHoraFinalizado;
    }

    public void setDataHoraFinalizado(LocalDateTime dataHoraFinalizado) {
        this.dataHoraFinalizado = dataHoraFinalizado;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getUrlPagamento() {
        return urlPagamento;
    }

    public void setUrlPagamento(String urlPagamento) {
        this.urlPagamento = urlPagamento;
    }

    private String urlPagamento;




    public LocalDateTime getDataHoraRecebimento() {
        return dataHoraRecebimento;
    }



    public boolean isIdentificaCliente() {
        return false;
    }


    public void setTempoEspera(String s) {
    }
}
