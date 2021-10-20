package se.trafiklab.bus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class BusController {
    private BusService service;

    public BusController(BusService service) {
        this.service = service;
    }

    @GetMapping("/busline")
    List<BusLineInfo> getBusLineInfo(){
        return service.getTopTenBusLines();
    }

    @GetMapping("/busline/{lineNumber}")
    BusLineRouteInfo getBusLineInfo(@PathVariable(name="lineNumber") Integer lineNumber) throws Exception {
        Optional<BusLineRouteInfo> busLineRouteInfo = service.getRouteInfoByLineNumber(lineNumber);
        if(busLineRouteInfo.isEmpty()) {
            throw new Exception("Line number " + lineNumber + " was not found");
        }
        return busLineRouteInfo.get();
    }

}
