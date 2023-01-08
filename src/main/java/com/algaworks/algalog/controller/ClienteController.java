package com.algaworks.algalog.controller;

import com.algaworks.algalog.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
public class ClienteController {

    @GetMapping("/clientes")
    public List<Cliente> listar(){

        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome("João11");
        cliente1.setTelefone("12121313");
        cliente1.setEmail("salesjoao@gmail.com");

        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNome("Maria");
        cliente2.setTelefone("43554554");
        cliente2.setEmail("salesmaria@gmail.com");

        return Arrays.asList(cliente1, cliente2);
    }


}
