package com.unlimint.testTask;

import com.unlimint.testTask.parser.GenericOrderParser;
import com.unlimint.testTask.services.OrderConverterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collection;
import java.util.stream.Stream;

@SpringBootApplication
public class TestTaskApplication{

    private static OrderConverterServiceImpl orderConverterService;

    @Autowired
    void setConverter(OrderConverterServiceImpl converterService){
        orderConverterService = converterService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TestTaskApplication.class, args);

        Stream.of(args)
                .parallel()
                .map(orderConverterService::convert)
                .flatMap(Collection::stream)
                .forEachOrdered(Printer::print);
    }
}
