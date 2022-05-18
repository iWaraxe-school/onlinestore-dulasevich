package by.issoft.store;

import by.issoft.domain.Category;

import java.sql.SQLException;
import java.util.List;

public class StoreHelper {
    //added new class which creates store and can be used throughout the app
    public static Store createStore(){
        RandomStorePopulator r = new RandomStorePopulator();
        List<Category> list = r.fillStoreRandomly();
        Store s = Store.getInstance(list);

        return s;
    }
}
