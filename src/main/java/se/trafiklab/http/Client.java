package se.trafiklab.http;

import okhttp3.Request;

import java.io.IOException;

public interface Client {
     String makeCall (Request request) throws IOException;
}
