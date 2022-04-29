package by.issoft.domain;

import by.issoft.domain.categories.BookCategory;
import by.issoft.domain.categories.MilkCategory;
import by.issoft.domain.categories.PhoneCategory;

import java.util.Objects;

/**
 *  Using Factory pattern to create categories in the store
 */

public class CategoryFactory {

    public Category getCategories(CategoryType categoryType){
        if(Objects.equals(categoryType, CategoryType.BOOK)){
            return new BookCategory();
        } else if (Objects.equals(categoryType, CategoryType.PHONE)){
            return new PhoneCategory();
        } else if (Objects.equals(categoryType, CategoryType.MILK)){
            return new MilkCategory();
        }
        return null;
    }
}
