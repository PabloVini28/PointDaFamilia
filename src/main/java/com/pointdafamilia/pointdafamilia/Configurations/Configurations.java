package com.pointdafamilia.pointdafamilia.Configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configurations{
    
    @Bean   
    public ModelMapper modelmapper(){
        return new ModelMapper();
    }
}