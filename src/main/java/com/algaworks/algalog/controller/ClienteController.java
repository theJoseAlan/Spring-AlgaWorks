package com.algaworks.algalog.controller;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor //Gera os construtores
@RestController
public class ClienteController {

    /*@PersistenceContext
    private EntityManager manager;*/

    //@Autowired //Injetar uma instancia gerenciada pelo Spring
    private ClienteRepository clienteRepository;

    @GetMapping("/clientes")
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

    @GetMapping("/clientes/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId){
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        if(cliente.isPresent()){ //se tiver alguma coisa
            return ResponseEntity.ok(cliente.get()); //retorna o que tem dentro do cliente
        }

        return ResponseEntity.notFound().build(); //Not found se nao encontrar nada (retorna um 404)

        //return cliente.orElse(null); //Se não tiver nada ele retorna null (apenas se usa 'Cliente'
        // ao invés do ReponseEntity<Cliente>
    }


}
