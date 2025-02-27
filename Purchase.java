package ArthurAndShawn;
import java.util.Date;

public class Purchase {
    private Product[] products;
    private Date purchaseDate;

    // Constructor
    public Purchase(Product[] products, Date purchaseDate) {
        this.products = products;
        this.purchaseDate = purchaseDate;
    }

    // Getters
    public Product[] getProducts() {
        return products;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Purchase Date: ").append(purchaseDate).append("\n");
        builder.append("Products: ").append("\n");
        for (Product product : products) {
            if (product == null) break;
            builder.append("  ").append(product).append("\n");
        }
        return builder.toString();
    }
}
