package se.trafiklab;

import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import se.trafiklab.bus.BusController;
import se.trafiklab.bus.BusService;
import se.trafiklab.bus.BusServiceImpl;
import se.trafiklab.http.Client;
import se.trafiklab.http.RequestFactory;
import se.trafiklab.http.impl.ClientImpl;
import se.trafiklab.http.impl.RequestFactoryImpl;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public BusService busService() {
        return new BusServiceImpl(requestFactory());
    }

    @Bean
    public RequestFactory requestFactory() {
        return new RequestFactoryImpl(env.getProperty("trafiklab.api-key"));
    }

    @Bean
    public Client client() {
        return new ClientImpl(okHttpClient());
    }

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }
}
