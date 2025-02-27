package ArthurAndShawn;


import java.util.Arrays;
import java.util.Date;

public class Customer extends User implements Comparable<Customer> {

    private Address address;
    private Product[] cart;
    private int products;
    private Purchase[] history;
    private int purchases;

    public Customer(String username, String password,Address address) {
        super(username,password);
        this.address = address;
        this.cart = new Product[1];
        this.products = 0;
        this.history = new Purchase[1];
        this.purchases = 0;


    }
    public int compareTo(Customer other) {
        if (other == null) {
            return 1; // this instance is considered greater if the other is null
        }
        if (this.username == null && other.username == null) {
            return 0; // both are null, considered equal
        }
        if (this.username == null) {
            return -1; // this instance is considered less if its username is null
        }
        if (other.username == null) {
            return 1; // this instance is considered greater if the other's username is null
        }
        return this.username.compareTo(other.username);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Product[] getCart() {
        return cart;
    }

    public void setCart(Product[] cart) {
        this.cart = cart;
    }

    public int getProducts() {
        return products;
    }

    public void setProducts(int products) {
        this.products = products;
    }

    public Purchase[] getHistory() {
        return history;
    }

    public void setHistory(Purchase[] history) {
        this.history = history;
    }

    public int getPurchases() {
        return purchases;
    }

    public void setPurchases(int purchases) {
        this.purchases = purchases;
    }

    public Product[] cartExtender(Product[] cart){
        Product[] newcart = new Product[cart.length * 2];
        System.arraycopy(cart,0, newcart, 0, cart.length);
        return newcart;

    }
    public Purchase[] historyExtender(Purchase[] history){
        Purchase[] newHistory = new Purchase[history.length * 2];
        System.arraycopy(history, 0, newHistory, 0, history.length);
        return newHistory;
    }


    public void addToCart(Product product){
        if (products == cart.length){
            cart = cartExtender(cart);
        }
        cart[products] = product;
        products++;
    }
    public void cartPrice(Customer customer){
        double price = 0.0;
        for (Product product : customer.getCart()){
            if (product == null){
                break;
            }
            price += product.getPrice();
        }
        System.out.println("Total price - " + price);
    }
    public void addHistory(Customer customer1){
        if (purchases == history.length){
            history = historyExtender(history);
        }
        Date date = new Date();
        Product[] purchasedProducts = new Product[products];
        System.arraycopy(cart, 0, purchasedProducts, 0, products);
        history[purchases] = new Purchase(purchasedProducts, date);
        purchases++;

    }
    public void deleteCart(Customer customer){
        Product[] cart = new Product[1];
        customer.setCart(cart);
        customer.setProducts(0);
    }
    public void switchCartWithHistory(int index) {
        if (index >= 0 && index < purchases) {
            cart = history[index].getProducts();
            System.out.println("Cart switched with history cart number " + index + 1);
        } else {
            System.out.println("Invalid cart number");
        }
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Customer name = ").append(username).append('\n');
        builder.append("Cart = ").append(Arrays.toString(cart)).append('\n');
        builder.append("Purchase history = ").append('\n');
        for (int i = 0; i < purchases; i++) {
            builder.append(history[i]).append('\n');
        }
        return builder.toString();
    }

}

