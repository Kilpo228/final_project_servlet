package ua.testing.model.entity.dto;

public class UserReceiptDTO {
    private String dateTime;
    private String productName;
    private String price;
    private int amount;

    public UserReceiptDTO(String dateTime, String productName, String price, int amount) {
        this.dateTime = dateTime;
        this.productName = productName;
        this.price = price;
        this.amount = amount;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
