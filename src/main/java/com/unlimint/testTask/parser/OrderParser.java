package com.unlimint.testTask.parser;

import com.unlimint.testTask.orders.InputOrder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class OrderParser implements Parser {

    private Logger LOGGER;

    @Autowired
    public void setLogger(Logger logger) {
        this.LOGGER = logger;
    }

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

    public abstract Optional<InputOrder> getOrder(String orderLine);
}
