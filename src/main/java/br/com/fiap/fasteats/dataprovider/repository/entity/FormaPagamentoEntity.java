package br.com.fiap.fasteats.dataprovider.repository.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "FormaPagamento")
@Table(name = "formapagamentos")
@EqualsAndHashCode(of = "id")
public class FormaPagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, length = 200)
    private String nome;
    @Column(nullable = false, name = "externo")
    private boolean externo = false;

    @Column(nullable = true)
    private boolean ativo = true;
}
