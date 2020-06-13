package ua.testing.model.entity.dto;

import ua.testing.model.entity.ProductCategory;

public class AdminProductDTO {
    private String enProductName;
    private String ruProductName;
    private String price;
    private ProductCategory category;
    private int amount;

    public AdminProductDTO(String enProductName, String ruProductName, String price, ProductCategory category, int amount) {
        this.enProductName = enProductName;
        this.ruProductName = ruProductName;
        this.price = price;
        this.category = category;
        this.amount = amount;
    }

    public String getEnProductName() {
        return enProductName;
    }

    public void setEnProductName(String enProductName) {
        this.enProductName = enProductName;
    }

    public String getRuProductName() {
        return ruProductName;
    }

    public void setRuProductName(String ruProductName) {
        this.ruProductName = ruProductName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        return 31 *
                (enProductName == null ? 0 : enProductName.hashCode()) +
                (ruProductName == null ? 0 : ruProductName.hashCode()) +
                (price == null ? 0 : price.hashCode()) +
                (category == null ? 0 : category.hashCode()) +
                amount;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (obj instanceof AdminProductDTO) {
            AdminProductDTO adminProductDTO = (AdminProductDTO) obj;
            return
                    this.enProductName.equals(adminProductDTO.enProductName) &&
                    this.ruProductName.equals(adminProductDTO.ruProductName) &&
                    this.price.equals(adminProductDTO.price) &&
                    this.category.equals(adminProductDTO.category) &&
                    this.amount == adminProductDTO.amount;
        } else {
            return false;
        }
    }
}
