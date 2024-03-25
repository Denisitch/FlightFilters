package com.gridnine.testing;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.FlightBuilder;
import com.gridnine.testing.service.FilterExcludeArrivalEarlierDeparture;
import com.gridnine.testing.service.FilterExcludeDepartureBeforeCurrentTime;
import com.gridnine.testing.service.FilterExcludeTransferMore;
import com.gridnine.testing.service.FlightsFilter;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("Flights for filtration:");
        flights.forEach(System.out::println);

        System.out.println("\nExclude flights until the current moment in time:");
        FilterExcludeDepartureBeforeCurrentTime filterExcludeDepartureBeforeCurrentTime =
                new FilterExcludeDepartureBeforeCurrentTime(LocalDateTime.now());
        filterService(flights, filterExcludeDepartureBeforeCurrentTime);

        System.out.println("\nExclude flights with an arrival date earlier than the departure date:");
        FilterExcludeArrivalEarlierDeparture filterExcludeArrivalEarlierDeparture =
                new FilterExcludeArrivalEarlierDeparture();
        filterService(flights, filterExcludeArrivalEarlierDeparture);

        System.out.println("\nExclude flights with a transfer duration of more than 2 hours:");
        FilterExcludeTransferMore filterExcludeTransferMore =
                new FilterExcludeTransferMore(2);
        filterService(flights, filterExcludeTransferMore);
    }

    private static void filterService(List<Flight> flights, FlightsFilter<Flight> flightsFilter) {
        flightsFilter.filter(flights).forEach(System.out::println);
    }
}
