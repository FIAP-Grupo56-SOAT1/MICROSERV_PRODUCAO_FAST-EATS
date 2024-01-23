package br.com.fiap.fasteats.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDate;

@Data
@Builder
@Document(collection = "cozinha")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cozinha {

    @Id
    private String CozinhaId;
    private LocalDate  DataEntrada;
    private LocalDate DataSaida;
    private Long idPedido;

    private Long statusId;
    private String statusNome;




}
