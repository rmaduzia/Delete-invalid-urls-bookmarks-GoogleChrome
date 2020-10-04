import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlValidator {

    public boolean isValid;


    public boolean isUrlValid(String website) {
        isValid = true;
        int code;

        String url = "https://google.pl";

        try {
            URL siteURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            connection.setInstanceFollowRedirects(false);
            connection.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            System.out.println(sb.toString());

            System.out.println(" The content of given url is: "+siteURL.getContent());
            //  System.out.println(body);

            code = connection.getResponseCode();
            System.out.println(code);
            if (code == 200) {
                return isValid;
            } else {
                isValid = false;
            }
        } catch (Exception e) {
            isValid = false;
        }


        return isValid;


    }


}
