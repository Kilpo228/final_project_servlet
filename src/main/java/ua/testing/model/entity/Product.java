package ua.testing.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private long id;
    private ProductCategory category;
    private String enName;
    private String ruName;
    private int amount;
    private long price;
    private List<Ingredient> ingredients = new ArrayList<>();

    public Product(ProductCategory category, String enName, String ruName, int amount, long price, List<Ingredient> ingredients) {
        this.category = category;
        this.enName = enName;
        this.ruName = ruName;
        this.amount = amount;
        this.price = price;
        this.ingredients = ingredients;
    }

    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getRuName() {
        return ruName;
    }

    public void setRuName(String ruName) {
        this.ruName = ruName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
