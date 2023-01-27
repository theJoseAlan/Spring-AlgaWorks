package com.algaworks.algalog.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Classe para definições de bean no Spring, nem sempre ele reconhece algo que colocamos
@Configuration
public class configModelMapper {

    @Bean //Inicializa e configura um bean que será usado pelo Spring
    public ModelMapper metodoQueCriaUmModelMapper(){
        return new ModelMapper();

    }

}
