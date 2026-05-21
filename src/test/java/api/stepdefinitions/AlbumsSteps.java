package api.stepdefinitions;

import api.hooks.ApiHooks;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AlbumsSteps {
    @Then("User filters albums where id is not divisible by userId")
    public void user_filters_albums_where_id_is_not_divisible_by_user_id() {
        try {
            List<Map<String, Object>> albums = CommonSteps.getResponse().jsonPath().getList("$");
            List<Map<String, Object>> filteredAlbums = new ArrayList<>();
            for (Map<String, Object> album : albums) {
                int userId = Integer.parseInt(album.get("userId").toString());
                int id = Integer.parseInt(album.get("id").toString());
                if (id % userId != 0) {
                    filteredAlbums.add(album);
                }
            }
            ApiHooks.extentTest.get().pass("Filtered albums count: " + filteredAlbums.size());
        } catch (Exception e) {
            ApiHooks.extentTest.get().fail("Album filtering failed: " + e.getMessage());
            Assert.fail("Album filtering failed: " + e.getMessage());
        }
    }
}
