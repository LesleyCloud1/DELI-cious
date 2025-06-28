package com.pluralsight;

import com.pluralsight.util.MenuOptions;
import java.util.Scanner;

//This class manages all interactions with the user via the command line interface (CLI)
public class UserInterface {
    private Scanner scanner; //Used to read user input

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
            System.out.println((i + 1) + ") " + MenuOptions.BREAD_TYPES[i]);
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        return MenuOptions.BREAD_TYPES[choice - 1];
    }

    //This method manages the entire order process, guiding the user through ordering process
    private void startNewOrder() {
        Order order = new Order();

        while (true) {
            System.out.println("\n🧾 Order Menu:");
            System.out.println("1) 🥪 Add Sandwich");
            System.out.println("2) 🥤 Add Drink");
            System.out.println("3) 🍟 Add Chips");
            System.out.println("4) ✅ Checkout");
            System.out.println("0) ❌ Cancel Order");
            System.out.print("Choose an option to continue: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    Sandwich sandwich = buildSandwich(order); //Pass the order so combo items can be added
                    order.addSandwich(sandwich);
                    break;
                case "2":
                    displayDrinkOptions(order);
                    break;
                case "3":
                    displayChipOptions(order);
                    break;
                case "4":
                    System.out.println("\n🧾 Final Order Summary:\n");
                    System.out.println(order.getOrderSummary());
                    System.out.print("Would you like to confirm this order? (yes/no): ");
                    if (scanner.nextLine().equalsIgnoreCase("yes")) {
                        ReceiptWriter.saveReceipt(order);
                        System.out.println("✅ Your order has been saved! Returning to home menu.");
                        return;
                    }
                    break;
                case "0":
                    System.out.println("❌ Order canceled. Returning to main menu.");
                    return;
                default:
                    System.out.println("⚠️ Invalid choice. Please enter a number from the menu.");
            }
        }
    }

    private void displayDrinkOptions(Order order) {
        System.out.println("Please select a drink flavor:");
        for (int i = 0; i < MenuOptions.DRINK_FLAVORS.length; i++) {
            System.out.println((i + 1) + ") " + MenuOptions.DRINK_FLAVORS[i]);
        }
        int flavorChoice = scanner.nextInt();
        scanner.nextLine();

        if (flavorChoice < 1 || flavorChoice > MenuOptions.DRINK_FLAVORS.length) {
            System.out.println("Invalid drink flavor selected.");
            return;
        }
        String flavor = MenuOptions.DRINK_FLAVORS[flavorChoice - 1];
        System.out.print("Enter drink size (small, medium, large): ");
        String size = scanner.nextLine();
        order.addDrink(size, flavor);
        System.out.println("Drink added to your order.");
    }

    private void displayChipOptions(Order order) {
        System.out.println("Please select a chip type:");
        for (int i = 0; i < MenuOptions.CHIP_TYPES.length; i++) {
            System.out.println((i + 1) + ") " + MenuOptions.CHIP_TYPES[i]);
        }
        int chipChoice = scanner.nextInt();
        scanner.nextLine();

        if (chipChoice < 1 || chipChoice > MenuOptions.CHIP_TYPES.length) {
            System.out.println("Invalid chip type selected.");
            return;
        }
        String chip = MenuOptions.CHIP_TYPES[chipChoice - 1];
        order.addChips(chip);
        System.out.println("Chips added to your order.");
    }

    //Builds a custom sandwich by prompting the user for each option step by step
    private Sandwich buildSandwich(Order order) {
        String bread = promptForBreadType(); // Ask user to choose bread
        System.out.print("Size (4, 8, 12): ");
        int size = Integer.parseInt(scanner.nextLine()); // Ask for sandwich size
        System.out.print("Toasted? (yes/no): ");
        boolean toasted = scanner.nextLine().equalsIgnoreCase("yes"); // Ask if they want it toasted

        Sandwich sandwich = new Sandwich(bread, size, toasted); // Create new Sandwich object

        //Prompt user to add ingredients to the sandwich
        promptForMeats(sandwich);
        promptForCheeses(sandwich);
        promptForToppings(sandwich);
        promptForSauces(sandwich);

        //Combo prompt
        System.out.print("Would you like to make it a combo? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            // If yes, ask for drink and chips
            displayDrinkOptions(order);
            displayChipOptions(order);
        }

        return sandwich; // Return the built sandwich
    }

    //Allows the user to add meats to the sandwich. Each selection can be made extra.
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

    //Allows the user to choose one or more cheeses for the sandwich, including extra options.
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

    //Prompts the user to select any number of regular toppings for the sandwich.
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

    //Lets the user pick sauces to be added to the sandwich from a predefined list.
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
