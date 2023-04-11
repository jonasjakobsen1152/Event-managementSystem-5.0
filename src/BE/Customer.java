package BE;

public class Customer {

    private int id;

    private String Name;

    private String LastName;

    private String Email;

    public Customer(int id, String name, String lastName, String email) {
        this.id = id;
        Name = name;
        LastName = lastName;
        Email = email;
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
}
