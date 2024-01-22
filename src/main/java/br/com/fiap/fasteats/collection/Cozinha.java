package br.com.fiap.fasteats.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Data
@Builder
@Document(collection = "person")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cozinha {

    @Id
    private String CozinhaId;
    private LocalDate  DataEntrada;
    private LocalDate DataSaida;
    private Long idPedido;

    private Long statusId;

    public Cozinha(LocalDate dataEntrada,
                   Long idPedido,
                   Long statusId,
                   String statusNome) {
        DataEntrada = dataEntrada;
        this.idPedido = idPedido;
        this.statusId = statusId;
        this.statusNome = statusNome;
    }

    private String statusNome;
}
