package ArthurAndShawn;

import java.sql.SQLOutput;
import java.util.*;


public class StoreFacade {
        private final Manager manager;
        private static StoreFacade instance;

    public static StoreFacade getInstance() {
        if(instance == null){
            instance = new StoreFacade();
        }
        return instance;
    }

    public StoreFacade() {
            this.manager = new Manager();
        }


        public void addSeller(Scanner scn) {
            System.out.println("Enter username: ");
            String username = scn.next();
            while (manager.doesSellerExist(username)) {
                System.out.println("Username already taken, please enter a different one :");
                username = scn.next();
            }
            System.out.println("Enter password: ");
            String password = scn.next();
            manager.addSeller(username, password);
        }


        public void addACustomer(Scanner scn) {
            System.out.println("Enter username: ");
            String username = scn.next();
            while (manager.doesCustomerExist(username)) {
                System.out.println("Username already taken, please enter a different one: ");
                username = scn.next();
            }
            System.out.println("Enter password: ");
            String password = scn.next();
            System.out.println("Enter country: ");
            String country = scn.next();
            System.out.println("Enter city: ");
            String city = scn.next();
            System.out.println("Enter street: ");
            String street = scn.next();
            int building = 0;
            boolean validBuilding = false;
            while (!validBuilding) {
                System.out.println("Enter building number: ");
                try {
                    building = scn.nextInt();
                    validBuilding = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid building number. Please enter a valid number.");
                    scn.next();
                }
            }
            Address address = AddressFactory.createAddress(country, city, street, building);
            manager.addCustomer(username, password, address);
        }

