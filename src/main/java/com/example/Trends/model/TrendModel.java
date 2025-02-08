package com.example.Trends.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrendModel {
    private LocalDateTime data;
    private int stream7Level;
    private int stream8Level;
}
