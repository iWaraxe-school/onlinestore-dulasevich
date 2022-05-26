package by.issoft.store.HTTP;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

    static final int port=8080;

    public static void startServer()  throws IOException {

        HttpServer server=HttpServer.create(new InetSocketAddress(port), 0);

        HttpContext categories = server.createContext("/categories", new Categories());
        HttpContext basket = server.createContext("/basket", new HttpBasket());
        HttpContext addProduct = server.createContext("/addProduct",new Order());

        categories.setAuthenticator(new Auth("/categories"));
        basket.setAuthenticator(new Auth("/basket"));
        addProduct.setAuthenticator(new Auth("/addProduct"));

        server.start();
    }
}