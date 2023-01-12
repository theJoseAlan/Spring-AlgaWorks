package com.algaworks.algalog.domain.service;


import com.algaworks.algalog.domain.Exception.NegocioException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.StatusEntrega;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

    private CatalogoClienteService catalogoClienteService;

    private EntregaRepository entregaRepository;

    @Transactional
    public Entrega solicitar(Entrega entrega){
        //Verificando se o Id de cliente informado existe
        Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());

        entrega.setCliente(cliente); //se não colocar isso, aparecera tudo null, com excessão do Id
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());

        return entregaRepository.save(entrega);
    }
}
