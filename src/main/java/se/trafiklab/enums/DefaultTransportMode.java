package se.trafiklab.enums;

public enum DefaultTransportMode {
    BUS("BUS"),
    METRO("METRO"),
    TRAM("TRAM"),
    TRAIN("TRAIN"),
    SHIP("SHIP"),
    FERRY("FERRY");

    private String code;

    DefaultTransportMode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
