package com.algaworks.algalog.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL) //Se o campo for nulo, ele não inclui na representação
// (no postman não aparece o campo nulo quando uma excessão é disparada)
@Getter
@Setter
public class Problema {
    private int status; //Opcional, já que já tem o http status lá postman
    private LocalDateTime dataHora;
    private String titulo;
    private List<Campo> campos;

    @AllArgsConstructor
    @Getter
    public static class Campo{
        private String nome; //do campo
        private String mensagem;
    }

}
