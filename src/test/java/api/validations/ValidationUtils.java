package api.validations;

import io.restassured.response.Response;
import org.testng.Assert;

public class ValidationUtils {

    public static void validateStatusCode(Response response, int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "Status code mismatch");
    }

    public static void validateResponseNotNull(Response response) {
        Assert.assertNotNull(response.getBody(), "Response body is null");
    }

    public static void validateTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }
}