package by.issoft.store;

import by.issoft.domain.Category;
import java.util.List;

public class Store{

    private List<Category> categoryList;

    public Store(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    @Override
    public String toString() {
        return "Store - " +
                "categoryList: " + '\n' + categoryList +
                '}';
    }
}

