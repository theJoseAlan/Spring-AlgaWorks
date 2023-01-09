package com.algaworks.algalog.controller;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import jakarta.persistence.EntityManager;
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

    /*@PersistenceContext
    private EntityManager manager;*/

    //@Autowired //Injetar uma instancia gerenciada pelo Spring
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listar(){

        return clienteRepository.findAll();

        //Faz parte do entity manager
        //return manager.createQuery("from Cliente", Cliente.class).getResultList();
    }

    /*@GetMapping("/clientes")
    public List<Cliente> listar(){

        return clienteRepository.findByNomeContaining("a");

    }*/

    /*@GetMapping("/clientes")
    public List<Cliente> listar(){

        return clienteRepository.findByNome("Joao Da Silva");

    }*/

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId){

        return clienteRepository.findById(clienteId)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

        //ou

        /*Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        if(cliente.isPresent()){ //se tiver alguma coisa
            return ResponseEntity.ok(cliente.get()); //retorna o que tem dentro do cliente
        }

        return ResponseEntity.notFound().build(); //Not found se nao encontrar nada (retorna um 404)*/

        //return cliente.orElse(null); //Se não tiver nada ele retorna null (apenas se usa 'Cliente'
        // ao invés do ReponseEntity<Cliente>
    }

    //Inserindo cliente
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    //Atualizando Cliente
    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @RequestBody Cliente cliente){
        if(!clienteRepository.existsById(clienteId)){//Se o cliente não existir
            return ResponseEntity.notFound().build();
        }
        cliente.setId(clienteId);
        cliente = clienteRepository.save(cliente);

        return ResponseEntity.ok(cliente);
    }

    //Deletando clientes
    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> remover(@PathVariable Long clienteId){
        if(!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build(); //retorna 404, se nao existir
        }

        clienteRepository.deleteById(clienteId);

        return ResponseEntity.noContent().build(); //Retorna o 204
    }


}
