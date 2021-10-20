package se.trafiklab.bus;

import java.util.Collections;
import java.util.Set;

public class BusLineRouteInfo {
    private Integer lineNumber;
    private Set<String> stops;

    private BusLineRouteInfo(Integer lineNumber, Set<String> stops) {
        this.lineNumber = lineNumber;
        this.stops = stops;
    }

    public static BusLineRouteInfo from(Integer lineNumber,  Set<String> stops) {
        return new BusLineRouteInfo(lineNumber, stops);
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public Set<String> getStops() {
        return Collections.unmodifiableSet(stops);
    }
}
