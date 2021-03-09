package com.unlimint.testTask;

import com.unlimint.testTask.converter.Converter;
import com.unlimint.testTask.orders.OutputOrder;
import com.unlimint.testTask.parser.GenericOrderParser;
import com.unlimint.testTask.printer.OrderPrinter;
import com.unlimint.testTask.printer.Printer;
import com.unlimint.testTask.reader.OrderReader;
import com.unlimint.testTask.reader.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootApplication
public class TestTaskApplication{

    private static Converter orderConverter;
    private static GenericOrderParser parser;
    private static Reader reader;
    private static Printer printer;

    @Autowired
    void setConverter(Converter converterService, GenericOrderParser orderParser,
                      OrderReader orderReader, OrderPrinter orderPrinter){
        orderConverter = converterService;
        parser = orderParser;
        reader = orderReader;
        printer = orderPrinter;
    }

    public static void main(String[] args) {
        SpringApplication.run(TestTaskApplication.class, args);
        Stream.of(args)
                .parallel()
                .flatMap(TestTaskApplication::processFile)
                .forEachOrdered(printer::print);
    }

    public static Stream<OutputOrder> processFile(String filename){
        List<String> list = reader.read(filename);
        return IntStream.range(0, list.size())
                .parallel()
                .mapToObj(index -> orderConverter.convert(parser.parse(list.get(index), filename),
                        filename, index + 1));
    }
}
