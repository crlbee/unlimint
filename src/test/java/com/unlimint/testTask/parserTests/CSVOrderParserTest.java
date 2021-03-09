package com.unlimint.testTask.parserTests;

import com.unlimint.testTask.orders.InputOrder;
import com.unlimint.testTask.parser.CSVOrderParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CSVOrderParserTest {

    @Autowired
    CSVOrderParser parser;

    static final String CORRECT_ORDER = "1,100,USD,оплата заказа";
    static final String INCORRECT_ORDER = "1,asd,123,asdasdd";

    static Optional<InputOrder> correctInputOrder;
    static Optional<InputOrder> incorrectInputOrder;

    @BeforeAll
    public static void init(){
        correctInputOrder = Optional.of(new InputOrder(1, 100, "USD", "оплата заказа"));
        incorrectInputOrder = Optional.empty();
    }

    @Test
    void parseFileWithCorrectOrders(){
        Optional<InputOrder> inputOrder = parser.parse(CORRECT_ORDER);
        assertEquals(correctInputOrder, inputOrder);
    }

    @Test
    void parseFileWithIncorrectOrders(){
        Optional<InputOrder> inputOrders = parser.parse(INCORRECT_ORDER);
        assertEquals(incorrectInputOrder, inputOrders);
    }
}
