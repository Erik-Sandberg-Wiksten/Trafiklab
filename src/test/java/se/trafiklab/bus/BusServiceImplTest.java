package se.trafiklab.bus;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BusServiceImplTest {

    @Test
    public void test() throws IOException {
        BusServiceImpl service = new BusServiceImpl();
        service.getTopTenBusLines();
    }

}