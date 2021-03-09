package com.unlimint.testTask.converter;

import com.unlimint.testTask.orders.InputOrder;
import com.unlimint.testTask.orders.OutputOrder;

import java.util.Optional;

public interface Converter {
    OutputOrder convert(Optional<InputOrder> inputOrder, String filename, int line);
}
