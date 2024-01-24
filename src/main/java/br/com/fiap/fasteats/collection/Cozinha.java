package br.com.fiap.fasteats.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;


import java.time.LocalDate;

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
    private LocalDate  dataEntrada;
    private LocalDate dataSaida;
    private Long idPedido;

    private Long statusId;
    private String statusNome;

}
