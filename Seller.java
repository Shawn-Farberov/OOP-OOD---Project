package ArthurAndShawn;

import java.util.Arrays;

public class Seller extends User implements Comparable<Seller> {

    private int productsToSell;



    public String getName() {
        return username;
    }

    public void setName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public int getProductsToSell() {
        return productsToSell;
    }



    public void setProductsToSell(int productsToSell) {
        this.productsToSell = productsToSell;
    }

    public Seller(String username, String password) {
        super(username,password);
        this.productsToSell = 0;
    }

    public int compareTo(Seller other) {
        return this.products.length - other.products.length;
    }

    public Product[] productExtender(Product[] cart){
        Product[] newproducts = new Product[products.length * 2];
        System.arraycopy(products,0, newproducts, 0, products.length);
        return newproducts;

    }

    public void addProduct(Product product){
        if (productsToSell == products.length){
            products= productExtender(products);
        }
        products[productsToSell] = product;
        productsToSell++;
    }
    public Product findProductByName(String name){
        for (Product product : products){
            if (product == null){
                return null;
            }
            if (product.getName().equals(name)){
                return product;
            }
        }
        return null;
    }
    public void printProducts(Seller seller){
        System.out.println(Arrays.toString(seller.getProducts()));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Seller name: ").append(username).append('\n');
        builder.append("Products for sale: ").append('\n');
        for (int i = 0; i < productsToSell; i++) {
            builder.append(products[i]).append('\n');
        }
        return builder.toString();
    }
}


