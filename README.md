# Delete-invalid-urls-bookmarks-GoogleChrome



## How to run:

Set Up 2 variables in ConfigApp:
-INPUT_FILE_NAME - Input Json BookMarks
-OUTPUT_FILE_NAME - Output Json BookMarks

You can also add values to look for, just add them to List: ConfigApp.SEARCH_STRING_IN_BODY

Script considers url as correct if server don't contains any String of List (ConfigApp.SEARCH_STRING_IN_BODY) and return any of this Http Status code:
200, 301, 302
