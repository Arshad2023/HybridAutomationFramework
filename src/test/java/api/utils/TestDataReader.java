package api.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class TestDataReader {

    private static JsonNode jsonNode;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonNode = mapper.readTree(new File("src/test/resources/testdata/apiTestData.json"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getString(String parentKey, String childKey) {
        return jsonNode.get(parentKey).get(childKey).asText();
    }
    public static int getInt(String parentKey, String childKey) {
        return jsonNode.get(parentKey).get(childKey).asInt();
    }
}