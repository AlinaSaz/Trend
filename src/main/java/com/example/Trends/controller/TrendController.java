package com.example.Trends.controller;

import com.example.Trends.model.TrendModel;
import com.example.Trends.service.TrendService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TrendController {
    @Autowired
    private TrendService trendService;

    @RequestMapping(value = "/getStream7Points", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getStream7Points(@RequestParam(value = "threshold") Double threshold) throws IOException {

            List<TrendModel> trendData = trendService.getTrends("data/trend_data_generated.csv");
        return ResponseEntity.status(HttpStatus.OK)
                .body(trendService.getStream7Points(trendData, threshold));


    }
    @RequestMapping(value = "/getStream8Points", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getStream8Points(@RequestParam(value = "threshold") Double threshold) throws IOException {

        List<TrendModel> trendData = trendService.getTrends("data/trend_data_generated.csv");
        return ResponseEntity.status(HttpStatus.OK)
                .body(trendService.getStream8Points(trendData, threshold));


    }
}
