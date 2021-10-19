package se.trafiklab.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class JourneyPatternPointOnLineModel {
    @JsonProperty("LineNumber")
    private int lineNumber;
    @JsonProperty("DirectionCode")
    private int directionCode;
    @JsonProperty("JourneyPatternPointNumber")
    private int journeyPatternPointNumber;
    @JsonProperty("LastModifiedUtcDateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime lastModifiedUtc;
    @JsonProperty("ExistsFromDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime existFrom;

    public int getLineNumber() {
        return lineNumber;
    }

    public int getDirectionCode() {
        return directionCode;
    }

    public int getJourneyPatternPointNumber() {
        return journeyPatternPointNumber;
    }

    public LocalDateTime getLastModifiedUtc() {
        return lastModifiedUtc;
    }

    public LocalDateTime getExistFrom() {
        return existFrom;
    }

    @Override
    public String toString() {
        return "JourneyPatternPointOnLineModel{" +
                "lineNumber=" + lineNumber +
                ", directionCode=" + directionCode +
                ", journeyPatternPointNumber=" + journeyPatternPointNumber +
                ", lastModifiedUtc=" + lastModifiedUtc +
                ", existFrom=" + existFrom +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JourneyPatternPointOnLineModel that = (JourneyPatternPointOnLineModel) o;
        return lineNumber == that.lineNumber && directionCode == that.directionCode && journeyPatternPointNumber == that.journeyPatternPointNumber && Objects.equals(lastModifiedUtc, that.lastModifiedUtc) && Objects.equals(existFrom, that.existFrom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lineNumber, directionCode, journeyPatternPointNumber, lastModifiedUtc, existFrom);
    }
}
