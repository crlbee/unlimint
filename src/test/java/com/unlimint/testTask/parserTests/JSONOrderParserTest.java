package com.unlimint.testTask.parserTests;

import com.unlimint.testTask.orders.InputOrder;
import com.unlimint.testTask.parser.JSONOrderParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

@SpringBootTest
public class JSONOrderParserTest {

    @Autowired
    JSONOrderParser parser;

    static final String CORRECT_ORDER = "{\"orderId\":1,\"amount\":1,\"currency\":\"RUB\",\"comment\":\"test\"}";
    static final String INCORRECT_ORDER = "{\"orderId\":1,\"amount\":asdasd,\"currency\":\"RUB\",\"comment\":\"test\"}";

    static Optional<InputOrder> correctInputOrder;
    static Optional<InputOrder> incorrectInputOrder;

    @BeforeAll
    public static void init(){
        correctInputOrder = Optional.of(new InputOrder(1, 1, "RUB", "test"));
        incorrectInputOrder = Optional.empty();

    }

    @Test
    void parseFileWithCorrectOrder(){
        Optional<InputOrder> inputOrders = parser.parse(CORRECT_ORDER);
        assertEquals(correctInputOrder, inputOrders);
    }

    @Test
    void parseFileWithIncorrectOrder(){
        Optional<InputOrder> inputOrders = parser.parse(INCORRECT_ORDER);
        assertEquals(incorrectInputOrder, inputOrders);
    }
}
