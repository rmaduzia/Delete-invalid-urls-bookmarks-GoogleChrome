import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Objects;
import java.util.Set;

public class ParseBookMarks {

    public void parseJsonObject() {
        try {
            String filePath = "bookmarks_testing.json";
            JSONObject jsonObject = getJsonObject(filePath);
            JSONObject root = (JSONObject) jsonObject.get("roots");
            for (String string : (Set<String>) root.keySet()) {
                JSONObject newJsonObject = (JSONObject) root.get(string);
                printChildren(newJsonObject);
            }
            System.out.println("1" + jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JSONObject getJsonObject(String filePath) throws Exception {
        FileReader reader = new FileReader(filePath);
        return (JSONObject) new JSONParser().parse(reader);
    }

    private void printUrls(JSONArray children) {
        for (int i=0; i<children.size(); i++){
            JSONObject jsonObject = (JSONObject) children.get(i);
            printChildren(jsonObject);
            String url = (String) jsonObject.get("url");
            if (Objects.nonNull(url)) {
                System.out.println(url);
                if (url.contains("https://sekurak.pl/")){
                    System.out.println("tak");
                    children.remove(i);

                }
            }
        }
    }

    private void printChildren(JSONObject jsonObject) {
        if (isChildren(jsonObject)) {
            printUrls(getChildren(jsonObject));
        }
    }

    private boolean isChildren(JSONObject jsonObject) {
        return jsonObject.containsKey("children");
    }

    private JSONArray getChildren(JSONObject jsonObject) {
        return (JSONArray) jsonObject.get("children");
    }
}
