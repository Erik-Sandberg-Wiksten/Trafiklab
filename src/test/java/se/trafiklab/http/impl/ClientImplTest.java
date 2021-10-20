package se.trafiklab.http.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.junit.jupiter.api.Test;
import se.trafiklab.http.Client;
import se.trafiklab.model.Model;
import se.trafiklab.model.JourneyPatternPointOnLineModel;

import java.io.IOException;

class ClientImplTest {

    @Test
    public void test() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Client clientImpl = new ClientImpl(client);

        Request request = new Request.Builder()
                .url("https://api.sl.se/api2/LineData.json?model=JourneyPatternPointOnLine&DefaultTransportModeCode=BUS&key=db416c90002e4ae5b0b22675c8590977")
                .addHeader("Accept-Encoding", "gzip, deflate")
                .build();

        String bodyJson = clientImpl.makeCall(request);

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        Model<JourneyPatternPointOnLineModel> responseModel = mapper.readValue(bodyJson, new TypeReference<>() {});

        System.out.println(responseModel.getExecutionTime());
    }

}