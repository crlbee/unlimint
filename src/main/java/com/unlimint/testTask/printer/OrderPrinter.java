package com.unlimint.testTask.printer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.unlimint.testTask.orders.OutputOrder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderPrinter implements Printer{
    private static int id = 0;

    static private ObjectMapper MAPPER;
    static private Logger LOGGER;

    @Autowired
    public OrderPrinter(ObjectMapper mapper, Logger logger) {
        MAPPER = mapper;
        LOGGER = logger;
    }

    public void print(OutputOrder order){
        ObjectNode node = MAPPER.createObjectNode();
        try {
            node.put("Id",  ++id);
            node.setAll(MAPPER.readValue(MAPPER.writeValueAsString(order), ObjectNode.class));
        } catch (JsonProcessingException e) {
            LOGGER.warn("can't convert from Output to json", e);
        }
        System.out.println(node);
    }
}
