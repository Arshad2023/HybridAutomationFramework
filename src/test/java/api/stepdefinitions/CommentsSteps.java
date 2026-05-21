package api.stepdefinitions;

import api.hooks.ApiHooks;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.util.*;

public class CommentsSteps {

    @Then("User groups emails by top level domain")
    public void user_groups_emails_by_top_level_domain() {

        try {
            List<Map<String, Object>> comments = CommonSteps.getResponse().jsonPath().getList("$");
            Map<String, List<String>> tldMap = new HashMap<>();

            for (Map<String, Object> comment : comments) {
                String email = comment.get("email").toString();
                String tld = email.substring(email.lastIndexOf(".") + 1);

                tldMap.putIfAbsent(tld, new ArrayList<>());
                tldMap.get(tld).add(email);
            }
            ApiHooks.extentTest.get().pass("Emails grouped by TLD. Total groups: " + tldMap.size());
            ApiHooks.extentTest.get().info("Grouped email data: " + tldMap);

        } catch (Exception e) {
            ApiHooks.extentTest.get().fail("Email grouping failed: " + e.getMessage());
            Assert.fail("Email grouping failed: " + e.getMessage());
        }
    }
}
