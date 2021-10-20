package se.trafiklab.http;

import okhttp3.Request;

public interface RequestFactory {
    Request createJourneyPatternPointOnLine();
}
