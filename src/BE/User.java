package BE;

public class User {
    private int id;
    private String userName;
    private String passWord;
    private String Roles;

    public User(int id, String userName, String userPassWord, String roles) {
        this.id = id;
        this.userName = userName;
        this.passWord = userPassWord;
        this.Roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setUserPassWord(String userPassWord) {
        this.passWord = userPassWord;
    }

    public String getRoles() {
        return Roles;
    }

    public void setRoles(String roles) {
        Roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", Roles='" + Roles + '\'' +
                '}';
    }
}
