package br.com.fiap.fasteats.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Date;
import java.util.List;
@Data
@Builder
@Document(collection = "person")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cozinha {

    @Id
    private String CozinhaId;
    private Date DataEntrada;
    private Date DataSaida;
    private Integer idPedido;
}
