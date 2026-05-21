package api.stepdefinitions;

import api.hooks.ApiHooks;
import api.utils.TestDataReader;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class PostsSteps {

    @Then("User prints posts where title or body contains keyword from test data")
    public void user_prints_posts_where_title_or_body_contains_keyword_from_test_data() {

        try {

            String keyword = TestDataReader.getString("posts", "keyword");
            List<Map<String, Object>> posts = CommonSteps.getResponse().jsonPath().getList("$");

            int matchedPostsCount = 0;
            for (Map<String, Object> post : posts) {
                String title = post.get("title").toString().toLowerCase();
                String body = post.get("body").toString().toLowerCase();
                if (title.contains(keyword.toLowerCase()) || body.contains(keyword.toLowerCase())) {
                    matchedPostsCount++;
                    ApiHooks.extentTest.get().pass("Matched post found: " + post);
                }
            }

            ApiHooks.extentTest.get().info("Total matched posts count: " + matchedPostsCount);

            if (matchedPostsCount == 0) {
                ApiHooks.extentTest.get().fail("No posts found containing keyword: " + keyword);

                Assert.fail("No posts found containing keyword: " + keyword);
            }

            ApiHooks.extentTest.get().pass("Post keyword validation completed");

        } catch (Exception e) {
            ApiHooks.extentTest.get().fail("Post keyword validation failed: " + e.getMessage());

            Assert.fail("Post keyword validation failed: " + e.getMessage());
        }
    }
}
