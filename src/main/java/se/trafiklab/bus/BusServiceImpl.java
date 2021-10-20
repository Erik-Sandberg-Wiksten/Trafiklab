package se.trafiklab.bus;

import okhttp3.Request;
import se.trafiklab.http.Client;
import se.trafiklab.http.RequestFactory;
import se.trafiklab.model.JourneyPatternPointOnLineModel;
import se.trafiklab.model.Model;
import se.trafiklab.model.StopPointModel;
import se.trafiklab.model.factory.ModelFactory;

import java.io.IOException;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class BusServiceImpl implements BusService{

    private RequestFactory requestFactory;
    private Client client;
    private ModelFactory modelFactory;
    private Function<Model<JourneyPatternPointOnLineModel>, List<BusLineInfo>> topTenFunction;
    private Function<Model<JourneyPatternPointOnLineModel>, Map<Integer, Set<Integer>>> groupByLineNumberFunction;
    private BiFunction<Model<StopPointModel>, Set<Integer>, Set<String>> lineNumberToLineNameFunction;

    public BusServiceImpl(RequestFactory requestFactory, Client client, ModelFactory modelFactory,
                          Function<Model<JourneyPatternPointOnLineModel>, List<BusLineInfo>> topTenFunction,
                          Function<Model<JourneyPatternPointOnLineModel>, Map<Integer, Set<Integer>>> groupByLineNumberFunction,
                          BiFunction<Model<StopPointModel>, Set<Integer>, Set<String>> lineNumberToLineNameFunction) {
        this.requestFactory = requestFactory;
        this.client = client;
        this.modelFactory = modelFactory;
        this.topTenFunction = topTenFunction;
        this.groupByLineNumberFunction = groupByLineNumberFunction;
        this.lineNumberToLineNameFunction = lineNumberToLineNameFunction;
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

    @Override
    public Optional<BusLineRouteInfo> getRouteInfoByLineNumber(Integer lineNumber) {
        Optional<Model<JourneyPatternPointOnLineModel>> journeyPatternPointOnLineModel = getJourneyPatternPointOnLineModel();
        if(journeyPatternPointOnLineModel.isEmpty()) {
            return Optional.empty();
        }

        Map<Integer, Set<Integer>> lineNumberAndTheirStopsMap = groupByLineNumberFunction.apply(journeyPatternPointOnLineModel.get());
        if(!lineNumberAndTheirStopsMap.containsKey(lineNumber)) {
            return Optional.empty();
        }
        Set<Integer> stopNumbers = lineNumberAndTheirStopsMap.get(lineNumber);

        Optional<Model<StopPointModel>> stopPointModel = getStopPointModel();
        if(stopPointModel.isEmpty()) {
            return Optional.empty();
        }

        Set<String> stopPointNames = lineNumberToLineNameFunction.apply(stopPointModel.get(), stopNumbers);

        return Optional.of(BusLineRouteInfo.from(lineNumber, stopPointNames));
    }

    private Optional<Model<StopPointModel>> getStopPointModel() {
        String json = makeCallAndRetrieveJson(requestFactory.createStopPoint());
        return modelFactory.createStopPointModel(json);
    }

    private Optional<Model<JourneyPatternPointOnLineModel>> getJourneyPatternPointOnLineModel() {
        String json = makeCallAndRetrieveJson(requestFactory.createJourneyPatternPointOnLine());
        return modelFactory.createJourneyPatternPointOnLineModel(json);
    }

    private String makeCallAndRetrieveJson(Request request) {
        try {
            return client.makeCall(request);
        } catch (IOException e) {
            return "";
        }
    }
}
