package com.gridnine.testing.service;

import java.util.List;

public interface FlightsFilter<Flight> {
    List<Flight> filter(List<Flight> flights);
}
