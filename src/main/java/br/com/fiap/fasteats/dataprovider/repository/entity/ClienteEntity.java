package br.com.fiap.fasteats.dataprovider.repository.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "cpf")

public class ClienteEntity {

    private String cpf;
    private String primeiroNome;
    private String ultimoNome;
    private String email;
    private Boolean ativo = true;

    public String getNome() {
        return primeiroNome + " " + ultimoNome;
    }
}
