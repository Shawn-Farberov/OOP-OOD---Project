package ArthurAndShawn;

public class CustomerFactory {
    public static Customer createCustomer (String username, String Password, Address address){
        return new Customer(username,Password,address);
    }
}
