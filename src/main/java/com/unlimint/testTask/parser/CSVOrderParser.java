package com.unlimint.testTask.parser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.unlimint.testTask.orders.InputOrder;
import org.springframework.stereotype.Component;

import java.io.StringReader;
import java.util.Optional;

@Component("csvParser")
public class CSVOrderParser extends OrderParser {

    @Override
    public Optional<InputOrder> getOrder(String orderLine) {
        try {
            CsvToBean<InputOrder> csvParser =
                    new CsvToBeanBuilder<InputOrder>(new StringReader(orderLine)).withType(InputOrder.class).build();
            return Optional.of(csvParser.parse().get(0));
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }
}
