package com.unlimint.testTask.parserTests;

import com.unlimint.testTask.orders.InputOrder;
import com.unlimint.testTask.parser.JSONOrderParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class JSONOrderParserTest {

    @Autowired
    JSONOrderParser parser;

    static final String CORRECT_ORDERS_FILEPATH = "src/test/resources/correct.json";
    static final String INCORRECT_ORDERS_FILEPATH = "src/test/resources/incorrect.json";
    static final String INCORRECT_FILENAME = "INCORRECT_FILENAME";

    static List<Optional<InputOrder>> correctInputOrderList;
    static List<Optional<InputOrder>> incorrectInputOrderList;

    @BeforeAll
    public static void init(){
        correctInputOrderList = Arrays.asList(Optional.of(new InputOrder(3, 1.24, "EUR", "оплата заказа")),
                Optional.of(new InputOrder(2, 1.23, "USD", "оплата заказа")));
        incorrectInputOrderList = Arrays.asList(Optional.empty(), Optional.empty());

    }

    @Test
    void openFileWithWrongName(){
        List<Optional<InputOrder>> inputOrders = parser.parse(INCORRECT_FILENAME);
        assertTrue(inputOrders.isEmpty());
    }

    @Test
    void parseFileWithCorrectOrders(){
        List<Optional<InputOrder>> inputOrders = parser.parse(CORRECT_ORDERS_FILEPATH);
        assertEquals(correctInputOrderList, inputOrders);
    }

    @Test
    void parseFileWithIncorrectOrders(){
        List<Optional<InputOrder>> inputOrders = parser.parse(INCORRECT_ORDERS_FILEPATH);
        assertEquals(incorrectInputOrderList, inputOrders);
    }
}
