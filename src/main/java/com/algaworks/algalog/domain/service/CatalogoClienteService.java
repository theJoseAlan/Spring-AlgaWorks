package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.Exception.NegocioException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//A classe de serviço é útil para as regras de negócio do sistema.
//Se não tiver ela, as regras vão para o Controller e vai ficar uma bagunça

//Resposável pelo cadastro do cliente, CRUD
@AllArgsConstructor
@Service
public class CatalogoClienteService {

    private ClienteRepository clienteRepository;

    public Cliente buscar(Long clienteId){
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
    }

    @Transactional //Diz que esse método deve ser executado dentro de uma transação. Se algo der errado, a transação é descartada
    public Cliente salvar(Cliente cliente){

        //Verifica se o email que será salvo já está em uso
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .stream().anyMatch(clienteExistente -> !clienteExistente.equals(cliente));

        if(emailEmUso){
            throw  new NegocioException("Já existe um cliente cadastrado com esse email");
        }

        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluir(Long clienteId){
        clienteRepository.deleteById(clienteId);
    }

}
