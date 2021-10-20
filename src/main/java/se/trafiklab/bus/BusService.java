package se.trafiklab.bus;

import java.util.List;

public interface BusService {
    List<BusLineInfo> getTopTenBusLines();
}
