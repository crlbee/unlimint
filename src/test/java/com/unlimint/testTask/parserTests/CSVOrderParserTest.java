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

    static final String CORRECT_ORDERS_FILEPATH = "src/test/resources/correct.csv";
    static final String INCORRECT_ORDERS_FILEPATH = "src/test/resources/incorrect.csv";
    static final String INCORRECT_FILENAME = "INCORRECT_FILENAME";

    static List<Optional<InputOrder>> correctInputOrderList;
    static List<Optional<InputOrder>> incorrectInputOrderList;

    @BeforeAll
    public static void init(){
        correctInputOrderList = Arrays.asList(Optional.of(new InputOrder(1, 100, "USD", "оплата заказа")),
                Optional.of(new InputOrder(2, 123, "EUR", "оплата заказа")));
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
