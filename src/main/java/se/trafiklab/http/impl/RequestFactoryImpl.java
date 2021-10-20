package se.trafiklab.http.impl;

import okhttp3.Request;
import se.trafiklab.enums.DataModelType;
import se.trafiklab.enums.DefaultTransportMode;
import se.trafiklab.http.RequestFactory;

public class RequestFactoryImpl implements RequestFactory {

    private static final String BASE_URL = "https://api.sl.se/api2/LineData.json";
    private final String apiKey;

    public RequestFactoryImpl(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public Request createJourneyPatternPointOnLine() {

        return  new Request.Builder()
                .url(BASE_URL +
                        "?model=" + DataModelType.JOURNEY_PATTERN_POINT_ON_LINE.getName() +
                        "&DefaultTransportModeCode=" + DefaultTransportMode.BUS.getCode() +
                        "&key=" + apiKey)
                .addHeader("Accept-Encoding", "gzip, deflate")
                .build();
    }
}
