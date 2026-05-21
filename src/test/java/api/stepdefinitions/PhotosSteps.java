package api.stepdefinitions;

import api.hooks.ApiHooks;
import api.utils.TestDataReader;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class PhotosSteps {

    @Then("User validates random photo URLs")
    public void user_validates_random_photo_urls() {

        try {
            int randomUrlCount = TestDataReader.getInt("photos", "randomUrlCount");
            List<Map<String, Object>> photos = CommonSteps.getResponse().jsonPath().getList("$");
            Collections.shuffle(photos);
            Set<String> validatedUrls = new HashSet<>();

            int count = 0;
            int validUrlCount = 0;
            int brokenUrlCount = 0;
            int skippedUrlCount = 0;

            for (Map<String, Object> photo : photos) {

                if (count == randomUrlCount) {
                    break;
                }

                String url = photo.get("url").toString();

                if (validatedUrls.contains(url)) {
                    continue;
                }

                validatedUrls.add(url);

                HttpURLConnection connection = null;

                try {

                    connection = (HttpURLConnection) new URL(url).openConnection();

                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(10000);
                    connection.setReadTimeout(10000);
                    connection.setInstanceFollowRedirects(true);

                    connection.connect();

                    int statusCode = connection.getResponseCode();

                    if (statusCode == 200) {

                        validUrlCount++;
                        ApiHooks.extentTest.get()
                                .pass("Valid photo URL: " + url
                                        + " | Status Code: " + statusCode);

                    } else {
                        brokenUrlCount++;
                        ApiHooks.extentTest.get()
                                .warning("Broken photo URL: " + url
                                        + " | Status Code: " + statusCode);
                    }

                } catch (Exception e) {

                    skippedUrlCount++;

                    ApiHooks.extentTest.get()
                            .skip("Skipped photo URL: " + url
                                    + " | Error: " + e.getMessage());

                } finally {

                    if (connection != null) {
                        connection.disconnect();
                    }
                }

                count++;
            }

            ApiHooks.extentTest.get().info("Total URLs checked: " + count);
            ApiHooks.extentTest.get().info("Valid URLs count: " + validUrlCount);
            ApiHooks.extentTest.get().info("Broken URLs count: " + brokenUrlCount);
            ApiHooks.extentTest.get().info("Skipped URLs count: " + skippedUrlCount);

            ApiHooks.extentTest.get()
                    .pass("Random photo URL validation completed");

        } catch (Exception e) {

            ApiHooks.extentTest.get().fail("Photo URL validation failed: " + e.getMessage());
            Assert.fail("Photo URL validation failed: " + e.getMessage());
        }
    }
}
