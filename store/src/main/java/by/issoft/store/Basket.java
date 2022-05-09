package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;

import java.util.List;
import java.util.Random;
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

    public static List<Product> addProductToBasket(Store store){
        List<Category> categoryList = store.getCategoryList();

        Random rand = new Random();
        Category randomCategory = categoryList.get(rand.nextInt(categoryList.size()));
        int randomProduct = rand.nextInt(randomCategory.getProducts().size());

        productsInBasket.add(randomCategory.getProducts().get(randomProduct));
        return productsInBasket;
    }

    public static void cleanBasket(){
        productsInBasket.clear();
    }
}
