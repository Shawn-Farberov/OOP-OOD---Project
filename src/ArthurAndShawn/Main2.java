package ArthurAndShawn;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import java.util.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main2 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        Manager manager = new Manager();
        StoreFacade facade = StoreFacade.getInstance();
        LinkedHashMap<String, Integer> nameMap = new LinkedHashMap<>();
        Map<Integer, Command> commandMap = new HashMap<>();
        commandMap.put(99, new PrintNamesCommand(facade));
        CopyArrayCommand copyCommand = new CopyArrayCommand(facade);
        commandMap.put(100, copyCommand);
        commandMap.put(101, new NameCounterCommand(facade, scn, nameMap));
        commandMap.put(102, new ReverseArrayCommand(facade, nameMap));
        commandMap.put(103, new SortNamesCommand(facade));
        commandMap.put(104, new SaveListCommand(manager, nameMap));
        commandMap.put(105, new RestoreListCommand(manager));
        int choice = -1;
        while (choice != 1) {
            System.out.println("""
                    Hello and welcome to our store, please choose one of the following options:
                    1. Exit
                    2. Add a seller
                    3. Add a customer
                    4. Add a product to a seller
                    5. Add a product to a customer
                    6. Payment
                    7. Show all customers
                    8. Show all sellers
                    9. Print products by category
                    10. Choose from history cart
                    98. add the objects to the array for testing
                    99. print the names in the array by order
                    100. copy array to new collection and print it with number of occurrences
                    101. counting the amount of times a name you enter is in the array
                    102. reversed array double names
                    103. sorted by length
                    104. save Array List
                    105. Restore Array List""");

            try {
                choice = scn.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scn.next();
                continue;
            }
            if (commandMap.containsKey(choice)) {
                commandMap.get(choice).execute();
                if (choice == 100) {
                    nameMap.putAll(copyCommand.getNameMap());
                }
            } else {
                switch (choice) {
                    case 1:
                        System.out.println("Thanks for visiting, Bye.");
                        break;
                    case 2: {
                        facade.addSeller(scn);
                        break;
                    }
                    case 3: {
                        facade.addACustomer(scn);
                        break;
                    }
                    case 4: {
                        facade.addProductToSeller(scn);
                        break;
                    }
                    case 5: {
                        facade.addProductToCustomer(scn);
                        break;
                    }
                    case 6: {
                        facade.payment(scn);
                        break;
                    }
                    case 7: {
                        facade.showAllCustomers();
                        break;
                    }
                    case 8: {
                        facade.showAllSellers();
                        break;
                    }
                    case 9: {
                        facade.printProductByCategory(scn);
                        break;
                    }
                    case 10: {
                        facade.chooseFromHistory(scn);
                        break;

                    }
                    case 98: {
                        facade.hardCodedSellers();
                        break;
                    }
                    default:
                        System.out.println("Invalid choice. Please choose a valid option.");
                }
            }
        }
    }
}