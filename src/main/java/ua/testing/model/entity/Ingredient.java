package ua.testing.model.entity;

public class Ingredient {
    private long id;
    private String enName;
    private String ruName;
    private int amount;

    public Ingredient(long id, String enName, String ruName, int amount) {
        this.id = id;
        this.enName = enName;
        this.ruName = ruName;
        this.amount = amount;
    }

    public Ingredient(String enName, String ruName, int amount) {
        this.enName = enName;
        this.ruName = ruName;
        this.amount = amount;
    }

    public Ingredient(String enName) {
        this.enName = enName;
    }

    public Ingredient() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        return (int) (31 *
                id +
                (enName == null ? 0 : enName.hashCode()) +
                (ruName == null ? 0 : ruName.hashCode()) +
                amount);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (obj instanceof Ingredient) {
            Ingredient ingredient = (Ingredient) obj;
            return
                    this.id == ingredient.id &&
                    this.enName.equals(ingredient.enName) &&
                    this.ruName.equals(ingredient.ruName) &&
                    this.amount == ingredient.amount;
        } else {
            return false;
        }
    }
}
