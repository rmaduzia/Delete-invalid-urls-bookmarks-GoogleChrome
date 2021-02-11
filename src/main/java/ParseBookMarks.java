import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;



public class ParseBookMarks {


    public void parseJsonObject(String filePath) {
        try {
            JSONObject jsonObject = getJsonObject(filePath);
            JSONObject root = (JSONObject) jsonObject.get("roots");
            for (String string : (Set<String>) root.keySet()) {
                JSONObject newJsonObject = (JSONObject) root.get(string);
                printChildren(newJsonObject);
            }
            System.out.println(jsonObject.toJSONString().replaceAll("\\\\",""));


            writeJsonToFile(jsonObject);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JSONObject getJsonObject(String filePath) throws Exception {
        FileReader reader = new FileReader(filePath);
        return (JSONObject) new JSONParser().parse(reader);
    }

    private void printUrls(JSONArray children) {
        UrlValidator urlValidator = new UrlValidator();

        for (int i=0; i<children.size(); i++){
            JSONObject jsonObject = (JSONObject) children.get(i);
            printChildren(jsonObject);
            String url = (String) jsonObject.get("url");
            if (Objects.nonNull(url)) {
                boolean result = urlValidator.isUrlValid(url, ConfigApp.SEARCH_STRING_IN_BODY);
                if (!result){
                    System.out.println("deleting " + url);
                    children.remove(i);
                }

            }
        }
    }

    private void writeJsonToFile(JSONObject jsonObject){

        try (FileWriter file = new FileWriter(ConfigApp.OUTPUT_FILE_NAME)) {

            ObjectMapper myObjectMapper = new ObjectMapper();
            myObjectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            myObjectMapper.writeValue(file, jsonObject);

        } catch (IOException e) {
            e.printStackTrace();
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
