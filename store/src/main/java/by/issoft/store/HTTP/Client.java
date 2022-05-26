package by.issoft.store.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Client {

    private static final int port= 8080;

    public static HttpURLConnection getConnection(String method, String path) throws IOException {

        URL url = new URL("http://localhost:" + port + path);

        String auth = Base64.getEncoder()
                .encodeToString((Auth.user + ":" + Auth.pass).getBytes(StandardCharsets.UTF_8));

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Authorization", "Basic " + auth);

        return connection;
    }

    public static void getCategories() throws IOException {
        String method = "GET";
        String path = "/categories";

        HttpURLConnection connection = getConnection(method, path);
        connection.setRequestMethod(method);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }
        in.close();

        connection.disconnect();
    }

    public static void goToBasket() throws IOException {
        String method = "GET";
        String path = "/basket";

        HttpURLConnection connection = getConnection(method, path);
        connection.setRequestMethod(method);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }
        in.close();

        connection.disconnect();
    }

    public static void addProduct() throws IOException {
        String method = "POST";
        String path = "/addProduct";

        HttpURLConnection connection = getConnection(method, path);
        connection.setRequestMethod(method);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }
        in.close();

        connection.disconnect();
    }
}
