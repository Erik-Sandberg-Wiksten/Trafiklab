package se.trafiklab.bus;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import se.trafiklab.http.Client;
import se.trafiklab.http.RequestFactory;
import se.trafiklab.http.impl.ClientImpl;
import se.trafiklab.http.impl.RequestFactoryImpl;
import se.trafiklab.model.JourneyPatternPointOnLineModel;
import se.trafiklab.model.Model;
import se.trafiklab.model.factory.ModelFactory;
import se.trafiklab.model.factory.impl.ModelFactoryImpl;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class BusServiceImpl implements BusService{

    private RequestFactory requestFactory;

    public List<BusLineInfo> getTopTenBusLines() throws IOException {
        RequestFactory requestFactory = new RequestFactoryImpl("db416c90002e4ae5b0b22675c8590977");
        Request request = requestFactory.createJourneyPatternPointOnLine();
        OkHttpClient okHttpClient = new OkHttpClient();
        Client client = new ClientImpl(okHttpClient);
        String json = client.makeCall(request);
        ObjectMapper mapper = new ObjectMapper();
        ModelFactory modelFactory = new ModelFactoryImpl(mapper);
        Model<JourneyPatternPointOnLineModel> journeyPatternPointOnLineModel = modelFactory.createJourneyPatternPointOnLineModel(json);

        Map<Integer, Set<Integer>> map = journeyPatternPointOnLineModel.getResponseData().getResult().stream()
                .collect(Collectors.groupingBy(
                        JourneyPatternPointOnLineModel::getLineNumber,
                        Collectors.mapping(
                                JourneyPatternPointOnLineModel::getJourneyPatternPointNumber, Collectors.toSet())));
        return map.entrySet()
                .stream()
                .sorted(Comparator.comparing(e -> e.getValue().size(), Comparator.reverseOrder()))
                .limit(10)
                .map(entry -> BusLineInfo.from(entry.getKey(), entry.getValue().size()))
                .collect(Collectors.toList());

    }
}
