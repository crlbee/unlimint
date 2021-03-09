package com.unlimint.testTask.converter;

import com.unlimint.testTask.orders.InputOrder;
import com.unlimint.testTask.orders.OutputOrder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderConverter implements Converter{

    public OutputOrder convert(Optional<InputOrder> inputOrder, String filename, int line){
        if (inputOrder.isPresent()){
            InputOrder order = inputOrder.get();
            return new OutputOrder(order.getAmount(), order.getComment(), filename, line, "OK");
        } else {
            return new OutputOrder(filename, line, "can't read line");
        }
    }
}
