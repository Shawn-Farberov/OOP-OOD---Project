package ArthurAndShawn;

public class Product {
    private String name;
    private double price;
    private Category category;
    private int serialNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Product(String name, double price, Category category, int serialNumber) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.serialNumber = serialNumber;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Name: ").append(name).append('\n');
        builder.append("Price: $").append(price).append('\n');
        builder.append("Category: ").append(category).append('\n');
        builder.append("Serial Number: ").append(serialNumber).append('\n');
        return builder.toString();
    }
}
