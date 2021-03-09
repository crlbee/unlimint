package com.unlimint.testTask.parser;

import com.unlimint.testTask.orders.InputOrder;

import java.util.List;
import java.util.Optional;

public interface Parser {
    Optional<InputOrder> parse(String line);
}
