package com.algaworks.algalog.api.exceptionhandler;

import com.algaworks.algalog.domain.Exception.NegocioException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

//Aqui tratamos excessões
@AllArgsConstructor //Construtor para MessageSource
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {

        List<Problema.Campo> campos = new ArrayList<>();

        for(ObjectError error : ex.getBindingResult().getAllErrors()){
            String nome = ((FieldError)error).getField(); //É necessário fazer esse cast, já que o objetive error
                                                            //é um objective error

            //String mensagem = error.getDefaultMessage(); //caso não use o messageSource

            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale()); //Pega o local de onde esta sendo executado para colocar os errors em ptBr, nesse caso

            campos.add(new Problema.Campo(nome, mensagem));

        }

        Problema problema = new Problema();
        problema.setStatus(status.value());
        problema.setDataHora(OffsetDateTime.now());
        problema.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");
        problema.setCampos(campos);

        return handleExceptionInternal(ex, problema, headers, status, request);
        //return handleExceptionInternal(ex, "Algo está errado. Tente novamente", headers, status, request);
        //Usa-se o comentário acima caso deseje não retornar um objeto
    }

    @ExceptionHandler(NegocioException.class) //Caso essa esseção seja disparada, esse é o método responsável por tratá-la
    public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request){

        HttpStatus status = HttpStatus.BAD_REQUEST;

        Problema problema = new Problema();
        problema.setStatus(status.value());
        problema.setDataHora(OffsetDateTime.now());
        problema.setTitulo(ex.getMessage());

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }
}
