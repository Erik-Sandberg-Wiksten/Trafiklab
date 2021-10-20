package se.trafiklab.bus;

import java.util.List;
import java.util.Optional;

public interface BusService {
    List<BusLineInfo> getTopTenBusLines();
    Optional<BusLineRouteInfo> getRouteInfoByLineNumber(Integer lineNumber);
}
