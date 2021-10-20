package se.trafiklab.bus.function;

import se.trafiklab.model.Model;
import se.trafiklab.model.StopPointModel;

import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class LineNumberToLineNameFunction implements BiFunction<Model<StopPointModel>, Set<Integer>, Set<String>> {
    @Override
    public Set<String> apply(Model<StopPointModel> stopPointModelModel, Set<Integer> stopNumbers) {
        Map<Integer, String> stopPointInfo = stopPointModelModel.getResponseData().getResult().stream()
                .collect(Collectors.toMap(StopPointModel::getStopPointNumber, StopPointModel::getStopPointName));

        return stopNumbers.stream()
                .map(stopPointInfo::get)
                .collect(Collectors.toSet());
    }
}
