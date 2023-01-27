package com.algaworks.algalog.controller;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.service.CatalogoClienteService;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor //Gera os construtores
@RestController
@RequestMapping("/clientes") //Assim não preciso colocar "/clientes" em all GetMapping
public class ClienteController {


    private ClienteRepository clienteRepository;
    private CatalogoClienteService catalogoClienteService;

    @GetMapping
    public List<Cliente> listar(){

        return clienteRepository.findAll();

    }


    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId){

        return clienteRepository.findById(clienteId)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());


    }

    //Inserindo cliente
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente){
        //return clienteRepository.save(cliente); //Se não usar o CatalogoClienteService
        return catalogoClienteService.salvar(cliente);
    }

    //Atualizando Cliente
    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @Valid @RequestBody Cliente cliente){
        if(!clienteRepository.existsById(clienteId)){//Se o cliente não existir
            return ResponseEntity.notFound().build();
        }

        cliente.setId(clienteId);
        //cliente = clienteRepository.save(cliente);
        cliente = catalogoClienteService.salvar(cliente);

        return ResponseEntity.ok(cliente);
    }

    //Deletando clientes
    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> remover(@PathVariable Long clienteId){
        if(!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build(); //retorna 404, se nao existir
        }

        //clienteRepository.deleteById(clienteId);
        catalogoClienteService.excluir(clienteId);

        return ResponseEntity.noContent().build(); //Retorna o 204
    }


}
