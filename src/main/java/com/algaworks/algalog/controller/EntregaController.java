package com.algaworks.algalog.controller;

import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import com.algaworks.algalog.domain.service.SolicitacaoEntregaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private EntregaRepository entregaRepository;

    private SolicitacaoEntregaService solicitacaoEntregaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega solicitar(@Valid @RequestBody Entrega entrega){
        return solicitacaoEntregaService.solicitar(entrega);
    }

    @GetMapping
    public List<Entrega> listar(){
        return entregaRepository.findAll();
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<Entrega> buscar(@PathVariable Long entregaId){
        return entregaRepository.findById(entregaId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
