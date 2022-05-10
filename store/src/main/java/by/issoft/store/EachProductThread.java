package by.issoft.store;

import static org.reflections.Reflections.log;

public class EachProductThread implements Runnable {

    private boolean needRun = true;

   @Override
   public void run() {
           while(needRun) {
               Basket.addProductToBasket(StoreHelper.createStore());
               log.info("Products in basket: " + Basket.getProductsInBasket());
               needRun = false;
            }
        }
    }

