import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class UrlValidator {

    public boolean isValid;


    public boolean isUrlValid(String website, List<String> searchStrings) {
        int code;

        boolean isLookingForString = !searchStrings.isEmpty();

        try {
            URL siteURL = new URL(website);
            //HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            connection.setInstanceFollowRedirects(false);
            //connection.setRequestProperty("User-Agent", "Mozilla/5.0...");

            connection.connect();

            code = connection.getResponseCode();
            System.out.println(code + "   " + website);


            BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            if (code == 200  || code == 302 || code == 301) {
                if (isLookingForString) {
                    isValid = checkIsUrlBodyContentValid(searchStrings, sb.toString());
                }
                isValid = true;
            } else {
                isValid = false;
            }
        } catch (Exception e) {
            isValid = false;
        }
        return isValid;
    }

    public boolean checkIsUrlBodyContentValid(List<String> searchString, String content) {

        for (String value: searchString) {
            if (content.contains(value)) {
                return false;
            }
        }
        return true;
    }
}
