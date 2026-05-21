package api.stepdefinitions;

import api.hooks.ApiHooks;
import api.services.ApiService;
import api.validations.ValidationUtils;
import api.utils.RequestBuilder;
import api.utils.ResponseBuilder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class CommonSteps {

    private static final Logger log = LogManager.getLogger(CommonSteps.class);
    private static final ThreadLocal<Response> response = new ThreadLocal<>();
    public static Response getResponse() {
        return response.get();
    }
    public static void clearResponse() {
        response.remove();
    }

    ApiService apiService = new ApiService();

    @Given("User hits the {string} endpoint")
    public void user_hits_the_endpoint(String endpoint) {
        try {
            response.set(apiService.getRequest(RequestBuilder.getRequestSpec(), endpoint));
            log.info("Endpoint requested: {}", endpoint);
            ApiHooks.extentTest.get().info("Endpoint requested: " + endpoint);
        } catch (Exception e) {
            log.error("Endpoint request failed: {} | Error: {}", endpoint, e.getMessage());
            ApiHooks.extentTest.get().fail("Endpoint request failed: " + endpoint + " | Error: " + e.getMessage());
            Assert.fail("Endpoint request failed: " + e.getMessage());
        }
    }
    @Then("User validates response status code as {int}")
    public void user_validates_response_status_code_as(Integer expectedStatusCode) {
        try {
            getResponse().then().spec(ResponseBuilder.getResponseSpec(expectedStatusCode));
            log.info("Status code validated: {}", expectedStatusCode);
            ApiHooks.extentTest.get().pass("Status code validated: " + expectedStatusCode);
        } catch (Exception e) {
            log.error("Status code validation failed: {}", e.getMessage());
            ApiHooks.extentTest.get().fail("Status code validation failed: " + e.getMessage());
            Assert.fail("Status code validation failed: " + e.getMessage());
        }
    }

    @Then("User prints total items count")
    public void user_prints_total_items_count() {
        try {
            int totalCount = getResponse().jsonPath().getList("$").size();
            log.info("Total items count: {}", totalCount);
            ApiHooks.extentTest.get().pass("Total items count: " + totalCount);
        } catch (Exception e) {
            log.error("Total item count validation failed: {}", e.getMessage());
            ApiHooks.extentTest.get().fail("Total item count validation failed: " + e.getMessage());
            Assert.fail("Total item count validation failed: " + e.getMessage());
        }
    }
}
