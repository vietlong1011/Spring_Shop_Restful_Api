package com.web.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean(name = "moderMapper")
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.getConfiguration()
//                .setMatchingStrategy(MatchingStrategies.LOOSE);//mapper giua 2 value gan dung(MatchingStrategies.LOOSE)
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper;
    }
}
