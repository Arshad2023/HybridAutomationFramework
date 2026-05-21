package api.stepdefinitions;

import api.hooks.ApiHooks;
import api.utils.TestDataReader;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class UsersSteps {

    @Then("User prints user details whose company name is from test data")
    public void user_prints_user_details_whose_company_name_is_from_test_data() {

        try {
            String companyName =
                    TestDataReader.getString(
                            "users",
                            "companyName"
                    );

            List<Map<String, Object>> users =
                    CommonSteps.getResponse().jsonPath().getList("$");

            boolean userFound = false;

            for (Map<String, Object> user : users) {

                Map<String, Object> company = (Map<String, Object>) user.get("company");

                String actualCompanyName =
                        company.get("name").toString();

                if (actualCompanyName.equalsIgnoreCase(companyName)) {

                    userFound = true;

                    Map<String, Object> address = (Map<String, Object>) user.get("address");

                    String name = user.get("name").toString();

                    String username = user.get("username").toString();

                    String email =
                            user.get("email").toString();

                    String city =
                            address.get("city").toString();

                    String phone =
                            user.get("phone").toString();

                    ApiHooks.extentTest.get()
                            .pass("User details found");

                    ApiHooks.extentTest.get()
                            .info("Name: " + name);

                    ApiHooks.extentTest.get()
                            .info("Username: " + username);

                    ApiHooks.extentTest.get()
                            .info("Email: " + email);

                    ApiHooks.extentTest.get()
                            .info("City: " + city);

                    ApiHooks.extentTest.get()
                            .info("Phone: " + phone);
                }
            }

            if (!userFound) {

                ApiHooks.extentTest.get()
                        .fail("No user found for company name: "
                                + companyName);

                Assert.fail(
                        "No user found for company name: "
                                + companyName
                );
            }

            ApiHooks.extentTest.get()
                    .pass("User details validation completed");

        } catch (Exception e) {

            ApiHooks.extentTest.get()
                    .fail("User details validation failed: "
                            + e.getMessage());

            Assert.fail(
                    "User details validation failed: "
                            + e.getMessage()
            );
        }
    }
}
