package br.com.fiap.fasteats.dataprovider.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Pagamento")
@Table(name = "pagamentos")
@EqualsAndHashCode(of = "id")
public class PagamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "formapagamentoid_fk")
    private FormaPagamentoEntity formaPagamento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "statuspagamentoid_fk")
    private StatusPagamentoEntity statusPagamento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pedidoid_fk")
    private PedidoEntity pedido;

    @Column(nullable = false, name = "datahoracriado")
    private LocalDateTime dataHoraCriado;

    @Column(nullable = false, name = "datahoraprocessamento")
    private LocalDateTime dataHoraProcessamento;

    @Column(nullable = false, name = "datahorafinalizado")
    private LocalDateTime dataHoraFinalizado;

    @Column(nullable = false, name = "idpagamentoexterno")
    private Long idPagamentoExterno;

    @Column(nullable = false, name = "urlpagamento")
    private String urlPagamento;

    @Column(nullable = false, name = "qrcode")
    private String qrCode;
}
