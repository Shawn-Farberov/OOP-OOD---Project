package ArthurAndShawn;

public class SellerFactory {
    public static Seller createSeller (String username, String Password){
        return new Seller(username,Password);
    }
}
