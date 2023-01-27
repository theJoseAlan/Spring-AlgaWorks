package com.algaworks.algalog.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable //Indicando que a classe pode ser usada como embeded em alguma entidade
public class Destinatario {

    @NotBlank
    @Column(name = "destinatario_nome")
    private String nome;

    @NotBlank
    @Column(name = "destinatario_logradouro")
    private String logradouro;

    @NotBlank
    @Column(name = "destinatario_numero")
    private String numero;

    //Lá no migration, pode remover o not null, precisa apenas refazer tudo ali.
    // Complementos não são obrigatórios
    @NotBlank
    @Column(name = "destinatario_complemento")
    private String complemento;

    @NotBlank
    @Column(name = "destinatario_bairro")
    private String bairro;

}
