package com.algaworks.algalog.controller;

import com.algaworks.algalog.api.model.DestinatarioModel;
import com.algaworks.algalog.api.model.EntregaModel;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import com.algaworks.algalog.domain.service.SolicitacaoEntregaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private ModelMapper modelMapper;

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
    public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId){
        return entregaRepository.findById(entregaId)
                .map(entrega -> {

                  EntregaModel entregaModel = modelMapper.map(entrega, EntregaModel.class);//Converte o objeto entrega para um objeto de EntregaModel.class

                  /*entregaModel.setId(entrega.getId());
                  entregaModel.setNomeCliente(entrega.getCliente().getNome());
                  entregaModel.setDestinatario(new DestinatarioModel());
                  entregaModel.getDestinatario().setNome(entrega.getDestinatario().getNome());
                  entregaModel.getDestinatario().setLogradouro(entrega.getDestinatario().getLogradouro());
                  entregaModel.getDestinatario().setNumero(entrega.getDestinatario().getNumero());
                  entregaModel.getDestinatario().setComplemento(entrega.getDestinatario().getComplemento());
                  entregaModel.getDestinatario().setBairro(entrega.getDestinatario().getBairro());
                  entregaModel.setTaxa(entrega.getTaxa());
                  entregaModel.setStatus(entrega.getStatus());
                  entregaModel.setDataPedido(entrega.getDataPedido());
                  entregaModel.setDataFinalizacao(entrega.getDataFinalizacao());*/

                  return ResponseEntity.ok(entregaModel);
                }).orElse(ResponseEntity.notFound().build());
    }

}
