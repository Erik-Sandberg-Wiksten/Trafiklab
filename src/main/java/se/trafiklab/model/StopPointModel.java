package se.trafiklab.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import se.trafiklab.enums.StopAreaType;

import java.time.LocalDateTime;
import java.util.Objects;

public class StopPointModel {
    @JsonProperty("StopPointNumber")
    private int stopPointNumber;
    @JsonProperty("StopPointName")
    private String stopPointName;
    @JsonProperty("StopAreaNumber")
    private int stopAreaNumber;
    @JsonProperty("LocationNorthingCoordinate")
    private double locationNorthingCoordinate;
    @JsonProperty("LocationEastingCoordinate")
    private double locationEastingCoordinate;
    @JsonProperty("ZoneShortName")
    private String zoneShortName;
    @JsonProperty("StopAreaTypeCode")
    private StopAreaType stopAreaType;
    @JsonProperty("LastModifiedUtcDateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime lastModifiedUtc;
    @JsonProperty("ExistsFromDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime existFrom;

    public int getStopPointNumber() {
        return stopPointNumber;
    }

    public String getStopPointName() {
        return stopPointName;
    }

    public int getStopAreaNumber() {
        return stopAreaNumber;
    }

    public double getLocationNorthingCoordinate() {
        return locationNorthingCoordinate;
    }

    public double getLocationEastingCoordinate() {
        return locationEastingCoordinate;
    }

    public String getZoneShortName() {
        return zoneShortName;
    }

    public StopAreaType getStopAreaType() {
        return stopAreaType;
    }

    public LocalDateTime getLastModifiedUtc() {
        return lastModifiedUtc;
    }

    public LocalDateTime getExistFrom() {
        return existFrom;
    }

    @Override
    public String toString() {
        return "StopPointModel{" +
                "stopPointNumber=" + stopPointNumber +
                ", stopPointName='" + stopPointName + '\'' +
                ", stopAreaNumber=" + stopAreaNumber +
                ", locationNorthingCoordinate=" + locationNorthingCoordinate +
                ", locationEastingCoordinate=" + locationEastingCoordinate +
                ", zoneShortName='" + zoneShortName + '\'' +
                ", stopAreaType=" + stopAreaType +
                ", lastModifiedUtc=" + lastModifiedUtc +
                ", existFrom=" + existFrom +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StopPointModel that = (StopPointModel) o;
        return stopPointNumber == that.stopPointNumber && stopAreaNumber == that.stopAreaNumber && Double.compare(that.locationNorthingCoordinate, locationNorthingCoordinate) == 0 && Double.compare(that.locationEastingCoordinate, locationEastingCoordinate) == 0 && Objects.equals(stopPointName, that.stopPointName) && Objects.equals(zoneShortName, that.zoneShortName) && stopAreaType == that.stopAreaType && Objects.equals(lastModifiedUtc, that.lastModifiedUtc) && Objects.equals(existFrom, that.existFrom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stopPointNumber, stopPointName, stopAreaNumber, locationNorthingCoordinate, locationEastingCoordinate, zoneShortName, stopAreaType, lastModifiedUtc, existFrom);
    }
}
