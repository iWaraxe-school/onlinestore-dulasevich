package by.issoft.consoleApp;

import by.issoft.domain.Category;
import by.issoft.store.RandomStorePopulator;
import by.issoft.store.Store;
import by.issoft.store.XMLpackage.Comparators;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class StoreApp {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        RandomStorePopulator r = new RandomStorePopulator();
        List<Category> list = r.fillStoreRandomly();
        Store s = Store.getInstance(list);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter what would you like to sort the store - name, price or rate: ");
        String sortOption = reader.readLine();
        while (!(sortOption.equals("name") || sortOption.equals("price") || sortOption.equals("rate")
                || sortOption.isEmpty())){
            System.out.println("Store option is either skipped or incorrect entered");
            sortOption = reader.readLine();
        }
        Comparators.sortStore(sortOption, s);
        System.out.println(Comparators.sortTop5Expensive(s));

        System.out.println("Enter 'close' to quit the app: ");
        String closeOption = reader.readLine();
        while (!(closeOption.equals("close")) || closeOption.isEmpty()){
            System.out.println("Incorrect close option");
            closeOption = reader.readLine();
        }
        reader.close();
    }
}