package ArthurAndShawn;

public abstract class User {

    protected String username;
    protected String password;
    protected Product[] products = new Product[1];



    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername(){
        return username;
    }
}
