package se.trafiklab;

import okhttp3.OkHttpClient;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TrafiklabTestConfig {

    @Bean
    public OkHttpClient okHttpClient() {
        return Mockito.mock(OkHttpClient.class);
    }
}
