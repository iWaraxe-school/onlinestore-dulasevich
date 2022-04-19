package by.issoft.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Category {

    private String name;
    private List<Product> products = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() { return products; }

    public void setProducts(List<Product> products) { this.products = products; }

    public void addProduct(Product p){
        products.add(p);
    }

    @Override
    public String toString() {
        return "Category: " +
                "name='" + name + '\'' +
                ", products= " + products +
                '}'  + '\n';
    }
}
