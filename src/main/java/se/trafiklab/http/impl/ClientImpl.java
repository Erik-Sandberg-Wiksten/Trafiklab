package se.trafiklab.http.impl;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import se.trafiklab.http.Client;

import java.io.IOException;
import java.util.Objects;

public class ClientImpl implements Client {

    private OkHttpClient client;

    public ClientImpl(OkHttpClient client) {
        this.client = client;
    }

    @Override
    public String makeCall(Request request) throws IOException {
        Call call = client.newCall(request);

        try (Response response = call.execute()) {

            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            if(Objects.isNull(response.body())) {
                throw new IOException("Body is null ");
            }

            return response.body().string();
        }
    }
}