        public void addProductToSeller(Scanner scn) {
            try {
                if (manager.getSellersAmount() == 0) {
                    System.out.println("There are no sellers!");
                    return;
                }
                System.out.println("Enter seller name: ");
                String username = scn.next();
                while (!manager.doesSellerExist(username)) {
                    System.out.println("Seller doesn't exist, please choose another: ");
                    username = scn.next();
                }
                System.out.println("Enter product name: ");
                String name = scn.next();
                double price = 0;
                boolean validPrice = false;
                while (!validPrice) {
                    System.out.println("Enter price: ");
                    try {
                        price = scn.nextDouble();
                        validPrice = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid price. Please enter a valid number.");
                        scn.next();
                    }
                }
                String cat = "";
                Category category = null;
                boolean validCategory = false;
                while (!validCategory) {
                    System.out.println("Enter category: (children/electric/office/clothing)");
                    cat = scn.next().toUpperCase();
                    try {
                        category = Category.valueOf(cat);
                        validCategory = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid category. Please enter a valid category: (children/electric/office/clothing)");
                    }
                }
                int serialNumber = manager.numGenerator();
                System.out.println("Does the product require packing? (yes/no)");
                String c = scn.next();
                while (manager.choiceChecker(c)) {
                    System.out.println("Wrong answer please choose(yes/no)");
                    c = scn.next();
                }
                if (Objects.equals(c, "yes")) {
                    System.out.println("What is the price of the package?");
                    int packPrice = scn.nextInt();
                    Product product = ProductFactory.createProduct(name, price + packPrice, category, serialNumber);
                    manager.addProductToSeller(username, product);
                    System.out.println("Product has been packaged successfully.");
                } else {
                    Product product = new Product(name, price, category, serialNumber);
                    manager.addProductToSeller(username, product);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid option. Please try again.");
            }
        }


        public void addProductToCustomer(Scanner scn) {
            if (manager.getSellersAmount() == 0) {
                System.out.println("There are no sellers!");
                return;
            }
            if (manager.getCustomersAmount() == 0) {
                System.out.println("There are no customers!");
                return;
            }
            String customer;
            do {
                System.out.println("Enter the name of the customer: ");
                customer = scn.next();
            } while (!manager.doesCustomerExist(customer));
            String seller;
            do {
                System.out.println("Choose seller:");
                manager.printSellers();
                seller = scn.next();
            } while (!manager.doesSellerExist(seller));

            Seller seller1 = manager.findSellerByName(seller);
            if (seller1.getProductsToSell() == 0) {
                System.out.println("This seller has no products for sale");
                return;
            }
            String pick = "yes";
            while (!(pick.equals("no"))) {
                seller1.printProducts(seller1);
                System.out.println("Choose product: ");
                String product = scn.next();
                manager.addToCustomerCart(customer, seller, product);
                System.out.println("Choose another product? :(yes/no)");
                pick = scn.next();
                while (manager.choiceChecker(pick)) {
                    System.out.println("Wrong answer please choose(yes/no)");
                    pick = scn.next();
                }
            }
        }

        public void payment(Scanner scn) {
            if (manager.getCustomersAmount() == 0) {
                System.out.println("There are no customers");
                return;
            }
            System.out.println("Enter customer name for payment: ");
            String customer = scn.next();
            Customer customer1 = manager.findCustomerByName(customer);
            try {
                if (customer1.getProducts() == 0) {
                    throw new EmptyCartException("Cart is empty, please pick another customer.");
                }
                customer1.cartPrice(customer1);
                customer1.addHistory(customer1);
                customer1.deleteCart(customer1);
            } catch (EmptyCartException e) {
                System.out.println(e.getMessage());
            }
        }

        public void showAllCustomers() {
            manager.printAllCustomersInfo();
        }

        public void showAllSellers() {
            manager.printAllSellersInfo();
        }

        public void printProductByCategory(Scanner scn) {
            boolean validCategory = false;
            while (!validCategory) {
                System.out.println("""
                        Choose category:
                        1. Children
                        2. Electric
                        3. Office
                        4. Clothing""");
                String cat = scn.next().toUpperCase();
                try {
                    Category category = Category.valueOf(cat);
                    manager.printByCategory(category);
                    validCategory = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid category. Please enter a valid category: (children/electric/office/clothing)");
                }
            }
        }


        public void chooseFromHistory(Scanner scn) {
            try {
                System.out.println("Enter customer name: ");
                String customerName = scn.next();
                Customer customer = manager.findCustomerByName(customerName);

                if (customer == null) {
                    System.out.println("Customer not found.");
                    return;
                }
                if (customer.getProducts() != 0) {
                    System.out.println("your current cart is not empty, are you sure?(yes/no)");
                    String c = scn.next();
                    while (manager.choiceChecker(c)) {
                        System.out.println("Wrong answer please choose(yes/no)");
                        c = scn.next();
                    }
                    if (Objects.equals(c, "no")) return;
                }


                System.out.println("Choose a cart from history:\n" + Arrays.toString(customer.getHistory()));
                int historyIndex = scn.nextInt();

                if (historyIndex < 1 || historyIndex > customer.getPurchases()) {
                    System.out.println("Invalid cart number.");
                    return;
                }


                customer.switchCartWithHistory(historyIndex - 1);
                System.out.println("Cart switched successfully.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scn.next();
            }
        }


        public void hardCodedSellers() {
            manager.addSeller("bambi", "14");
            manager.addSeller("tami", "4");
            manager.addSeller("bisli", "1");
            manager.addSeller("bug", "2000");
            manager.addSeller("elvis", "isalive");
            manager.addSeller("BambI", "14");
        }

        public void printNames() {
            manager.printNames();
        }

        public LinkedHashMap copyArrayToHashMap() {
            return manager.copyArrayToHashMap();
        }

        public void nameCounter(Scanner scn, LinkedHashMap nameMap) {
            System.out.println("please enter a name");
            String name = scn.next();
            manager.nameCounter(name, nameMap);
        }

        public void reverseArray(LinkedHashMap nameMap) {
            ArrayList<String> names = manager.nameArray(nameMap);
            System.out.println("Do you want to see the output of my self-implemented iterators (Y/y) or any other key to skip");
            Scanner scn = new Scanner(System.in);
            String choice = scn.nextLine();
            if (choice.equalsIgnoreCase("y")){
                Action1 action1 = new Action1();
                Action2 action2 = new Action2();
                Manager.CustomIterator iterator = manager.ourIterator();
                ((Subject) iterator).registerObserver(action1);
                ((Subject) iterator).registerObserver(action2);
                Manager.CustomListIterator ListIterator = manager.ourListIterator();
                ((Subject) ListIterator).registerObserver(action1);
                ((Subject) ListIterator).registerObserver(action2);
                System.out.println("printing in normal order using custom Iterator:");
                while (iterator.hasNext()){
                    System.out.println(iterator.next());
                }
                System.out.println("printing in normal order using custom ListIterator:");
                while (ListIterator.hasNext()){
                    System.out.println(ListIterator.next());
                }
                System.out.println("printing in reversed order using custom ListIterator:");
                while (ListIterator.hasPrevious()){
                    System.out.println(ListIterator.previous());
                }
            }
        }

        public void sort() {
            manager.printSortedNames();
        }


    }


