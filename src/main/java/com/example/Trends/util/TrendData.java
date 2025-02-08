package com.example.Trends.util;

import com.example.Trends.model.TrendModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrendData {

    public static void generateTrendData(String fileName) {
        String[] headers = {"Дата, время", "Ручей 7: Уровень металла в кристаллизаторе, мм", "Ручей 8: Уровень металла в кристаллизаторе, мм"};

        try (FileWriter out = new FileWriter(fileName);
             CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(headers))) {

            LocalDateTime startTime = LocalDateTime.of(2025, 2, 8, 15, 50);
            Random random = new Random();

            for (int i = 0; i < 800; i++) {
                LocalDateTime currentTime = startTime.plusMinutes(i);
                String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));

                int stream7Level = 620 + random.nextInt(200);
                int stream8Level = 620 + random.nextInt(200);

                printer.printRecord(formattedTime, stream7Level, stream8Level);
            }

            System.out.println("Данные успешно записаны в файл: " + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
public static List<TrendModel> getTrendData(String filePath){
    List<TrendModel> trendModelList = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    try (Reader reader = new FileReader(filePath);
         CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

        for (CSVRecord record : csvParser) {
            LocalDateTime timestamp = LocalDateTime.parse(record.get("Дата, время"), formatter);
            int stream7Level = Integer.parseInt(record.get("Ручей 7: Уровень металла в кристаллизаторе, мм"));
            int stream8Level = Integer.parseInt(record.get("Ручей 8: Уровень металла в кристаллизаторе, мм"));

            TrendModel data = new TrendModel(timestamp, stream7Level, stream8Level);
            trendModelList.add(data);
        }


    } catch (IOException e) {
        e.printStackTrace();
    }
    return trendModelList;
}
}


