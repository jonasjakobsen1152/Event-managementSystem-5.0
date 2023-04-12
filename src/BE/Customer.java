package BE;

public class Customer {

    private int id;

    private String Name;

    private String lastName;

    private String email;

    public Customer(int id, String name, String lastName, String email) {
        this.id = id;
        this.Name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
