package com.algaworks.algalog.api.assembler;

//Reponsável por mapeamento de outras classes, transformando o objeto de uma no objeto de outra

import com.algaworks.algalog.api.model.EntregaModel;
import com.algaworks.algalog.api.model.input.EntregaInput;
import com.algaworks.algalog.domain.model.Entrega;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component //Marcando como componente do Spring
public class EntregaAssembler {

    private ModelMapper modelMapper;

    public EntregaModel toModel(Entrega entrega){
        return modelMapper.map(entrega, EntregaModel.class);
    }

    public List<EntregaModel> toCollectionModel(List<Entrega> entregas){
        //stream retorna uma sequencia de elementos que suporta navegação/transformação...
        //No .map é colocado uma funcao aos elementos do stream, que retorna um novo stream
        //como resultado
        //Convertendo uma stream de entregas para uma stream de entregaModel
        //collect converter a stream em lista, que é o retorno dessa classe
        return entregas.stream().map(this::toModel).collect(Collectors.toList());
    }

    public Entrega toEntity(EntregaInput entregaInput){
        return modelMapper.map(entregaInput, Entrega.class);
    }

}
