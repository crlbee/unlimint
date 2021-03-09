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

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GenericOrderParserTest {

    static final String JSON_FILE = "json.json";
    static final String UNSUPPORTED_FILE = "json.asdf";
    static final String CSV_FILE = "csv.csv";

    static final String JSON_ORDER = "{\"orderId\":1,\"amount\":1,\"currency\":\"RUB\",\"comment\":\"test\"}";
    static final String CSV_ORDER = "1,1,RUB,test";

    static final Optional<InputOrder> CORRECT_RESULT =
            Optional.of(new InputOrder(1, 1, "RUB", "test"));

    static final Optional<InputOrder> EMPTY_ORDER = Optional.empty();

    @MockBean(name = "jsonParser")
    JSONOrderParser jsonOrderParser;

    @MockBean(name = "csvParser")
    CSVOrderParser csvOrderParser;

    @Autowired
    GenericOrderParser genericOrderParser;

    @Test
    public void parseCorrectJson(){
        Mockito.when(jsonOrderParser.parse(JSON_ORDER))
                .thenReturn(CORRECT_RESULT);
        assertEquals(CORRECT_RESULT, genericOrderParser.parse(JSON_ORDER, JSON_FILE));
    }

    @Test
    public void parseCorrectCsv(){
        Mockito.when(csvOrderParser.parse(CSV_ORDER))
                .thenReturn(CORRECT_RESULT);
        assertEquals(CORRECT_RESULT, genericOrderParser.parse(CSV_ORDER, CSV_FILE));
    }

    @Test
    public void parseUnsupportedFile(){
        assertEquals(EMPTY_ORDER, genericOrderParser.parse(CSV_ORDER,UNSUPPORTED_FILE));
    }

    @Test
    public void parseUnsupportedFileWithoutExtensionWithDot(){
        assertEquals(EMPTY_ORDER, genericOrderParser.parse(CSV_ORDER,"."));
    }

    @Test
    public void parseUnsupportedFileWithoutExtension(){
        assertEquals(EMPTY_ORDER, genericOrderParser.parse(CSV_ORDER,"ASDFG"));
    }







}
