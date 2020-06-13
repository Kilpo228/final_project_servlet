package ua.testing.model.entity.dto;

public class CartDTO {
    private String productName;
    private int amount;
    private String price;

    public CartDTO(String productName, int amount, String price) {
        this.productName = productName;
        this.amount = amount;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        return 31 *
                (productName == null ? 0 : productName.hashCode()) +
                amount +
                (price == null ? 0 : price.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (obj instanceof CartDTO) {
            CartDTO cartDTO = (CartDTO) obj;
            return
                    this.productName.equals(cartDTO.productName) &&
                    this.amount == cartDTO.amount &&
                    this.price.equals(cartDTO.price);
        } else {
            return false;
        }
    }
}
