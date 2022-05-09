package by.issoft.store;

import java.util.concurrent.*;

import static org.reflections.Reflections.log;

public class BasketThread implements Runnable {

    public static boolean isBasketCleaned = false;

    @Override
    public void run() {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 30);
        log.info("Thread for adding products started: products will be added each " + randomNum + " seconds");
            while(!isBasketCleaned){
                try {
                    TimeUnit.SECONDS.sleep(randomNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new Thread(new EachProductThread()).start();
            }
        }
}

