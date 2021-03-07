package com.unlimint.testTask.parserTests;

import com.unlimint.testTask.orders.InputOrder;
import com.unlimint.testTask.parser.GenericOrderParser;
import com.unlimint.testTask.parser.JSONOrderParser;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GenericOrderParserTest {

    static final String JSON_FILE = "json.json";
    static final String UNSUPPORTED_FILE = "json.asdf";

    static final List<Optional<InputOrder>> CORRECT_RESULT = Arrays.asList(
            Optional.of(new InputOrder(1, 1, "RUB", "test")),
            Optional.of(new InputOrder(2, 2, "RUB", "test")));
    static final List<Optional<InputOrder>> EMPTY_LIST = Collections.emptyList();

    @MockBean(name = "jsonParser")
    static JSONOrderParser jsonOrderParser;

    @Autowired
    GenericOrderParser genericOrderParser;

    @Test
    public void parseCorrectJson(){
        Mockito.when(jsonOrderParser.parse(JSON_FILE))
                .thenReturn(CORRECT_RESULT);
        assertEquals(genericOrderParser.parse(JSON_FILE), CORRECT_RESULT);
    }

    @Test
    public void parseUnsupportedFile(){
        Mockito.when(jsonOrderParser.parse(UNSUPPORTED_FILE))
                .thenReturn(EMPTY_LIST);
        assertEquals(genericOrderParser.parse(UNSUPPORTED_FILE), EMPTY_LIST);
    }




}
