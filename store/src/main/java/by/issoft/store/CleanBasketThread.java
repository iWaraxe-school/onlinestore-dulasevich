package by.issoft.store;

import java.util.TimerTask;
import static org.reflections.Reflections.log;


public class CleanBasketThread extends TimerTask {

    @Override
    public void run() {
        Basket.cleanBasket();
        log.info("Cleaning all the products in basket. Products in basket now: " + Basket.getProductsInBasket());
    }
}