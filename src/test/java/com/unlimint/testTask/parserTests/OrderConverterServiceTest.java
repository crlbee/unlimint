package com.unlimint.testTask.parserTests;

import com.unlimint.testTask.orders.InputOrder;
import com.unlimint.testTask.orders.OutputOrder;
import com.unlimint.testTask.parser.GenericOrderParser;
import com.unlimint.testTask.services.OrderConverterServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OrderConverterServiceTest {

    @MockBean
    GenericOrderParser genericOrderParser;

    static final String FILENAME = "FILENAME";

    @Autowired
    OrderConverterServiceImpl orderConverterService;

    static final List<Optional<InputOrder>> ORDER_LIST = Arrays.asList(
            Optional.of(new InputOrder(1, 1, "RUB", "test")),
            Optional.of(new InputOrder(2, 2, "RUB", "test")));
    static final List<Optional<InputOrder>> EMPTY_OPTIONALS_LIST = Arrays.asList(Optional.empty(), Optional.empty());


    static final List<OutputOrder> CORRECT_RESULT = Arrays.asList(
            new OutputOrder(1, "test", FILENAME, 1, "OK"),
            new OutputOrder(2, "test", FILENAME, 2, "OK"));

    static final List<OutputOrder> CORRECT_RESULT_WITH_EMPTY_LINES = Arrays.asList(
            new OutputOrder(FILENAME, 1, "can't read line"),
            new OutputOrder(FILENAME, 2, "can't read line"));

    static final List<Optional<InputOrder>> EMPTY_LIST = Collections.emptyList();


    @Test
    public void convertFileWithFineLines(){
        Mockito.when(genericOrderParser.parse(FILENAME))
                .thenReturn(ORDER_LIST);
        assertEquals(CORRECT_RESULT, orderConverterService.convert(FILENAME));
    }

    @Test
    public void convertFileWithEmptyLines(){
        Mockito.when(genericOrderParser.parse(FILENAME))
                .thenReturn(EMPTY_OPTIONALS_LIST);
        assertEquals(CORRECT_RESULT_WITH_EMPTY_LINES, orderConverterService.convert(FILENAME));
    }

    @Test
    public void convertEmptyFile(){
        Mockito.when(genericOrderParser.parse(FILENAME))
                .thenReturn(EMPTY_LIST);
        assertTrue(orderConverterService.convert(FILENAME).isEmpty());
    }
}
