public class DeleteInvalidBookMarksApplication {

    public static void main(String[] args) {

        ConfigApp.SEARCH_STRING_IN_BODY.add("This video was deleted.");
        ConfigApp.SEARCH_STRING_IN_BODY.add("Video was deleted");
        ConfigApp.SEARCH_STRING_IN_BODY.add("Video was deleted.");
        ConfigApp.SEARCH_STRING_IN_BODY.add("Video has been flagged for verification in accordance with our trust and safety policy");
        ConfigApp.SEARCH_STRING_IN_BODY.add("Film Nie Jest Już Dostępny.");
        ConfigApp.SEARCH_STRING_IN_BODY.add("Film został usunięty");
        ConfigApp.SEARCH_STRING_IN_BODY.add("Video Disabled");
        ConfigApp.SEARCH_STRING_IN_BODY.add("Film został usunięty na prośbę");
        ConfigApp.SEARCH_STRING_IN_BODY.add("Video has been flagged for verification.");
        ConfigApp.SEARCH_STRING_IN_BODY.add("Trwa konwersja filmu");

        ParseBookMarks parseBookMarks = new ParseBookMarks();
        parseBookMarks.parseJsonObject(ConfigApp.INPUT_FILE_NAME);
    }
}





