package se.trafiklab.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StopAreaType {
    BUSS_TERM("BUSTERM"),
    TRAM_STN("TRAMSTN"),
    METRO_STN("METROSTN"),
    RAILW_STN("RAILWSTN"),
    SHIP_BER("SHIPBER"),
    FERRY_BER("FERRYBER"),
    UNKNOWN("UNKNOWN");

    private String code;

    StopAreaType(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }
}
