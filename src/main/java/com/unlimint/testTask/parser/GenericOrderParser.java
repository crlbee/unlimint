package com.unlimint.testTask.parser;

import com.unlimint.testTask.orders.InputOrder;
import org.slf4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GenericOrderParser implements OrderParser{
    static final String PARSER_BEAN_SUFFIX = "Parser";

    private final ApplicationContext CONTEXT;
    private final Logger LOGGER;

    @Autowired
    private GenericOrderParser(ApplicationContext ctx, Logger log){
        CONTEXT = ctx;
        LOGGER = log;
    }

    public List<Optional<InputOrder>> parse(String filename) {
        final String PARSER_BEAN_PREFIX = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();

        String parserName = PARSER_BEAN_PREFIX + PARSER_BEAN_SUFFIX;
        try {
            return CONTEXT.getBean(parserName, OrderParser.class).parse(filename);
        } catch (BeansException e) {
            LOGGER.warn("unsupported file extension", e);
            return Collections.emptyList();
        }
    }
}
