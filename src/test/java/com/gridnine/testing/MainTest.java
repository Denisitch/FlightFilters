package com.gridnine.testing;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.FlightBuilder;
import com.gridnine.testing.service.FilterExcludeArrivalEarlierDeparture;
import com.gridnine.testing.service.FilterExcludeDepartureBeforeCurrentTime;
import com.gridnine.testing.service.FilterExcludeTransferMore;
import com.gridnine.testing.service.FlightsFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private List<Flight> flights;

    @BeforeEach
    void setUp() {
        flights = FlightBuilder.createFlights();
    }

    @Test
    void testDepartureBefore() {
        LocalDateTime dateTime = LocalDateTime.now();
        FlightsFilter<Flight> flightFilter = new FilterExcludeDepartureBeforeCurrentTime(dateTime);
        List<Flight> filterFlights = flightFilter.filter(flights);
        assertEquals(5, filterFlights.size());
        assertFalse(filterFlights.contains(flights.get(2)));
    }

    @Test
    void testArrivalBeforeDeparture() {
        FlightsFilter<Flight> flightFilter = new FilterExcludeArrivalEarlierDeparture();
        List<Flight> filterFlights = flightFilter.filter(flights);
        assertEquals(5, filterFlights.size());
        assertFalse(filterFlights.contains(flights.get(3)));
    }

    @Test
    void testOverTwoHoursOnEarth() {
        FlightsFilter<Flight> flightFilter = new FilterExcludeTransferMore(2);
        List<Flight> filterFlights = flightFilter.filter(flights);
        assertEquals(4, filterFlights.size());
        assertFalse(filterFlights.contains(flights.get(4)));
        assertFalse(filterFlights.contains(flights.get(5)));
    }
}