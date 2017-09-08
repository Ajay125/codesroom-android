package firstapplication.codesroom.com.firstprojectfromcodesroom.network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import firstapplication.codesroom.com.firstprojectfromcodesroom.utils.Constants;

/**
 * Created by spice on 19/08/17.
 */

public class ApiConnector {

    public static String getItems() {
        return connect(Constants.POST_URL);
    }

    private static String connect(String urlString) {
        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            connection.connect();

            InputStream stream = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
