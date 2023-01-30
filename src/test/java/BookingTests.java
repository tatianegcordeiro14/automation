import Entities.Booking;
import Entities.bookingdates;
import Entities.User;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.config.LogConfig.logConfig;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class BookingTests {
    public static  Faker faker;
    private static RequestSpecification request;
    private static RequestSpecification requestNoAuth;
    private static Booking booking;
    private static bookingdates bookingdates;
    private static User user;


    @BeforeAll
    public static void Setup(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        faker = new Faker();
        user = new User(faker.name().username(),
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().safeEmailAddress(),
                faker.internet().password(8,10),
                faker.phoneNumber().toString());

        // talvez usar o faker para gerar a data
        bookingdates = new bookingdates("2018-01-02", "2018-01-03");

        booking = new Booking(user.getFirstname(), user.getLastname(),
                (float)faker.number().randomDouble(2,50,100000),
                true, bookingdates,"");
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new ErrorLoggingFilter());

    }

    @BeforeEach
    void setRequest(){
        request = given().config(RestAssured.config().logConfig(logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                .contentType(ContentType.JSON)
                .auth().basic("admin", "password123");

        //a linha da request está um pouco diferente do video
    }

    @BeforeEach
    void setRequestNoAuth(){
        requestNoAuth = given().config(RestAssured.config().logConfig(logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                .contentType(ContentType.JSON);

        //a linha da request está um pouco diferente do video
    }

    @Test
    public void getAllBookingsById_returnOk(){

        request = given().config(RestAssured.config().logConfig(logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                .contentType(ContentType.JSON)
                .auth().basic("admin", "password123");

        Response response = request
                                .when()
                                    .get("/booking")
                                .then()
                                    .extract()
                                    .response();
        Assertions.assertNotNull(response);
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    public void getAllBookingsByUserFirstName_BookingExists_returnOk(){
        request
                .when()
                    .queryParam("firstName","Tatiane")
                    .get("/booking")
                .then()
                    .assertThat()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                .and()
                .body("results", hasSize(greaterThan(0)));

    }

    @Test
    public void CreateBooking_WithValidData_returnOk() {

        Booking test = booking;
        requestNoAuth
                .when()
                .body(booking)
                .post("/booking")
                .then()
                .body(matchesJsonSchemaInClasspath("createBookingRequestSchema.json"))
                .and()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON).and().time(lessThan(2000L));
    }
}
