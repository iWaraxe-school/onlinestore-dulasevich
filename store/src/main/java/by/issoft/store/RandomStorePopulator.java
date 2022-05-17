package by.issoft.store;

import by.issoft.domain.*;

import com.github.javafaker.Faker;

import java.sql.SQLException;
import java.util.*;

public class RandomStorePopulator {

    List<Category> categories = new ArrayList<>();
    Faker faker = new Faker();

    //updating method which is adding categories to the store to use Factory instead of reflections
    public List<Category> addCategories(){

        CategoryFactory catFactory = new CategoryFactory();
        Category book = catFactory.getCategories(CategoryType.BOOK);
        Category phone = catFactory.getCategories(CategoryType.PHONE);
        Category milk = catFactory.getCategories(CategoryType.MILK);

        categories.add(book);
        categories.add(phone);
        categories.add(milk);

        return categories;
    }

    public List<Category> fillStoreRandomly(){

        categories= addCategories();
        for(Category c:categories){
            int categorySize = faker.number().numberBetween(1, 5);
            for(int i=0; i<categorySize; i++) {
                Product p = new Product(faker.app().name(), faker.number().randomDouble(2, 1, 5),
                        faker.number().randomDouble(2, 1, 1000));
                c.addProduct(p);
            }
        }
        return categories;
    }
}
