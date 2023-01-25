package com.algaworks.algalog.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class criaModelMapper {

    @Bean
    public ModelMapper metodoQueCriaUmModelMapper(){
        return new ModelMapper();

    }

}
