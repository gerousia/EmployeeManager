package com.gerousia.database;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.util.List;

public class CSV {

    public static <T> List<T> loadCSV(String file, Class<T> type) throws FileNotFoundException {
        return new CsvToBeanBuilder(new FileReader(file))
                .withType(type)
                .build()
                .parse();
    }

    public static <T> void saveCSV(String file, List<T> list) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        try (Writer writer = new FileWriter(file)) {
            new StatefulBeanToCsvBuilder(writer)
                    //.withMappingStrategy(new HeaderColumnNameMappingStrategyBuilder<Employee>().build())
                    .build()
                    .write(list);
        }
    }

}
