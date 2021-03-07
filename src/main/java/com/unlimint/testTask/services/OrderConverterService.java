package com.unlimint.testTask.services;

import com.unlimint.testTask.orders.InputOrder;
import com.unlimint.testTask.orders.OutputOrder;

import java.util.List;
import java.util.Optional;

public interface OrderConverterService {
    List<OutputOrder> convert(String filename);
    OutputOrder inputToOutputOrder(Optional<InputOrder> order, int index, String filename);
}
