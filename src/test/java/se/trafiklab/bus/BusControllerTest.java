package se.trafiklab.bus;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.trafiklab.GlobalControllerExceptionHandler;
import se.trafiklab.TrafiklabApplication;
import se.trafiklab.TrafiklabTestConfig;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;


@ContextConfiguration(classes = { TrafiklabApplication.class, TrafiklabTestConfig.class })
@ExtendWith(SpringExtension.class)
class BusControllerTest {

    @Autowired
    private BusController busController;
    @Autowired
    private GlobalControllerExceptionHandler exceptionHandler;

    @Autowired
    private OkHttpClient okHttpClient;


    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("/busline/aaa Return 400 in case of an invalid parameter")
    public void shouldGetBadRequestFromInvalidLineNumber() {
        // given
        MockMvcRequestSpecification givenRestAssuredSpecification = RestAssuredMockMvc.given()
                .standaloneSetup(busController);

        // when
        MockMvcResponse response = givenRestAssuredSpecification.when().get("/busline/aaaa");

        // then
        response.then().statusCode(400);
    }

    @Test
    @DisplayName("/busline/123 Return 400 in case of an IOException")
    public void shouldGetBadRequestFromIOExceptionInClient() throws IOException {
        // given
        Call call = Mockito.mock(Call.class);
        BDDMockito.given(okHttpClient.newCall(any())).willReturn(call);
        BDDMockito.given(call.execute()).willThrow(IOException.class);
        MockMvcRequestSpecification givenRestAssuredSpecification = RestAssuredMockMvc.given()
                .standaloneSetup(busController, exceptionHandler);

        // when
        MockMvcResponse response = givenRestAssuredSpecification.when().get("/busline/123");

        // then
        response.then().statusCode(400);
    }

}