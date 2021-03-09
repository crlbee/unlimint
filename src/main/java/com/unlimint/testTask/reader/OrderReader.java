package com.unlimint.testTask.reader;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

@Component
public class OrderReader implements Reader {

    private static ApplicationContext CONTEXT;
    private static Logger LOGGER;

    private static final String BEAN_SUFFIX = "Parser";

    @Autowired
    void setContext(ApplicationContext ctx, Logger lgr){
        CONTEXT = ctx;
        LOGGER = lgr;
    }

     public List<String> read(String filename){
        final String BEAN_PREFIX = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();
        if(CONTEXT.containsBean(BEAN_PREFIX + BEAN_SUFFIX))
            return readFromFile(filename);
        else {
            return Collections.emptyList();
        }
    }

    public List<String> readFromFile(String filename){
        try {
            return Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            LOGGER.warn("unsupported file extension", e);
            return Collections.emptyList();
        }
    }
}
