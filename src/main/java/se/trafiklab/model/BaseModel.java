package se.trafiklab.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseModel<T> {
    @JsonProperty("StatusCode")
    private int statusCode;
    @JsonProperty("ExecutionTime")
    private int executionTime;
    @JsonProperty("Message")
    private String message;
    @JsonProperty("ResponseData")
    private ResponseData<T> responseData;

    public int getStatusCode() {
        return statusCode;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public String getMessage() {
        return message;
    }

    public ResponseData<T> getResponseData() {
        return responseData;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "statusCode=" + statusCode +
                ", executionTime=" + executionTime +
                ", message='" + message + '\'' +
                ", responseData=" + responseData +
                '}';
    }
}
