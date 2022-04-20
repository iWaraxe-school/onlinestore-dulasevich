package by.issoft.consoleApp;

import by.issoft.domain.Category;
import by.issoft.store.RandomStorePopulator;
import by.issoft.store.Store;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class StoreApp {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        RandomStorePopulator r = new RandomStorePopulator();
        List<Category> list = r.fillStoreRandomly();
        Store s = new Store(list);
        System.out.println(s);
    }
}