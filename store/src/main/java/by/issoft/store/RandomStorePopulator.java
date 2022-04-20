package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import com.github.javafaker.Faker;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.*;


public class RandomStorePopulator {

    List<Category> categories = new ArrayList<>();

    Faker faker = new Faker();

    public List<Category> populateCategories() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Reflections reflections = new Reflections("by.issoft.domain");
        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);
        for (Class<? extends Category> cat : subTypes) {
            categories.add(cat.getConstructor().newInstance());
        }
        return categories;
    }

    public List<Category> fillStoreRandomly() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        categories= populateCategories();
        for(Category c:categories){
            int categorySize = faker.number().numberBetween(1, 5);
            for(int i=0; i<categorySize; i++) {
                Product p = new Product(faker.name().name(), faker.number().randomDouble(2, 1, 5),
                        faker.number().randomDouble(2, 1, 1000));
                c.addProduct(p);
            }
        }
        return categories;
    }
}
