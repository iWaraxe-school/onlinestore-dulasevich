package by.issoft.consoleApp;

import by.issoft.domain.Category;
import by.issoft.store.RandomStorePopulator;
import by.issoft.store.Store;
import java.util.List;

public class StoreApp {
    public static void main(String[] args) {
        RandomStorePopulator r = new RandomStorePopulator();
        List<Category> list = r.populateStore();
        Store s = new Store(list);
        System.out.println(s);
    }
}