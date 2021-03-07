package com.unlimint.testTask.services;

import com.unlimint.testTask.orders.InputOrder;
import com.unlimint.testTask.orders.OutputOrder;
import com.unlimint.testTask.parser.GenericOrderParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class OrderConverterServiceImpl implements OrderConverterService {

    private GenericOrderParser parser;

    @Autowired
    public OrderConverterServiceImpl(GenericOrderParser parser) {
        this.parser = parser;
    }

    @Override
    public List<OutputOrder> convert(String filename){
        List<Optional<InputOrder>> orderList = parser.parse(filename);
        return IntStream.range(0, orderList.size())
                .parallel()
                .mapToObj(index -> inputToOutputOrder(orderList.get(index), index, filename))
                .collect(Collectors.toList());
    }

    public OutputOrder inputToOutputOrder(Optional<InputOrder> order, int index, String filename){
        if (order.isPresent()){
            InputOrder inputOrder = order.get();
            return new OutputOrder(inputOrder.getAmount(), inputOrder.getComment(), filename, index + 1, "OK");
        } else {
            return new OutputOrder(filename, index + 1, "can't read line");
        }
    }
}
