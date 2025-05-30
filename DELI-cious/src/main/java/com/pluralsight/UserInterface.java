package com.pluralsight;

import com.pluralsight.util.MenuOptions;
import java.util.Scanner;

//This class manages all interactions with the user via the command line interface (CLI)
public class UserInterface {
    private Scanner scanner; // Used to read user input from the console

    //Constructor initializes the Scanner when the class is created
    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    //This method shows the welcome screen and handles the user's initial choice
    public void showHomeScreen() {
        while (true) {
            System.out.println();
            System.out.println("==========================================");
            System.out.println("          🥪  WELCOME TO DELI-cious  🥪");
            System.out.println("       Build Your Sandwich, Byte by Byte!");
            System.out.println("==========================================");
            System.out.println("What would you like to do?");
            System.out.println("1) 🛒 Start a New Order");
            System.out.println("0) ❌ Exit");
            System.out.print("\nEnter your choice: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.println("\nGreat choice! Let’s build something delicious...\n");
                startNewOrder();
            } else if (choice.equals("0")) {
                System.out.println("Thank you for visiting DELI-cious! Have a blessed day! 🙏");
                break;
            } else {
                System.out.println("⚠️ Oops! That's not a valid option. Please try again.\n");
            }
        }
    }

    //This method lets the user choose the bread type for the sandwich
    public String promptForBreadType() {
        System.out.println("Please select a bread type:");
        for (int i = 0; i < MenuOptions.BREAD_TYPES.length; i++) {
            System.out.println((i + 1) + ") " + MenuOptions.BREAD_TYPES[i]); //Print each bread option
        }

        int choice = scanner.nextInt(); //Read the user's number choice
        scanner.nextLine(); //Clear the newline character
        return MenuOptions.BREAD_TYPES[choice - 1]; //Return the selected bread
    }

    //This method manages the entire order process
    private void startNewOrder() {
        Order order = new Order(); //Create a new order

        while (true) {
            //Show the order menu
            System.out.println("\nOrder Menu:");
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");

            String option = scanner.nextLine(); //Read user choice

            //Handle each menu option
            switch (option) {
                case "1":
                    Sandwich sandwich = buildSandwich(); //Build a custom sandwich
                    order.addSandwich(sandwich); //Add to order
                    break;
                case "2":
                    System.out.print("Enter drink size (small, medium, large): ");
                    String size = scanner.nextLine();

                    System.out.println("Choose a flavor:");
                    for (int i = 0; i < MenuOptions.DRINK_FLAVORS.length; i++) {
                        System.out.println((i + 1) + ") " + MenuOptions.DRINK_FLAVORS[i]);
                    }
                    int flavorChoice = scanner.nextInt();
                    scanner.nextLine(); //consume newline
                    String flavor = MenuOptions.DRINK_FLAVORS[flavorChoice - 1];

                    order.addDrink(size, flavor);
                    break;

                case "3":
                    System.out.println("Choose a chip type:");
                    for (int i = 0; i < MenuOptions.CHIP_TYPES.length; i++) {
                        System.out.println((i + 1) + ") " + MenuOptions.CHIP_TYPES[i]);
                    }
                    int chipChoice = scanner.nextInt();
                    scanner.nextLine(); //consume newline
                    String chip = MenuOptions.CHIP_TYPES[chipChoice - 1];

                    order.addChips(chip);
                    break;

                case "4":
                    //Display order and ask for confirmation
                    System.out.println("\n" + order.getOrderSummary());
                    System.out.print("Confirm order? (yes/no): ");
                    if (scanner.nextLine().equalsIgnoreCase("yes")) {
                        ReceiptWriter.saveReceipt(order); //Save order to file
                        System.out.println("Order saved!");
                        return;
                    }
                    break;
                case "0":
                    System.out.println("Order canceled.");
                    return; //Exit to main screen
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    //This method walks the user through building a sandwich step by step
    private Sandwich buildSandwich() {
        String bread = promptForBreadType(); //Ask for bread type
        System.out.print("Size (4, 8, 12): ");
        int size = Integer.parseInt(scanner.nextLine()); //Get sandwich size

        System.out.print("Toasted? (yes/no): ");
        boolean toasted = scanner.nextLine().equalsIgnoreCase("yes"); //Toasted?

        Sandwich sandwich = new Sandwich(bread, size, toasted); //Create sandwich object

        promptForMeats(sandwich);   //Ask for meats
        promptForCheeses(sandwich); //Ask for cheeses
        promptForToppings(sandwich); //Ask for regular toppings
        promptForSauces(sandwich);   //Ask for sauces

        return sandwich; //Return the fully built sandwich
    }

    //Method to prompt user for meats
    private void promptForMeats(Sandwich sandwich) {
        System.out.println("Select meats (type number or 0 to finish):");
        while (true) {
            for (int i = 0; i < MenuOptions.MEATS.length; i++) {
                System.out.println((i + 1) + ") " + MenuOptions.MEATS[i]);
            }
            System.out.println("0) Done");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) break;
            if (choice >= 1 && choice <= MenuOptions.MEATS.length) {
                String meat = MenuOptions.MEATS[choice - 1];
                System.out.print("Add extra " + meat + "? (yes/no): ");
                boolean extra = scanner.nextLine().equalsIgnoreCase("yes");
                sandwich.addMeat(meat, extra);
            } else {
                System.out.println("Invalid option, try again.");
            }
        }
    }

    //Method to prompt user for cheeses
    private void promptForCheeses(Sandwich sandwich) {
        System.out.println("Select cheeses (type number or 0 to finish):");
        while (true) {
            for (int i = 0; i < MenuOptions.CHEESES.length; i++) {
                System.out.println((i + 1) + ") " + MenuOptions.CHEESES[i]);
            }
            System.out.println("0) Done");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) break;
            if (choice >= 1 && choice <= MenuOptions.CHEESES.length) {
                String cheese = MenuOptions.CHEESES[choice - 1];
                System.out.print("Add extra " + cheese + "? (yes/no): ");
                boolean extra = scanner.nextLine().equalsIgnoreCase("yes");
                sandwich.addCheese(cheese, extra);
            } else {
                System.out.println("Invalid option, try again.");
            }
        }
    }

    //Method to prompt user for regular toppings
    private void promptForToppings(Sandwich sandwich) {
        System.out.println("Select toppings (type number or 0 to finish):");
        while (true) {
            for (int i = 0; i < MenuOptions.REGULAR_TOPPINGS.length; i++) {
                System.out.println((i + 1) + ") " + MenuOptions.REGULAR_TOPPINGS[i]);
            }
            System.out.println("0) Done");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) break;
            if (choice >= 1 && choice <= MenuOptions.REGULAR_TOPPINGS.length) {
                sandwich.addTopping(MenuOptions.REGULAR_TOPPINGS[choice - 1]);
            } else {
                System.out.println("Invalid option, try again.");
            }
        }
    }

    //Method to prompt user for sauces
    private void promptForSauces(Sandwich sandwich) {
        System.out.println("Select sauces (type number or 0 to finish):");
        while (true) {
            for (int i = 0; i < MenuOptions.SAUCES.length; i++) {
                System.out.println((i + 1) + ") " + MenuOptions.SAUCES[i]);
            }
            System.out.println("0) Done");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) break;
            if (choice >= 1 && choice <= MenuOptions.SAUCES.length) {
                sandwich.addSauce(MenuOptions.SAUCES[choice - 1]);
            } else {
                System.out.println("Invalid option, try again.");
            }
        }
    }
}
