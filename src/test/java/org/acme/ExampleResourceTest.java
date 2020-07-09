package org.acme;

import io.quarkus.test.junit.QuarkusTest;

import org.acme.junitExtension.Unfriendly;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExampleResourceTest {

    @Test
    @Order(1)
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("hello my friend!"));
    }

    @Test
    @Unfriendly
    @Order(2)
    public void testHelloEndpointUnfriendly() {
        System.out.println("Inside Test CL: " + Thread.currentThread().getContextClassLoader().toString());
        given()
            .when().get("/hello")
            .then()
            .statusCode(200)
            .body(is("hello my foe!"));
    }


}