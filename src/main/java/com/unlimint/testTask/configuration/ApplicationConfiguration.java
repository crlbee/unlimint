package com.unlimint.testTask.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.unlimint.testTask.orders.InputOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Objects;


@Configuration
public class ApplicationConfiguration {

    @Bean
    ObjectMapper makeObjectMapper(){
        return new ObjectMapper();
    }

    @Bean
    @Scope("prototype")
    Logger logger(InjectionPoint injectionPoint){
        return LoggerFactory.getLogger(Objects.requireNonNull(injectionPoint.getMethodParameter())
                .getContainingClass().getName());
    }

    @Bean
    ObjectReader reader(){
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(InputOrder.class).withColumnSeparator(',');
        return mapper.readerFor(InputOrder.class).with(schema);
    }
}
