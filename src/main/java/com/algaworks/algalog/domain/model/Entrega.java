package com.algaworks.algalog.domain.model;

import com.algaworks.algalog.domain.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //O ConvertGroup faz uma conversão do "Default.class" (do beanValidation) para outra classe de validação
    //Nesse caso, para minha própria "interface" de validação
    //Ele ignora os outros atributos (de cliente) porque não especificamos na classe Cliente
    @Valid
    @ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
    @NotNull
    @ManyToOne
    private Cliente cliente;

    @Valid
    @NotNull
    @Embedded //A classe Destinatário está embutida na classe Entrega
    private Destinatario destinatario;

    @NotNull
    private BigDecimal taxa;

    //A anotação @JsonProperty define o acesso aos atributos,
    // aqui ela impede que o consumidor passe valores para esses campos.
    // Eles devem ser gerados pelo sistema
    //Se o consumidor da API tentar inserir, vai retornar um null
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(EnumType.STRING)//String ele pega o texto lá do status, se for int ele pega por numero (1,2,3)
    private StatusEntrega status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    //private LocalDateTime dataPedido;
    private OffsetDateTime dataPedido; //Esse configura melhor. Deve ser mudado em todos os lugares em que aparece

    @JsonProperty(access = JsonProperty.Access.READ_ONLY) //Apenas leitura
    //private LocalDateTime dataFinalizacao;
    private OffsetDateTime dataFinalizacao;

}
