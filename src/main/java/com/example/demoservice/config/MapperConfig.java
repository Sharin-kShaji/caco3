package com.example.demoservice.config;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.config.Configuration.AccessLevel;


@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper getMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration().setFieldAccessLevel(AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true);
        Converter<String, LocalDate> stringToDate = new AbstractConverter<>() {
            @Override
            protected LocalDate convert(String s) {
                return LocalDate.parse(s, DateTimeFormatter.ofPattern("yyy-MM-dd"));
            }
        };
        mapper.createTypeMap(String.class, LocalDate.class);
        mapper.addConverter(stringToDate);
        return mapper;
    }
}
