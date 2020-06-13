package ua.testing.model.entity.dto;

import ua.testing.model.entity.Ingredient;
import ua.testing.model.entity.ProductCategory;

import java.util.List;

public class UserProductDTO {
    private ProductCategory category;
    private String name;
    private String price;
    private List<Ingredient> ingredients;

    public UserProductDTO(ProductCategory category, String name, String price, List<Ingredient> ingredients) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public int hashCode() {
        return 31 *
                (category == null ? 0 : category.hashCode()) +
                (name == null ? 0 : name.hashCode()) +
                price.hashCode() +
                ingredients.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (obj instanceof UserProductDTO) {
            UserProductDTO product = (UserProductDTO) obj;
            return
                    this.category.equals(product.category) &&
                    this.name.equals(product.name) &&
                    this.price.equals(product.price) &&
                    this.ingredients.equals(product.ingredients);
        } else {
            return false;
        }
    }
}
