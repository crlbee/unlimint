package com.unlimint.testTask.readerTests;

import com.unlimint.testTask.reader.OrderReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReaderTest {

    static final String JSON_FILE = "src/test/resources/correct.json";
    static final String CSV_FILE = "src/test/resources/correct.csv";
    static final String UNSUPPORTED_FILE = "json.asdf";

    static final String CSV_PARSER = "csvParser";
    static final String JSON_PARSER = "jsonParser";

    static List<String> READ_FROM_JSON;
    static List<String> READ_FROM_CSV;
    static List<String> READ_FROM_UNSUPPORTED_FILE;

    @BeforeAll
    static void init(){
        READ_FROM_JSON = Arrays.asList("{\"orderId\":3,\"amount\":1.24,\"currency\":\"EUR\",\"comment\":\"оплата заказа\"}",
                "{\"orderId\":2,\"amount\":1.23,\"currency\":\"USD\",\"comment\":\"оплата заказа\"}");
        READ_FROM_CSV = Arrays.asList("2,123,EUR,оплата заказа", "1,100,USD,оплата заказа");
        READ_FROM_UNSUPPORTED_FILE = Collections.emptyList();
    }

    @MockBean
    ApplicationContext context;

    @Autowired
    OrderReader reader;

    @Test
    void readFromJson(){
        Mockito.when(context.containsBean(JSON_PARSER))
                .thenReturn(Boolean.TRUE);
        assertEquals(READ_FROM_JSON, reader.read(JSON_FILE));
    }

    @Test
    void readFromCsv(){
        Mockito.when(context.containsBean(CSV_PARSER))
                .thenReturn(Boolean.TRUE);
        assertEquals(READ_FROM_CSV, reader.read(CSV_FILE));
    }

    @Test
    void readFrom(){
        Mockito.when(context.containsBean(UNSUPPORTED_FILE))
                .thenReturn(Boolean.FALSE);
        assertEquals(READ_FROM_UNSUPPORTED_FILE, reader.read(UNSUPPORTED_FILE));
    }
}
