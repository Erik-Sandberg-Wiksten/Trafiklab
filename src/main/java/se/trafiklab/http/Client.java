package se.trafiklab.http;

import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public interface Client {
     String makeCall (Request request) throws IOException;
}
