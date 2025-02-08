package com.example.Trends.service;

import com.example.Trends.model.TrendModel;
import com.example.Trends.util.TrendData;
import org.springframework.stereotype.Service;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrendService {

    public List<TrendModel> getTrends(String filePath) {
        Path path = FileSystems.getDefault().getPath(filePath);
            if (!Files.exists(path)) {
                TrendData.generateTrendData(filePath);
            }
            return TrendData.getTrendData(filePath);

    }

    public List<String> getStream7Points(List<TrendModel> trend, double threshold) {
        List<String> stream7Points = new ArrayList<>();
        for (int i = 1; i < trend.size(); i++) {
            double diff = Math.abs(trend.get(i).getStream7Level() - trend.get(i - 1).getStream7Level());
            if (diff  > threshold) {
                stream7Points.add("Ручей 7. Дата: " + trend.get(i).getData() + " - " + trend.get(i - 1).getData() + " скачок превысил допустимый: " + diff);
            }
        }
        return stream7Points;
    }


    public List<String> getStream8Points(List<TrendModel> trend, double threshold) {
        List<String> stream8Points = new ArrayList<>();
        for (int i = 1; i < trend.size(); i++) {
            double diff = Math.abs(trend.get(i).getStream8Level() - trend.get(i - 1).getStream8Level());
            if (diff > threshold) {
                stream8Points.add("Ручей 8. Дата: " + trend.get(i).getData() + " - " + trend.get(i - 1).getData() + " скачок превысил допустимый: " + diff);
            }
        }
        return stream8Points;
    }
}
