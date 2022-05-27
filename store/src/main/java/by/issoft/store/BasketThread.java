package by.issoft.store;

import by.issoft.store.HTTP.Client;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.*;

import static org.reflections.Reflections.log;

public class BasketThread implements Runnable {

    private boolean isProductAdded = false;
    private int randomNum = ThreadLocalRandom.current().nextInt(1, 30);

    public int getRandomNum() {
        return randomNum;
    }

    @Override
    public void run() {
        log.info("Product will be added in " + randomNum + " seconds");
        while(!isProductAdded){
            try {
                TimeUnit.SECONDS.sleep(randomNum);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Client.addProduct();
            } catch (IOException e) {
                e.printStackTrace();
            }
//                try {
//                    Basket.addProductToBasket();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//                log.info("Products in basket: " + Basket.getProductsInBasket());
            isProductAdded = true;
        }
    }
}

