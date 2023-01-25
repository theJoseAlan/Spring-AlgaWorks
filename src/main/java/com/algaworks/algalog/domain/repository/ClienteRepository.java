package com.algaworks.algalog.domain.repository;

import com.algaworks.algalog.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //Um repositório gerencia as entidades
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNome(String nome);

    List<Cliente> findByNomeContaining(String nome);//Caso o nome nao esteja completo, tipo Jo (Joao da Silva)

    Optional<Cliente> findByEmail(String email); //Pode ter algum cliente com um dado email ou pode não ter

}