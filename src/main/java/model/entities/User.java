package model.entities;

public class User {
    private Integer id;
    private String names;
    private String lastnames;
    private Long amount;
    private String description;


    public User() {

    }

    public User(Integer id, String names, String lastnames, Long amount, String description) {
        this.id = id;
        this.names = names;
        this.lastnames = lastnames;
        this.amount = amount;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastnames() {
        return lastnames;
    }

    public void setLastnames(String lastnames) {
        this.lastnames = lastnames;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", names='" + names + '\'' +
                ", lastnames='" + lastnames + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
