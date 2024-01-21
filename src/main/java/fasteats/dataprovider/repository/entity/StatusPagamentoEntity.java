package fasteats.dataprovider.repository.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "StatusPagamento")
@Table(name = "statuspagamento")
@EqualsAndHashCode(of = "id")
public class StatusPagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, length = 200)
    private String nome;

    @Column(nullable = true)
    private Boolean ativo = true;

}
