package se.trafiklab.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DataModelType {

    SITE("Site"),
    STOP_POINT("StopPoint"),
    LINE("Line"),
    JOURNEY_PATTERN_POINT_ON_LINE("JourneyPatternPointOnLine"),
    TRANSPORT_MODE("TransportMode");



    private String name;

    DataModelType(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
