package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;
import java.util.List;

public class FilterExcludeDepartureBeforeCurrentTime implements FlightsFilter<Flight> {
    private final LocalDateTime dateTime;

    public FilterExcludeDepartureBeforeCurrentTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight ->
                        flight.getSegments().stream()
                                .anyMatch(segment -> segment.getDepartureDate().isAfter(dateTime)))
                .toList();
    }
}
