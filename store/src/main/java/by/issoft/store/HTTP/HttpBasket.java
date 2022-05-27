package by.issoft.store.HTTP;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class HttpBasket implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equals("GET")){
            exchange.sendResponseHeaders(200, 0);
            OutputStream o = exchange.getResponseBody();
            o.write("Products in basket are: ".getBytes(StandardCharsets.UTF_8));
            o.write(Order.httpBasketProducts.stream().toList().toString().getBytes(StandardCharsets.UTF_8));
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