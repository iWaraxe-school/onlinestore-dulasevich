package by.issoft.consoleApp;

import by.issoft.domain.Category;
import by.issoft.store.RandomStorePopulator;
import by.issoft.store.Store;
import by.issoft.store.XMLpackage.Comparator;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class StoreApp {
    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, IOException, ParserConfigurationException, SAXException {

        RandomStorePopulator r = new RandomStorePopulator();
        List<Category> list = r.fillStoreRandomly();
        Store s = new Store(list);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter what would you like to sort the store - name, price or rate: ");
        String sortOption = reader.readLine();
        while (!(sortOption.equals("name") || sortOption.equals("price") || sortOption.equals("rate")
                || sortOption.isEmpty())){
            System.out.println("Store option is either skipped or incorrect entered");
            sortOption = reader.readLine();
        }
        Comparator.sortStore(sortOption, s);
        System.out.println(Comparator.sortTop5Expensive(s));

        System.out.println("Enter 'close' if you'd like to close the app: ");
        String closeOption = reader.readLine();
        while (!(closeOption.equals("close")) || closeOption.isEmpty()){
            System.out.println("Incorrect close option");
            closeOption = reader.readLine();
        }
        reader.close();
    }
}