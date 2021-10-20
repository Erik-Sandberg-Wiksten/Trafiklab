package se.trafiklab.bus;

public class BusLineInfo {
    private Integer lineNumber;
    private Integer nrOfStops;

    private BusLineInfo(Integer lineNumber, Integer nrOfStops) {
        this.lineNumber = lineNumber;
        this.nrOfStops = nrOfStops;
    }

    public static BusLineInfo from(Integer lineNumber, Integer nrOfStops) {
        return new BusLineInfo(lineNumber, nrOfStops);
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public Integer getNrOfStops() {
        return nrOfStops;
    }
}
