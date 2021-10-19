package se.trafiklab.http;

import okhttp3.Request;
import okhttp3.Response;

public interface Client {
     Response makeCall (Request request);
}
