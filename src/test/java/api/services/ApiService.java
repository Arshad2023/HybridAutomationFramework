package api.services;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiService {

    public Response getRequest(RequestSpecification requestSpec, String endpoint) {
        return given()
                .spec(requestSpec)
                .when()
                .get("/" + endpoint);
    }

    public Response postRequest(RequestSpecification requestSpec, String endpoint, Object payload) {
        return given()
                .spec(requestSpec)
                .body(payload)
                .when()
                .post("/" + endpoint);
    }

    public Response putRequest(RequestSpecification requestSpec, String endpoint, Object payload) {
        return given()
                .spec(requestSpec)
                .body(payload)
                .when()
                .put("/" + endpoint);
    }

    public Response patchRequest(RequestSpecification requestSpec, String endpoint, Object payload) {
        return given()
                .spec(requestSpec)
                .body(payload)
                .when()
                .patch("/" + endpoint);
    }

    public Response deleteRequest(RequestSpecification requestSpec, String endpoint) {
        return given()
                .spec(requestSpec)
                .when()
                .delete("/" + endpoint);
    }
}