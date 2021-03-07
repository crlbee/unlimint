package com.unlimint.testTask.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unlimint.testTask.orders.InputOrder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("jsonParser")
public class JSONOrderParser implements OrderParser {

    private final ObjectMapper MAPPER;
    private final Logger LOGGER;
    
    @Autowired
    public JSONOrderParser(ObjectMapper mapper, Logger logger) {
        this.MAPPER = mapper;
        this.LOGGER = logger;
    }

    @Override
    public List<Optional<InputOrder>> parse(String filename){
        List<Optional<InputOrder>> InputOrders = new ArrayList<>();

        try {
            InputOrders = Files.lines(Paths.get(filename))
                    .map(this::getOrder)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.warn("Can't open file " + filename, e);
        }
        return InputOrders;
    }

    private Optional<InputOrder> getOrder(String orderLine){
        try {
            return Optional.of(MAPPER.readValue(orderLine, InputOrder.class));
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }
}
