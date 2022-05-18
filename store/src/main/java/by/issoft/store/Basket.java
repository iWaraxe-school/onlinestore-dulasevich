package by.issoft.store;

import by.issoft.domain.DB;
import by.issoft.domain.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Basket{

    private static List<Product> productsInBasket = new CopyOnWriteArrayList<>();

    public static List<Product> getProductsInBasket() {
        return productsInBasket;
    }

    @Override
    public String toString() {
        return "" + productsInBasket;
    }

    public static List<Product> addProductToBasket() throws SQLException, ClassNotFoundException {
//        List<Category> categoryList = store.getCategoryList();
//
//        Random rand = new Random();
//        Category randomCategory = categoryList.get(rand.nextInt(categoryList.size()));
//        int randomProduct = rand.nextInt(randomCategory.getProducts().size());

        Statement st = DB.getConnection().createStatement();
        String sqlName = ("SELECT * FROM PRODUCTS ORDER BY RAND() LIMIT 1");
        ResultSet rs = st.executeQuery(sqlName);
        while(rs.next()) {
            String name = rs.getString("name");
            double rate = rs.getDouble("rate");
            double price = rs.getDouble("price");

            Product p = new Product(name, rate, price);
            productsInBasket.add(p);
        }
        return productsInBasket;
    }

    public static void cleanBasket(){
        productsInBasket.clear();
    }
}
