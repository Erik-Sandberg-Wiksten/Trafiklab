package se.trafiklab.bus;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.trafiklab.GlobalControllerExceptionHandler;
import se.trafiklab.TrafiklabApplication;
import se.trafiklab.TrafiklabTestConfig;
import se.trafiklab.http.RequestFactory;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;


@ContextConfiguration(classes = { TrafiklabApplication.class, TrafiklabTestConfig.class })
@ExtendWith(SpringExtension.class)
class BusControllerTest {

    @Autowired
    private BusController busController;
    @Autowired
    private GlobalControllerExceptionHandler exceptionHandler;

    @Autowired
    private OkHttpClient okHttpClient;
    @Autowired
    private RequestFactory requestFactory;


    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("/busline/aaa Return 400 in case of an invalid parameter")
    public void shouldGetBadRequestFromInvalidLineNumber() {
        MockMvcRequestSpecification givenRestAssuredSpecification = RestAssuredMockMvc.given()
                .standaloneSetup(busController);

        MockMvcResponse response = givenRestAssuredSpecification.when().get("/busline/aaaa");

        response.then().statusCode(400);
    }

    @Test
    @DisplayName("/busline/123 Return 400 in case of an IOException")
    public void shouldGetBadRequestFromIOExceptionInClient() throws IOException {
        MockWebServer serviceMock = new MockWebServer();
        serviceMock.start();
        HttpUrl baseUrl = serviceMock.url("/busline");
        serviceMock.enqueue(new MockResponse()
                .addHeader("Accept-Encoding", "gzip, deflate")
                .setBody(""));

        Request request = new Request.Builder()
                .url(baseUrl.url())
                .addHeader("Accept-Encoding", "gzip, deflate")
                .build();
        BDDMockito.given(requestFactory.createJourneyPatternPointOnLine()).willReturn(request);
        MockMvcRequestSpecification givenRestAssuredSpecification = RestAssuredMockMvc.given()
                .standaloneSetup(busController, exceptionHandler);

        MockMvcResponse response = givenRestAssuredSpecification.when().get("/busline/123");

        response.then().statusCode(400);
    }

    @Test
    @DisplayName("/busline Return line number 1 with 2 stops")
    public void shouldGetLineNumber1() throws IOException {
        MockWebServer serviceMock = new MockWebServer();
        serviceMock.start();
        HttpUrl baseUrl = serviceMock.url("/busline");
        serviceMock.enqueue(new MockResponse()
                .addHeader("Accept-Encoding", "gzip, deflate")
                .setBody("{\n" +
                        "    \"StatusCode\": 0,\n" +
                        "    \"Message\": null,\n" +
                        "    \"ExecutionTime\": 348,\n" +
                        "    \"ResponseData\": {\n" +
                        "        \"Version\": \"2021-10-21 00:08\",\n" +
                        "        \"Type\": \"JourneyPatternPointOnLine\",\n" +
                        "        \"Result\": [\n" +
                        "            {\n" +
                        "                \"LineNumber\": \"1\",\n" +
                        "                \"DirectionCode\": \"1\",\n" +
                        "                \"JourneyPatternPointNumber\": \"10008\",\n" +
                        "                \"LastModifiedUtcDateTime\": \"2018-02-16 00:00:00.000\",\n" +
                        "                \"ExistsFromDate\": \"2018-02-16 00:00:00.000\"\n" +
                        "            },\n" +
                        "\t\t\t{\n" +
                        "                \"LineNumber\": \"1\",\n" +
                        "                \"DirectionCode\": \"1\",\n" +
                        "                \"JourneyPatternPointNumber\": \"10009\",\n" +
                        "                \"LastModifiedUtcDateTime\": \"2018-02-17 00:00:00.000\",\n" +
                        "                \"ExistsFromDate\": \"2018-02-17 00:00:00.000\"\n" +
                        "            }\n" +
                        "\t\t]\n" +
                        "\t}\n" +
                        "}"));

        Request request = new Request.Builder()
                .url(baseUrl.url())
                .addHeader("Accept-Encoding", "gzip, deflate")
                .build();
        BDDMockito.given(requestFactory.createJourneyPatternPointOnLine()).willReturn(request);

        MockMvcRequestSpecification givenRestAssuredSpecification = RestAssuredMockMvc.given()
                .standaloneSetup(busController, exceptionHandler);

        MockMvcResponse response = givenRestAssuredSpecification.when().get("/busline");

        response.then().statusCode(200);
        response.then().body("[0].lineNumber", equalTo(1));
        response.then().body("[0].nrOfStops", equalTo(2));

        serviceMock.shutdown();
    }

}