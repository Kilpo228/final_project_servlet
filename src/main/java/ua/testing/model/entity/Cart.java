package ua.testing.model.entity;

public class Cart {
    private long id;
    private String enProductName;
    private String ruProductName;
    private int amount;
    private long price;
    private String username;

    public Cart(String enProductName, String ruProductName, int amount, long price, String username) {
        this.enProductName = enProductName;
        this.ruProductName = ruProductName;
        this.amount = amount;
        this.price = price;
        this.username = username;
    }

    public Cart(String enProductName, String ruProductName, String username) {
        this.enProductName = enProductName;
        this.ruProductName = ruProductName;
        this.username = username;
    }

    public Cart() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
