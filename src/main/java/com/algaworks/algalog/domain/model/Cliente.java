package com.algaworks.algalog.domain.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Cliente {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Mesmo sem a anotação ele cria a coluna
    @NotBlank
    @Size(max = 60) //60 caracteres. Tem que ser condizente com o bd
    private String nome;

    @NotBlank
    @Email //Verifica sintaxe do email
    @Size(max = 255)
    private String email;

    //É preciso especificar, já que o bd chama essa coluna de fone
    //Mas, pode só mudar telefone para fone aqui que dá certo também
    @NotBlank
    @Size(max = 20)
    @Column(name = "fone")
    private String telefone;

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cliente cliente = (Cliente) o;
        return id != null && Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }*/
}
