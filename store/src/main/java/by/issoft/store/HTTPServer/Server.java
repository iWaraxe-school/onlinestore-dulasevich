package by.issoft.store.HTTPServer;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

    static final int port=8080;

    public static void startServer() throws IOException {

        HttpServer server=HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/categories", new Categories());
        server.createContext("/basket", new Basket());
        server.start();
    }
}
