package se.trafiklab;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.trafiklab.http.RequestFactory;
import se.trafiklab.http.impl.RequestFactoryImpl;

@Configuration
public class TrafiklabTestConfig {

    @Bean
    public RequestFactory requestFactory() {
        return Mockito.mock(RequestFactoryImpl.class);
    }
}
