package se.trafiklab.bus.function;

import org.jetbrains.annotations.NotNull;
import se.trafiklab.bus.BusLineInfo;
import se.trafiklab.model.JourneyPatternPointOnLineModel;
import se.trafiklab.model.Model;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TopTenBusLineFunction implements Function<Model<JourneyPatternPointOnLineModel>, List<BusLineInfo>> {

    private Function<Model<JourneyPatternPointOnLineModel>, Map<Integer, Set<Integer>>> byLineNumberFunction;

    public TopTenBusLineFunction(Function<Model<JourneyPatternPointOnLineModel>, Map<Integer, Set<Integer>>> byLineNumberFunction) {
        this.byLineNumberFunction = byLineNumberFunction;
    }

    @Override
    public List<BusLineInfo> apply(Model<JourneyPatternPointOnLineModel> journeyPatternPointOnLineModelModel) {
        Map<Integer, Set<Integer>> map = byLineNumberFunction.apply(journeyPatternPointOnLineModelModel);
        return getTopTenAggregatedBusLinesByNumberOfStops(map);
    }

    private List<BusLineInfo> getTopTenAggregatedBusLinesByNumberOfStops(Map<Integer, Set<Integer>> map) {
        return map.entrySet()
                .stream()
                .sorted(Comparator.comparing(e -> e.getValue().size(), Comparator.reverseOrder()))
                .limit(10)
                .map(entry -> BusLineInfo.from(entry.getKey(), entry.getValue().size()))
                .collect(Collectors.toList());
    }

}
