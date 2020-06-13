package ua.testing.model.entity;

import java.time.LocalDateTime;

public class Receipt {
    private long id;
    private LocalDateTime dateTime;
    private String enProductName;
    private String ruProductName;
    private long price;
    private int amount;
    private String username;

    public Receipt(LocalDateTime dateTime, String enProductName, String ruProductName, long price, int amount, String username) {
        this.dateTime = dateTime;
        this.enProductName = enProductName;
        this.ruProductName = ruProductName;
        this.price = price;
        this.amount = amount;
        this.username = username;
    }

    public Receipt() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
