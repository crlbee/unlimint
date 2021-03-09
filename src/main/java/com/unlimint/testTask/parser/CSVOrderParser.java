package com.unlimint.testTask.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectReader;
import com.unlimint.testTask.orders.InputOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("csvParser")
public class CSVOrderParser implements Parser {

    ObjectReader reader;

    @Autowired
    CSVOrderParser(ObjectReader reader){
        this.reader = reader;
    }

    @Override
    public Optional<InputOrder> parse(String orderLine) {
        try {
            return Optional.of(reader.readValue(orderLine));
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }
}
