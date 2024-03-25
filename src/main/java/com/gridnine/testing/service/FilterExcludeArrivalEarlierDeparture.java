package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;

import java.util.List;

public class FilterExcludeArrivalEarlierDeparture implements FlightsFilter<Flight> {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .noneMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())))
                .toList();
    }
}
