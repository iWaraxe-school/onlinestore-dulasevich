package by.issoft.store.XMLpackage;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Comparators{

    public static void sortStore(String sortOption, Store store) throws ParserConfigurationException, IOException, SAXException {
        List<Category> categoryList = store.getCategoryList();
        for (Category cat : categoryList) {
            List<Product> product = cat.getProducts();
            cat.setProducts(sortXML(sortOption, product));
        }
        System.out.println(store);
    }

    private static Comparator<Product> nameComparator(){
        return Comparator.comparing(Product::getName);
    }

    private static Comparator<Product> priceComparator(){
        return Comparator.comparing(Product::getPrice);
    }

    private static Comparator<Product> rateComparator(){
        return Comparator.comparing(Product::getRate);
    }

    public static List<Product> sortXML(String sortOption, List<Product> list) throws ParserConfigurationException, IOException, SAXException {
        Map<String, String> map = XMLReader.readXML();
        List<Product> sortedProducts = new ArrayList<>(list);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            switch (sortOption) {
                case "name" -> {
                    if(entry.getKey().equals(sortOption)){
                        if (entry.getValue().equals("asc")) {
                            sortedProducts.sort(nameComparator());
                        } else if (entry.getValue().equals("desc")) {
                            sortedProducts.sort(nameComparator().reversed());
                        }
                    }
                }
                case "price" -> {
                    if(entry.getKey().equals(sortOption)){
                        if (entry.getValue().equals("asc")) {
                            sortedProducts.sort(priceComparator());
                        } else if (entry.getValue().equals("desc")) {
                            sortedProducts.sort(priceComparator().reversed());
                        }
                    }
                }
                case "rate" -> {
                    if(entry.getKey().equals(sortOption)){
                        if (entry.getValue().equals("asc")) {
                            sortedProducts.sort(rateComparator());
                        } else if (entry.getValue().equals("desc")) {
                            sortedProducts.sort(rateComparator().reversed());
                        }
                    }
                }
            }
        }
        return sortedProducts;
    }

    public static List<Product> sortTop5Expensive(Store store){
        List<Category> categoryList = store.getCategoryList();
        List<Product> p = new ArrayList<>();
        for (Category c:categoryList) {
            p.addAll(c.getProducts());
            p.sort(priceComparator().reversed());
        }
        System.out.println("Top 5 most expensive products are:");
        if (p.size() < 5) {
            return p;
        }
        else {
            return  p.subList(0, 5);
        }
    }
}
