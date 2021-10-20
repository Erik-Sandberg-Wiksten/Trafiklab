package se.trafiklab.bus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
