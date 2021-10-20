package se.trafiklab;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import se.trafiklab.bus.BusLineInfo;
import se.trafiklab.bus.BusService;
import se.trafiklab.bus.BusServiceImpl;
import se.trafiklab.bus.function.GroupByLineNumberFunction;
import se.trafiklab.bus.function.LineNumberToLineNameFunction;
import se.trafiklab.bus.function.TopTenBusLineFunction;
import se.trafiklab.http.Client;
import se.trafiklab.http.RequestFactory;
import se.trafiklab.http.impl.ClientImpl;
import se.trafiklab.http.impl.RequestFactoryImpl;
import se.trafiklab.model.JourneyPatternPointOnLineModel;
import se.trafiklab.model.Model;
import se.trafiklab.model.StopPointModel;
import se.trafiklab.model.factory.ModelFactory;
import se.trafiklab.model.factory.impl.ModelFactoryImpl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public BusService busService() {
        return new BusServiceImpl(requestFactory(),
                client(),
                modelFactory(),
                topTenFunction(),
                groupByLineNumberFunction(),
                lineNumberToLineNameFunction());
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

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        return mapper;
    }

    @Bean
    public ModelFactory modelFactory() {
        return new ModelFactoryImpl(objectMapper());
    }

    @Bean
    public Function<Model<JourneyPatternPointOnLineModel>, List<BusLineInfo>> topTenFunction() {
        return new TopTenBusLineFunction(groupByLineNumberFunction());
    }

    @Bean
    public Function<Model<JourneyPatternPointOnLineModel>, Map<Integer, Set<Integer>>> groupByLineNumberFunction() {
        return new GroupByLineNumberFunction();
    }

    @Bean
    public BiFunction<Model<StopPointModel>, Set<Integer>, Set<String>> lineNumberToLineNameFunction() {
        return new LineNumberToLineNameFunction();
    }

}
