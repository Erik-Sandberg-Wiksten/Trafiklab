package se.trafiklab.bus.function;

import se.trafiklab.model.JourneyPatternPointOnLineModel;
import se.trafiklab.model.Model;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GroupByLineNumberFunction implements Function<Model<JourneyPatternPointOnLineModel>, Map<Integer, Set<Integer>>> {

    @Override
    public Map<Integer, Set<Integer>> apply(Model<JourneyPatternPointOnLineModel> journeyPatternPointOnLineModelModel) {
        return journeyPatternPointOnLineModelModel.getResponseData().getResult().stream()
                .collect(Collectors.groupingBy(
                        JourneyPatternPointOnLineModel::getLineNumber,
                        Collectors.mapping(
                                JourneyPatternPointOnLineModel::getJourneyPatternPointNumber, Collectors.toSet())));
    }
}
