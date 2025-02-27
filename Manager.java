package ArthurAndShawn;

import java.util.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Manager {

    private Customer[] customers;
    private Seller[] sellers;
    private int sellersAmount;
    private int customersAmount;
    private int[] serialNums = {0};
    private ArrayList<String> savedList = null;

    private static class Memento{
        private final ArrayList<String> state;

        private Memento(ArrayList<String> state) {
            this.state = new ArrayList<>(state); // Deep copy
        }

        private ArrayList<String> getState() {
            return new ArrayList<>(state); // Return a copy to prevent direct modification
        }
    }
    private Memento savedMemento = null;

    public void saveList(ArrayList<String> list){
        savedMemento = new Memento(list);
    }

    public ArrayList<String> restoreList(){
        if (savedMemento == null) {
            System.out.println("No saved list found!");
            return null;
        }
        System.out.println("List has been restored.");
        return savedMemento.getState();
    }
    
    public CustomIterator ourIterator() {
        return new CustomIterator();
    }

    public class CustomIterator extends Subject implements Iterator<String> {
        private int cur = 0;

        @Override
        public boolean hasNext() {
            if (cur >= sellersAmount) notifyObservers("My Iterator ended!");
            return cur < sellersAmount;
        }

        @Override
        public String next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return sellers[cur++].getName();
        }

    }

    public CustomListIterator ourListIterator() {
        return new CustomListIterator();
    }

    public class CustomListIterator extends Subject implements ListIterator<String> {
        private int cur = 0;

        @Override
        public boolean hasNext() {
            if (cur >= sellersAmount) notifyObservers("My ListIterator ended!");
            return cur < sellersAmount;
        }
        @Override
        public boolean hasPrevious() {
            if (cur == 0) notifyObservers("My ListIterator ended!");
            return  cur > 0;
        }

        @Override
        public String next() {
            if(!hasNext()){
                notifyObservers("My ListIterator ended!");
                throw new NoSuchElementException();
            }
            return sellers[cur++].getName();
        }
        @Override
        public String previous() {
            if(!hasPrevious()) {
                notifyObservers("My ListIterator ended!");
                throw new NoSuchElementException();
            }
            return sellers[--cur].getName();
        }
        @Override
        public int nextIndex(){
            return cur++;
        }

        @Override
        public int previousIndex(){
            return cur-1;
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported.");
        }

        @Override
        public void set(String e) {
            throw new UnsupportedOperationException("Set operation is not supported.");
        }

        @Override
        public void add(String e) {
            throw new UnsupportedOperationException("Add operation is not supported.");
        }

    }

    public Manager() {
        this.customers = new Customer[1]; // Initial capacity
        this.sellers = new Seller[1]; // Initial capacity
        this.customersAmount = 0;
        this.sellersAmount = 0;
    }

    public Customer[] getCustomers() {
        return customers;
    }

    public void setCustomers(Customer[] customers) {
        this.customers = customers;
    }

    public int getCustomersAmount() {
        return customersAmount;
    }

    public void setCustomersAmount(int customersAmount) {
        this.customersAmount = customersAmount;
    }

    public Seller[] getSellers() {
        return sellers;
    }

    public void setSellers(Seller[] sellers) {
        this.sellers = sellers;
    }

    public int getSellersAmount() {
        return sellersAmount;
    }

    public void setSellersAmount(int sellersAmount) {
        this.sellersAmount = sellersAmount;
    }

    public int[] getSerialNums() {
        return serialNums;
    }

    public void setSerialNums(int[] serialNums) {
        this.serialNums = serialNums;
    }

    private int[] arrayExtender(int[] arr) {
        int[] newarr = new int[arr.length * 2];
        System.arraycopy(arr, 0, newarr, 0, arr.length);
        return newarr;
    }

    public Customer[] customerExtender(Customer[] customers) {
        Customer[] newcustomer = new Customer[customers.length * 2];
        System.arraycopy(customers, 0, newcustomer, 0, customers.length);
        return newcustomer;
    }

    public Seller[] sellersExtender(Seller[] sellers) {
        Seller[] newsellers = new Seller[sellers.length * 2];
        System.arraycopy(sellers, 0, newsellers, 0, sellers.length);
        return newsellers;
    }

    public boolean doesSerialNumExist(int num) {
        for (int i = 0; i == serialNums.length - 1; i++) {
            if (num == serialNums[i]) {
                return true;
            }
        }
        return false;
    }


    public boolean doesSellerExist(String username) {

        for (Seller seller : sellers) {
            if (seller == null) {
                return false;
            }
            if (username.equals(seller.getName())) {
                return true;
            }
        }
        return false;
    }


    public boolean doesCustomerExist(String username) {
        for (Customer customer : customers) {
            if (customer == null) {
                return false;
            }
            if (username.equals(customer.getUsername())) {
                return true;
            }
        }
        return false;
    }

    public void addSeller(String username, String password) {
        if (sellersAmount == sellers.length) {
            sellers = sellersExtender(sellers);
        }
        sellers[sellersAmount] = SellerFactory.createSeller(username, password);
        sellersAmount++;
    }

    public void addCustomer(String username, String password, Address address) {
        if (customersAmount == customers.length) {
            customers = customerExtender(customers);
        }
        customers[customersAmount] = CustomerFactory.createCustomer(username, password, address);
        customersAmount++;
    }

    public Seller findSellerByName(String name) {
        for (Seller seller : sellers) {
            if (seller == null) {
                return null;
            }
            if (seller.getName().equals(name)) {
                return seller;
            }
        }
        return null;
    }

    public Customer findCustomerByName(String name) {
        for (Customer customer : customers) {
            if (customer == null) {
                return null;
            }
            if (customer.getUsername().equals(name)) {
                return customer;
            }
        }
        return null;
    }


    public void addProductToSeller(String name, Product product) {
        Seller seller = findSellerByName(name);
        seller.addProduct(product);

    }

    public void addToCustomerCart(String customer, String seller, String product) {
        Customer customer1 = findCustomerByName(customer);
        Seller seller1 = findSellerByName(seller);
        Product product1 = seller1.findProductByName(product);
        customer1.addToCart(product1);

    }

    public void printSellers() {
        if (sellersAmount == 0) {
            System.out.println("there are no sellers in the system");
        }
        Seller[] sellsersNew = new Seller[sellersAmount];
        int i = 0;
        for (Seller seller : sellers) {
            if (seller == null) {
                break;
            }
            sellsersNew[i] = seller;
            i++;
        }
        Arrays.sort(sellsersNew);
        for (Seller seller : sellsersNew) {
            System.out.println(seller.getName());
        }
    }

    public void printAllCustomersInfo() {
        if (customersAmount == 0) {
            System.out.println("there are no customers in the system");
        }
        Customer[] customersNew = new Customer[customersAmount];
        int i = 0;
        for (Customer customer : customers) {
            if (customer == null) {
                break;
            }
            customersNew[i] = customer;
            i++;
        }
        Arrays.sort(customersNew);
        for (Customer customer : customersNew) {
            System.out.println(customer.toString());
        }
    }

    public void printAllSellersInfo() {
        if (sellersAmount == 0) {
            System.out.println("there are no sellers in the system");
        }
        Seller[] sellsersNew = new Seller[sellersAmount];
        int i = 0;
        for (Seller seller : sellers) {
            if (seller == null) {
                break;
            }
            sellsersNew[i] = seller;
            i++;
        }
        Arrays.sort(sellsersNew);
        for (Seller seller : sellsersNew) {
            System.out.println(seller.toString());
        }
    }


    public int numGenerator() {
        int num = (int) (Math.random() * ((10000 - 1000) + 1)) + 1000;
        while (doesSerialNumExist(num)) {
            num = (int) (Math.random() * ((10000 - 1000) + 1)) + 1000;
        }
        serialNums[serialNums.length - 1] = num;
        serialNums = arrayExtender(serialNums);
        return num;
    }

    public void printByCategory(Category category) {
        int products = 0;
        for (Seller seller : sellers) {
            if (seller == null) break;
            for (Product product : seller.getProducts()) {
                if (product == null) break;
                if (product.getCategory().equals(category)) {
                    products++;
                    System.out.println(product);
                }
            }
        }
        if (products == 0) System.out.println("there are no products for this category");
    }

    public boolean choiceChecker(String choice) {
        return !Objects.equals(choice, "yes") && !Objects.equals(choice, "no");
    }

    public void printNames(){
        for (Seller seller:sellers){
            if (seller == null) return;
            System.out.println(seller.getName());
        }
    }

    public LinkedHashMap copyArrayToHashMap(){
        LinkedHashMap<String, Integer> nameMap = new LinkedHashMap<>();
        for (Seller seller: sellers){
            if (seller == null) break;
            String key = seller.getName().toLowerCase();
            if (nameMap.get(key) == null){
                nameMap.put(key, 1);
            }
            else {
                int value = nameMap.get(key);
                value++;
                nameMap.put(key, value);
            }
        }
        for(Map.Entry<String, Integer> seller: nameMap.entrySet()){
            String name = seller.getKey();
            int count = seller.getValue();
            System.out.println(name+ "..........." +count);
        }
        return nameMap;
    }
    public void nameCounter(String name, LinkedHashMap<String, Integer> nameMap){
        int value;
        if (nameMap.get(name.toLowerCase()) == null){
            value = 0;
        }
        else {
            value = nameMap.get(name.toLowerCase());
        }
        System.out.println(name + " is in the array " + value + " times");
    }

    public ArrayList<String> nameArray(LinkedHashMap<String, Integer> nameMap){
        ArrayList<String> names = new ArrayList<>();
        CustomListIteratorAdapter iterator = new CustomListIteratorAdapter(names);
        for (String name: nameMap.keySet()){
            iterator.myAdd(name);
            iterator.myAdd(name);
        }

        while (iterator.myHasPrevious()){
            System.out.println(iterator.myPrevious());
        }
        return names;

    }

    public void printSortedNames(){
        TreeSet<String> nameSet = new TreeSet<>(Comparator.comparingInt(String::length).thenComparing(String::compareTo));
        for (int i = 0; i< sellersAmount; i++){
            nameSet.add(sellers[i].getName().toUpperCase());
        }
        for(String name:nameSet){
            System.out.println(name);
        }
    }



}