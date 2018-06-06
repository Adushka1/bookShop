package com.adushka.shop.csv;

import com.adushka.shop.model.IEntity;
import org.apache.log4j.Logger;

import java.io.*;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CSVWorker {
    private final static Logger LOGGER = Logger.getLogger(CSVWorker.class);
    private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public static void saveToCSV(List<? extends IEntity> entities) {

        CsvEntity csvEntity = entities.get(0).getClass().getAnnotation(CsvEntity.class);
        String path = csvEntity.filename();
        String separator = csvEntity.valuesSeparator();

        while (true) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, false))) {

                for (IEntity entity : entities) {

                    StringBuilder str = new StringBuilder();
                    Field[] fields = entity.getClass().getDeclaredFields();

                    for (int j = 0; j < fields.length; j++) {
                        for (Field field : fields) {
                            if (field.isAnnotationPresent(CsvProperty.class)) {
                                CsvProperty annotation = field.getDeclaredAnnotation(CsvProperty.class);
                                int num = annotation.columnNumber();
                                field.setAccessible(true);
                                if (num == j + 1) {
                                    if (annotation.propertyType() == PropertyType.SimpleProperty) {
                                        if (field.getType().getSimpleName().equals("Date")) {
                                            str.append(df.format(field.get(entity))).append(separator);
                                        }
                                        str.append(field.get(entity)).append(separator);
                                    }
                                }
                            }
                        }
                    }
                    str.append("\n");
                    bw.write(str.toString());
                }

            }
            catch (Exception e) {
                LOGGER.error("Can't close", e);
            }
            break;
        }
    }

    public static List<String> loadCsvStrings(Class<? extends IEntity> clazz) {
        CsvEntity csvEntity = clazz.getAnnotation(CsvEntity.class);
        List<String> strings = new ArrayList<>();
        String str;

        try (BufferedReader reader = new BufferedReader(new FileReader(csvEntity.filename()))) {

            while ((str = reader.readLine()) != null) {
                strings.add(str);
            }
        }
        catch (IOException e) {
            LOGGER.error("AEntity's file not found", e);
        }
        return strings;
    }
}
