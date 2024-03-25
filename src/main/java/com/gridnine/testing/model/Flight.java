package com.gridnine.testing.model;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class Flight {
    private final List<Segment> segments;

    Flight(final List<Segment> segs) {
        segments = segs;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    @Override
    public String toString() {
        return segments.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
    }

    public int getTransferDurationInHours() {
        int transferDuration = 0;
        for (int i = 1; i < segments.size(); i++) {
            transferDuration += Duration.between(segments.get(i - 1).getArrivalDate(),
                    segments.get(i).getDepartureDate()).toHours();
        }
        return transferDuration;
    }
}
