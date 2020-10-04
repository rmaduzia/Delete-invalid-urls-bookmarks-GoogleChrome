import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Objects;

public class DeleteInvalidBookMarksApplication {

    public static void main(String[] args) throws IOException {

        String fileName = "bookmarks_testing.json";

        ParseBookMarks parseBookMarks = new ParseBookMarks();
        //parseBookMarks.parseJsonObject();

        String contents = new String((Files.readAllBytes(Paths.get(fileName))));
        JSONObject jsonObject = new JSONObject(contents);


        UrlValidator urlValidator = new UrlValidator();
        System.out.println(urlValidator.isUrlValid("http://google.pl/"));


    }
}





