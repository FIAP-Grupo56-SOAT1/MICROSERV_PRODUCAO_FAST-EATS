package br.com.fiap.fasteats.core.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class CozinhaPedido {
    private String cozinhaId;
    private LocalDateTime dataRecebimentoDoPedido;
    private LocalDateTime dataInicioPreparo;
    private LocalDateTime dataFinalizacaoPreparo;
    private LocalDateTime dataEntregaPedido;
    private Long idPedido;
    private String statusPedido;
    public String processoAtual;

    public CozinhaPedido() {
    }

    public CozinhaPedido(
                         LocalDateTime dataRecebimentoDoPedido,
                         LocalDateTime dataInicioPreparo,
                         LocalDateTime dataFinalizacaoPreparo,
                         LocalDateTime dataEntregaPedido,
                         Long idPedido,
                         String statusPedido,
                         String processoAtual) {
        this.dataRecebimentoDoPedido = dataRecebimentoDoPedido;
        this.dataInicioPreparo = dataInicioPreparo;
        this.dataFinalizacaoPreparo = dataFinalizacaoPreparo;
        this.dataEntregaPedido = dataEntregaPedido;
        this.idPedido = idPedido;
        this.statusPedido = statusPedido;
        this.processoAtual = processoAtual;
    }

    public String getCozinhaId() {
        return cozinhaId;
    }

    public void setCozinhaId(String cozinhaId) {
        this.cozinhaId = cozinhaId;
    }

    public LocalDateTime getDataRecebimentoDoPedido() {
        return dataRecebimentoDoPedido;
    }

    public void setDataRecebimentoDoPedido(LocalDateTime dataRecebimentoDoPedido) {
        this.dataRecebimentoDoPedido = dataRecebimentoDoPedido;
    }

    public LocalDateTime getDataInicioPreparo() {
        return dataInicioPreparo;
    }

    public void setDataInicioPreparo(LocalDateTime dataInicioPreparo) {
        this.dataInicioPreparo = dataInicioPreparo;
    }

    public LocalDateTime getDataFinalizacaoPreparo() {
        return dataFinalizacaoPreparo;
    }

    public void setDataFinalizacaoPreparo(LocalDateTime dataFinalizacaoPreparo) {
        this.dataFinalizacaoPreparo = dataFinalizacaoPreparo;
    }

    public LocalDateTime getDataEntregaPedido() {
        return dataEntregaPedido;
    }

    public void setDataEntregaPedido(LocalDateTime dataEntregaPedido) {
        this.dataEntregaPedido = dataEntregaPedido;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public String getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }

    public String getProcessoAtual() {
        return processoAtual;
    }

    public void setProcessoAtual(String processoAtual) {
        this.processoAtual = processoAtual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CozinhaPedido that = (CozinhaPedido) o;
        return Objects.equals(cozinhaId, that.cozinhaId) && Objects.equals(dataRecebimentoDoPedido, that.dataRecebimentoDoPedido) && Objects.equals(dataInicioPreparo, that.dataInicioPreparo) && Objects.equals(dataFinalizacaoPreparo, that.dataFinalizacaoPreparo) && Objects.equals(dataEntregaPedido, that.dataEntregaPedido) && Objects.equals(idPedido, that.idPedido) && Objects.equals(statusPedido, that.statusPedido) && Objects.equals(processoAtual, that.processoAtual);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cozinhaId, dataRecebimentoDoPedido, dataInicioPreparo, dataFinalizacaoPreparo, dataEntregaPedido, idPedido, statusPedido, processoAtual);
    }

    @Override
    public String toString() {
        return "CozinhaPedido{" +
                "cozinhaId='" + cozinhaId + '\'' +
                ", dataRecebimentoDoPedido=" + dataRecebimentoDoPedido +
                ", dataInicioPreparo=" + dataInicioPreparo +
                ", dataFinalizacaoPreparo=" + dataFinalizacaoPreparo +
                ", dataEntregaPedido=" + dataEntregaPedido +
                ", idPedido=" + idPedido +
                ", statusPedido='" + statusPedido + '\'' +
                ", processoAtual='" + processoAtual + '\'' +
                '}';
    }
}
