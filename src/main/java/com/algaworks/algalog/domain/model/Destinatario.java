package com.algaworks.algalog.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable //Indicando que a classe pode ser usada como embeded em alguma entidade
public class Destinatario {

    @Column(name = "destinatario_nome")
    private String nome;

    @Column(name = "destinatario_logradouro")
    private String logradouro;

    @Column(name = "destinatario_numero")
    private String numero;

    //Lá no migration, pode remover o not null, precisa apenas refazer tudo ali.
    // Complementos não são obrigatórios
    @Column(name = "destinatario_complemento")
    private String complemento;

    @Column(name = "destinatario_bairro")
    private String bairro;

}
