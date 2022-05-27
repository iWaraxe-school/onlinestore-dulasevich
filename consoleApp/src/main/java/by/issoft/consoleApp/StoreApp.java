package by.issoft.consoleApp;

import by.issoft.domain.CategoryType;
import by.issoft.domain.DB;
import by.issoft.store.*;
import by.issoft.store.HTTP.Client;
import by.issoft.store.HTTP.Server;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class StoreApp {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, InterruptedException, SQLException, ClassNotFoundException {

        boolean occurrence = true;

        Timer timer = new Timer();
        timer.schedule(new CleanBasketThread(), 1000, 120000);

        DB.createCategoryTable();
        DB.createProductTable();
        DB.addCategory(1, CategoryType.BOOK.name());
        DB.addCategory(2, CategoryType.MILK.name());
        DB.addCategory(3, CategoryType.PHONE.name());
        DB.addProducts(10);

        Server.startServer();

        while (occurrence){

            BufferedReader storeReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter what would you like to do with the store: "  + '\n' +
                    "1. Show store categories" + '\n' +
                    "2. Sort store" + '\n' +
                    "3. Show top 5 expensive products" + '\n' +
                    "4. Add random product to the basket" + '\n' +
                    "5. Close the store");
            String storeOption = storeReader.readLine();

            while (!(storeOption.equals("1") || storeOption.equals("2") || storeOption.equals("3")
                    || storeOption.equals("4") || storeOption.equals("5")) || storeOption.isEmpty()){
                System.out.println("Store option is either skipped or incorrect entered");
                storeOption = storeReader.readLine();
            }

            switch (storeOption){
                case "1" -> {
                    Client.getCategories();
                }
                case "2" -> {
                    System.out.println("Enter how would you like to sort the store - name, price or rate: ");
                    BufferedReader sortOptionReader = new BufferedReader(new InputStreamReader(System.in));
                    String sortOption = sortOptionReader.readLine();
                    while (!(sortOption.equals("name") || sortOption.equals("price") || sortOption.equals("rate"))
                            || sortOption.isEmpty()){
                        System.out.println("Sort option is either skipped or incorrect entered");
                        sortOption = storeReader.readLine();
                    }
                    DB.sort(sortOption);
                }
                case "3" -> {
                    DB.top5();
                    //System.out.println(Comparators.sortTop5Expensive(StoreHelper.createStore()));
                }
                case "4" -> {
                    ExecutorService executorService = Executors.newFixedThreadPool(2);

                    BasketThread basketThread = new BasketThread();

                    executorService.execute(basketThread);
                    executorService.awaitTermination(basketThread.getRandomNum() + 1, TimeUnit.SECONDS);
                    executorService.shutdown();
                    Client.goToBasket();
                }
                case "5" -> {
                    System.out.println("Enter 'close' to quit the app: ");
                    String closeOption = storeReader.readLine();
                    while (!(closeOption.equals("close")) || closeOption.isEmpty()){
                        System.out.println("Incorrect close option");
                        closeOption = storeReader.readLine();
                    }
                    occurrence = false;
                    DB.deleteProductsTable();
                    DB.deleteCategoryTable();
                    timer.cancel();
                    storeReader.close();
                    System.exit(130);
                }
            }
        }
    }
}