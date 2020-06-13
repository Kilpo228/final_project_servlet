package ua.testing.model.entity;

public class User {
    private long id;
    private RoleType role;
    private String username;
    private String password;
    private long balance;

    public User(RoleType role, String username, String password, long balance) {
        this.role = role;
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public User(RoleType role, String username, String password) {
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public User() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String toString() {
        return "[id = " + this.id + ", " +
                "role = " + this.role.name() + ", " +
                "username = " + this.username + ", " +
                "password = " + this.password + ", " +
                "balance = " + this.balance +
                "]";
    }
}
