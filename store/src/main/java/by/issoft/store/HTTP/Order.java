package by.issoft.store.HTTP;
;
import by.issoft.domain.Product;
import by.issoft.store.Basket;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Order implements HttpHandler {

    public static List<Product> httpBasketProducts = new ArrayList<>();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equals("POST")){
            exchange.sendResponseHeaders(200, 0);
            try {
                httpBasketProducts = Basket.addProductToBasket();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            OutputStream o = exchange.getResponseBody();
            o.write("Product is added to the bag".getBytes(StandardCharsets.UTF_8));
            o.close();
        }
        else {
            exchange.sendResponseHeaders(405, 0);
            OutputStream o = exchange.getResponseBody();
            o.write("Method not supported".getBytes(StandardCharsets.UTF_8));
            o.close();
        }
    }
}
