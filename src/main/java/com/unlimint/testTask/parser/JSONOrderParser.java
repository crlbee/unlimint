package com.unlimint.testTask.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unlimint.testTask.orders.InputOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("jsonParser")
public class JSONOrderParser extends OrderParser {
    private final ObjectMapper MAPPER;

    @Autowired
    public JSONOrderParser(ObjectMapper mapper) {
        this.MAPPER = mapper;
    }

    @Override
    public Optional<InputOrder> getOrder(String orderLine){
        try {
            return Optional.of(MAPPER.readValue(orderLine, InputOrder.class));
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }
}
