package se.trafiklab.bus;

import okhttp3.Request;
import se.trafiklab.http.Client;
import se.trafiklab.http.RequestFactory;
import se.trafiklab.model.JourneyPatternPointOnLineModel;
import se.trafiklab.model.Model;
import se.trafiklab.model.factory.ModelFactory;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;

public class BusServiceImpl implements BusService{

    private RequestFactory requestFactory;
    private Client client;
    private ModelFactory modelFactory;
    private Function<Model<JourneyPatternPointOnLineModel>, List<BusLineInfo>> topTenFunction;

    public BusServiceImpl(RequestFactory requestFactory, Client client, ModelFactory modelFactory, Function<Model<JourneyPatternPointOnLineModel>, List<BusLineInfo>> topTenFunction) {
        this.requestFactory = requestFactory;
        this.client = client;
        this.modelFactory = modelFactory;
        this.topTenFunction = topTenFunction;
    }

    public List<BusLineInfo> getTopTenBusLines() {
        Request request = requestFactory.createJourneyPatternPointOnLine();
        String json = makeCallAndRetrieveJson(request);
        Optional<Model<JourneyPatternPointOnLineModel>> journeyPatternPointOnLineModel = modelFactory.createJourneyPatternPointOnLineModel(json);

        if(journeyPatternPointOnLineModel.isEmpty()) {
            return Collections.emptyList();
        }

        return topTenFunction.apply(journeyPatternPointOnLineModel.get());
    }

    private String makeCallAndRetrieveJson(Request request) {
        try {
            return client.makeCall(request);
        } catch (IOException e) {
            return "";
        }
    }
}
