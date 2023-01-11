package com.algaworks.algalog.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @Embedded //A classe Destinatário está embutida na classe Entrega
    private Destinatario destinatario;

    private BigDecimal taxa;

    //A anotação @JsonProperty define o acesso aos atributos,
    // aqui ela impede que o consumidor passe valores para esses campos.
    // Eles devem ser gerados pelo sistema
    //Se o consumidor da API tentar inserir, vai retornar um null
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(EnumType.STRING)//String ele pega o texto lá do status, se for int ele pega por numero (1,2,3)
    private StatusEntrega status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime dataPedido;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY) //Apenas leitura
    private LocalDateTime dataFinalizacao;

}
