package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.domain.categories.BookCategory;
import by.issoft.domain.categories.MilkCategory;
import by.issoft.domain.categories.PhoneCategory;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class RandomStorePopulator {

    List<Product> productsBooks = new ArrayList<>();
    List<Product> productsPhones = new ArrayList<>();
    List<Product> productsMilk = new ArrayList<>();
    List<Category> categories = new ArrayList<>();

    Faker faker = new Faker();

    public List<Product> addBook(){
        int bookCategorySize = faker.number().numberBetween(1, 5);
        for(int i=0; i<bookCategorySize; i++){
        productsBooks.add(new Product(faker.book().title(), faker.number().randomDouble(2, 1, 5),
                faker.number().randomDouble(2,1, 1000)));
            }
        return productsBooks;
    }

    public List<Product> addPhone(){
        int phoneCategorySize = faker.number().numberBetween(1, 5);
        for(int i=0; i<phoneCategorySize; i++){
        productsPhones.add(new Product(faker.phoneNumber().subscriberNumber(6), faker.number().randomDouble(2, 1, 5),
                faker.number().randomDouble(2,1, 1000)));
            }
        return productsPhones;
    }

    public List<Product> addMilk(){
        int milkCategorySize = faker.number().numberBetween(1, 5);
        for(int i=0; i<milkCategorySize; i++) {
            productsMilk.add(new Product(faker.expression("Milk"), faker.number().randomDouble(2, 1, 5),
                    faker.number().randomDouble(2, 1, 1000)));
        }
        return productsMilk;
    }

    public List<Category> populateStore(){
        Category books = new BookCategory();
        books.setProducts(addBook());
        categories.add(books);

        Category phones = new PhoneCategory();
        phones.setProducts(addPhone());
        categories.add(phones);

        Category milk = new MilkCategory();
        milk.setProducts(addMilk());
        categories.add(milk);

        return categories;
    }
}
