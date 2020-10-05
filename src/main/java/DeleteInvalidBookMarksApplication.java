import java.util.*;

public class DeleteInvalidBookMarksApplication {

    public static void main(String[] args) {

        String fileName = "bookmarks_testing.json";
        List<String> searchStrings = new ArrayList<>();
        searchStrings.add("This video was deleted.");
        searchStrings.add("Video was deleted");

        ParseBookMarks parseBookMarks = new ParseBookMarks();

        parseBookMarks.parseJsonObject(fileName);


        UrlValidator urlValidator = new UrlValidator();

        //System.out.println("to sie wykonuje " + urlValidator.isUrlValid("http://google.pl", searchStrings));
        //System.out.println("to sie wykonuje " + urlValidator.isUrlValid("http://google.pl/", Collections.emptyList()));



    }
}





