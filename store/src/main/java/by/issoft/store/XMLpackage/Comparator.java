package by.issoft.store.XMLpackage;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;
import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static by.issoft.store.XMLpackage.ProductElements.*;

public class Comparator {

    public static void sortStore(ProductElements element, Store store) throws XMLStreamException, FileNotFoundException {
        List<Category> categoryList = store.getCategoryList();
            for(Category cat:categoryList) {
                List<Product> product = cat.getProducts();
                switch (element) {
                    case NAME -> sortName(product);
                    case PRICE -> sortPrice(product);
                    case RATE -> sortRate(product);
                }
            }
        System.out.println(categoryList);
    }

    public static List<Product> sortName(List<Product> list) throws XMLStreamException, FileNotFoundException {
            Map<String, String> map= XMLReader.readXML();
            for(Map.Entry<String, String> entry: map.entrySet()){
                if(entry.getKey().equals("name")){
                    if(entry.getValue().equals("asc")){
                        list.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
                    }
                    else {
                        list.sort((o1, o2) -> o2.getName().compareTo(o1.getName()));
                    }
                }
            }
        return list;
    }

    public static List<Product> sortPrice(List<Product> list) throws XMLStreamException, FileNotFoundException {
        Map<String, String> map= XMLReader.readXML();
        for(Map.Entry<String, String> entry: map.entrySet()){
            if(entry.getKey().equals("price")){
                if(entry.getValue().equals("asc")){
                    list.sort((o1, o2) -> Double.compare(o1.getPrice(), o2.getPrice()));
                }
                else {
                    list.sort((o1, o2) -> Double.compare(o2.getPrice(), o1.getPrice()));
                }
            }
        }
        return list;
    }

    public static List<Product> sortRate(List<Product> list) throws XMLStreamException, FileNotFoundException {
        Map<String, String> map= XMLReader.readXML();
        for(Map.Entry<String, String> entry: map.entrySet()){
            if(entry.getKey().equals("rate")){
                if(entry.getValue().equals("asc")){
                    list.sort((o1, o2) -> Double.compare(o1.getRate(), o2.getRate()));
                }
                else {
                    list.sort((o1, o2) -> Double.compare(o2.getRate(), o1.getRate()));
                }
            }
        }
        return list;
    }


    public static ProductElements sortBasedOnEnum(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter how would you like to sort the store: by NAME, PRICE or RATE");
        String s = scanner.nextLine();;
        while(!(s.equals(NAME.toString()) || s.equals(PRICE.toString()) || s.equals(RATE.toString()))){
            System.out.println("Sort option is either skipped or incorrect. Enter the new one:");
            s = scanner.nextLine();
        }
        return ProductElements.valueOf(s);
    }
}
