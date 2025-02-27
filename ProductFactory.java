package ArthurAndShawn;

public class ProductFactory {
    public static Product createProduct(String name, double price, Category category, int serialNumber){
        return new Product(name, price, category, serialNumber);
    }
}
