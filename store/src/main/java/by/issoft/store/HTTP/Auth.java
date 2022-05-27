package by.issoft.store.HTTP;

import com.sun.net.httpserver.BasicAuthenticator;

public class Auth extends BasicAuthenticator {

    public static final String user = "admin";
    public static final String pass = "123";


    public Auth(String path) {
        super(path);
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        return username.equals(user) && password.equals(pass);
    }
}
