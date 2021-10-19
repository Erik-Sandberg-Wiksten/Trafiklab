package se.trafiklab.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import se.trafiklab.enums.DataModelType;

import java.util.Collections;
import java.util.List;

public class ResponseData<T> {
    @JsonProperty("Version")
    private String version;
    @JsonProperty("Type")
    private DataModelType type;
    @JsonProperty("Result")
    private List<T> result;

    public String getVersion() {
        return version;
    }

    public DataModelType getType() {
        return type;
    }

    public List<T> getResult() {
        return Collections.unmodifiableList(result);
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "version='" + version + '\'' +
                ", type=" + type +
                ", result=" + result +
                '}';
    }
}
