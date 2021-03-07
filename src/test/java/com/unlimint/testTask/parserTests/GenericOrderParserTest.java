package com.unlimint.testTask.parserTests;

import com.unlimint.testTask.orders.InputOrder;
import com.unlimint.testTask.parser.CSVOrderParser;
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
    static final String CSV_FILE = "correct.csv";


    static final List<Optional<InputOrder>> CORRECT_RESULT = Arrays.asList(
            Optional.of(new InputOrder(1, 1, "RUB", "test")),
            Optional.of(new InputOrder(2, 2, "RUB", "test")));
    static final List<Optional<InputOrder>> EMPTY_LIST = Collections.emptyList();

    @MockBean(name = "jsonParser")
    JSONOrderParser jsonOrderParser;

    @MockBean(name = "csvParser")
    CSVOrderParser csvOrderParser;

    @Autowired
    GenericOrderParser genericOrderParser;

    @Test
    public void parseCorrectJson(){
        Mockito.when(jsonOrderParser.parse(JSON_FILE))
                .thenReturn(CORRECT_RESULT);
        assertEquals(CORRECT_RESULT, genericOrderParser.parse(JSON_FILE));
    }

    @Test
    public void parseCorrectCsv(){
        Mockito.when(csvOrderParser.parse(CSV_FILE))
                .thenReturn(CORRECT_RESULT);
        assertEquals(CORRECT_RESULT, genericOrderParser.parse(CSV_FILE));
    }

    @Test
    public void parseUnsupportedFile(){
        assertEquals(EMPTY_LIST, genericOrderParser.parse(UNSUPPORTED_FILE));
    }

    @Test
    public void parseUnsupportedFileWithoutExtensionWithDot(){
        assertEquals(EMPTY_LIST, genericOrderParser.parse("."));
    }

    @Test
    public void parseUnsupportedFileWithoutExtension(){
        assertEquals(EMPTY_LIST, genericOrderParser.parse("ASDFG"));
    }







}
