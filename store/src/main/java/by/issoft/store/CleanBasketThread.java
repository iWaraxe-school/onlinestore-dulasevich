package by.issoft.store;

import java.util.concurrent.TimeUnit;
import static by.issoft.store.BasketThread.isBasketCleaned;
import static org.reflections.Reflections.log;


public class CleanBasketThread implements Runnable {

    @Override
    public void run() {
            while (!isBasketCleaned) {
                try {
                    TimeUnit.SECONDS.sleep(120);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Basket.cleanBasket();
                log.info("Cleaning all the products in basket. Products in basket now: " + Basket.getProductsInBasket());
                isBasketCleaned = true;
            }
        }
}
