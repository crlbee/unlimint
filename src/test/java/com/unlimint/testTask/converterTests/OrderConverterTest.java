package com.unlimint.testTask.converterTests;

import com.unlimint.testTask.converter.Converter;
import com.unlimint.testTask.orders.InputOrder;
import com.unlimint.testTask.orders.OutputOrder;
import com.unlimint.testTask.parser.GenericOrderParser;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

@SpringBootTest
public class OrderConverterTest {

    @MockBean
    GenericOrderParser genericOrderParser;

    @Autowired
    Converter converter;

    static final String FILENAME = "FILENAME";


    static final String LINE = "1,100,RUB,test";

    static final Optional<InputOrder> INPUT_ORDER =
            Optional.of(new InputOrder(1, 1, "RUB", "test"));

    static final Optional<InputOrder> EMPTY_ORDER = Optional.empty();

    static final OutputOrder CORRECT_RESULT =
            new OutputOrder(1, "test", FILENAME, 1, "OK");

    static final OutputOrder CORRECT_RESULT_WITH_EMPTY_LINE =
            new OutputOrder(FILENAME, 1, "can't read line");


    @Test
    public void convertFileWithFineLines(){
        Mockito.when(genericOrderParser.parse(LINE ,FILENAME))
                .thenReturn(INPUT_ORDER);
        assertEquals(CORRECT_RESULT, converter.convert(INPUT_ORDER, FILENAME, 1));
    }

    @Test
    public void convertFileWithEmptyLines(){
        Mockito.when(genericOrderParser.parse(LINE, FILENAME))
                .thenReturn(EMPTY_ORDER);
        assertEquals(CORRECT_RESULT_WITH_EMPTY_LINE, converter.convert(EMPTY_ORDER, FILENAME, 1));
    }

}
