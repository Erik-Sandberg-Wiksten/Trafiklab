package se.trafiklab;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.trafiklab.bus.BusController;
import se.trafiklab.http.Client;
import se.trafiklab.http.impl.ClientImpl;
import se.trafiklab.model.Model;
import se.trafiklab.model.JourneyPatternPointOnLineModel;

import java.io.IOException;

@SpringBootApplication
@RestController
public class TrafiklabApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrafiklabApplication.class, args);
    }

    @GetMapping("/hello")
    public Model<JourneyPatternPointOnLineModel> sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Client clientImpl = new ClientImpl(client);

        Request request = new Request.Builder()
                .url("https://api.sl.se/api2/LineData.json?model=JourneyPatternPointOnLine&DefaultTransportModeCode=BUS&key=db416c90002e4ae5b0b22675c8590977")
                .addHeader("Accept-Encoding", "gzip, deflate")
                .build();

        String bodyJson = clientImpl.makeCall(request);

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        return mapper.readValue(bodyJson, new TypeReference<>() {
        });

    }
}
