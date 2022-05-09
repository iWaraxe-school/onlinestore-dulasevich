package by.issoft.store;

import by.issoft.domain.Category;
import java.util.List;

public class Store{

    private static Store instance;
    private List<Category> categoryList;

    private Store(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public static Store getInstance(List<Category> catList){
        //updated SingleTon for threads
        Store result = instance;
        if(instance != null){
            return result;
        }
        synchronized(Store.class) {
            if (instance == null) {
                instance = new Store(catList);
            }
            return instance;
        }
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public String toString() {
        return "Store - " +
                "categoryList: " + '\n' + categoryList +
                '}';
    }
}

