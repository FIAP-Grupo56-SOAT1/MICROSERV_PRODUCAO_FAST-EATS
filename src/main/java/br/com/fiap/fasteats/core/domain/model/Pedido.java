package br.com.fiap.fasteats.core.domain.model;

import java.time.LocalDateTime;

public class Pedido {
    private Long id;
    private String statusPedido;
    private LocalDateTime dataHoraCriado;
    private LocalDateTime dataHoraRecebimento;
    private LocalDateTime dataHoraFinalizado;

    public Pedido() {
    }

    public Pedido(Long id, String statusPedido, LocalDateTime dataHoraCriado, LocalDateTime dataHoraRecebimento, LocalDateTime dataHoraFinalizado) {
        this.id = id;
        this.statusPedido = statusPedido;
        this.dataHoraCriado = dataHoraCriado;
        this.dataHoraRecebimento = dataHoraRecebimento;
        this.dataHoraFinalizado = dataHoraFinalizado;
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

    public LocalDateTime getDataHoraCriado() {
        return dataHoraCriado;
    }

    public void setDataHoraCriado(LocalDateTime dataHoraCriado) {
        this.dataHoraCriado = dataHoraCriado;
    }

    public LocalDateTime getDataHoraRecebimento() {
        return dataHoraRecebimento;
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
}
