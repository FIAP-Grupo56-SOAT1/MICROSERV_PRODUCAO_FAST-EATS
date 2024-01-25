package br.com.fiap.fasteats.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "cozinha")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cozinha {

    @Id
    @MongoId(value = FieldType.OBJECT_ID)
    private String cozinhaId;

    private LocalDateTime dataRecebimentoDoPedido;
    private LocalDateTime dataInicioPreparo;
    private LocalDateTime dataFinalizacaoPreparo;
    private LocalDateTime dataEntregaPedido;

    private Long idPedido;

    private Long statusPedidoId;
    private String statusPedido;

    public String processoAtual;
}
