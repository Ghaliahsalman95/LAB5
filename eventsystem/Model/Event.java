package com.example.eventsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data@AllArgsConstructor
public class Event {
    private String ID , description ;
    private int capacity;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate , endDate;
}
