package Day2.Demo3URLConnection;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ULConnectionTest {
    public static void main(String[] args) {
        try {
            String urlName = "http://www.iti.gov.eg";
            URL url = new URL(urlName);

            URLConnection connection = url.openConnection();
            connection.connect();

            Map<String, List<String>> headers = connection.getHeaderFields();

            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                String key = entry.getKey();
                for (String value : entry.getValue()) {
                    System.out.println(key + ": " + value);
                }
            }
            System.out.println("----------");
            System.out.println("getContentType: "+ connection.getContentType());
            System.out.println("getContentLength: "+ connection.getContentLength());
            System.out.println("getContentEncoding: "+ connection.getContentEncoding());
            System.out.println("getDate: "+ connection.getDate());
            System.out.println("getExpiration: "+ connection.getExpiration());
            System.out.println("getLastModified: "+ connection.getLastModified());
            System.out.println("----------");
            String encoding= connection.getContentEncoding();
            if(encoding== null) encoding= "UTF-8";

            Scanner in = new Scanner(connection.getInputStream(), encoding);
            for (int n = 1 ; in.hasNextLine() && n <= 10 ; n++) {
                System.out.println(in.nextLine());
                if (in.hasNextLine())
                    System.out.println(". . .");
            }

        } catch (IOException malformedURLException) {
            malformedURLException.printStackTrace();
        }
    }
}
