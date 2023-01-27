package com.algaworks.algalog.api.model;

import lombok.Getter;
import lombok.Setter;

//Coloca-se na classe EntregaModel, uma classe ClienteResumoModel como tipo e aqui está o id e o nome do cliente
//Pela confModelMapper, o Spring procura o id e o nome do cliente, assim quando uma solicitação de entrega for
//feita para realizar uma listagem, aparecerá o id e nome do cliente dentro do objeto cliente
//Isso é ótimo, caso eu queira adcionar novos atributos ou decidir o que pode ser retornado
@Getter
@Setter
public class ClienteResumoModel {
    private Long id;
    private String nome;
}
