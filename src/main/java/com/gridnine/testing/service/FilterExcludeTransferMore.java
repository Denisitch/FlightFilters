package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;

import java.util.List;

public class FilterExcludeTransferMore implements FlightsFilter<Flight> {
    private final int transferDuration;

    public FilterExcludeTransferMore(int transferDuration) {
        this.transferDuration = transferDuration;
    }

    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getTransferDurationInHours() < transferDuration)
                .toList();
    }
}
