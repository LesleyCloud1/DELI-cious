package com.pluralsight;

import java.util.Scanner;
//this class handles all the user interaction through the console
public class UserInterface {
    private Scanner scanner;

    //Constructor initalizes scanner
    public UserInterface() {
        scanner = new Scanner(System.in); //Scanner reads user input
    }

    //This method displays the main home screen
    public void showHomeScreen() {
        while (true) {
            //print menu options
            System.out.println("Welcome to DELI-cious!");
            System.out.println("1) Start New Order");
            System.out.println("0) Exit");

            //Ask user for input
            System.out.print("Please choose a option: ");
            String choice = scanner.nextLine();

            //Decide what to do based on user input
            if (choice.equals("1")) {
                System.out.println("Starting new order...\n");
                startNewOrder(); //Start the ordering process
            } else if (choice.equals("0")) {
                System.out.println("Have a blessed day, thanks for visiting DELI-cious!");
                break;
            } else {
                System.out.println("Invalid input, please try again.\n");
            }
        }
    }

    //Starts a new order
    private void startNewOrder() {
        Order order = new Order();

        while (true) {
            System.out.println("\nOrder Menu:");
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    Sandwich sandwich = buildSandwich();
                    order.addSandwich(sandwich);
                    break;
                case "2":
                    System.out.print("Enter drink size (small, medium, large): ");
                    String size = scanner.nextLine();
                    System.out.print("Enter flavor: ");
                    String flavor = scanner.nextLine();
                    order.addDrink(size, flavor);
                    break;
                case "3":
                    System.out.print("Enter chip type: ");
                    String chip = scanner.nextLine();
                    order.addChips(chip);
                    break;
                case "4":
                    System.out.println("\n" + order.getOrderSummary());
                    System.out.print("Confirm order? (yes/no): ");
                    if (scanner.nextLine().equalsIgnoreCase("yes")) {
                        ReceiptWriter.saveReceipt(order);
                        System.out.println("Order saved!");
                        return;
                    }
                    break;
                case "0":
                    System.out.println("Order canceled.");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    //Builds a sandwich by prompting user step-by-step
    private Sandwich buildSandwich() {
        System.out.print("Bread type (white, wheat, rye, wrap): ");
        String bread = scanner.nextLine();

        System.out.print("Size (4, 8, 12): ");
        int size = Integer.parseInt(scanner.nextLine());

        System.out.print("Toasted? (yes/no): ");
        boolean toasted = scanner.nextLine().equalsIgnoreCase("yes");

        Sandwich sandwich = new Sandwich(bread, size, toasted);

        //Add meats
        while (true) {
            System.out.print("Add meat (type 'done' to finish): ");
            String meat = scanner.nextLine();
            if (meat.equalsIgnoreCase("done")) break;
            System.out.print("Extra meat? (yes/no): ");
            boolean extra = scanner.nextLine().equalsIgnoreCase("yes");
            sandwich.addMeat(meat, extra);
        }

        //Add cheeses
        while (true) {
            System.out.print("Add cheese (type 'done' to finish): ");
            String cheese = scanner.nextLine();
            if (cheese.equalsIgnoreCase("done")) break;
            System.out.print("Extra cheese? (yes/no): ");
            boolean extra = scanner.nextLine().equalsIgnoreCase("yes");
            sandwich.addCheese(cheese, extra);
        }

        //Add toppings
        while (true) {
            System.out.print("Add regular topping (type 'done' to finish): ");
            String topping = scanner.nextLine();
            if (topping.equalsIgnoreCase("done")) break;
            sandwich.addTopping(topping);
        }

        //Add sauces
        while (true) {
            System.out.print("Add sauce (type 'done' to finish): ");
            String sauce = scanner.nextLine();
            if (sauce.equalsIgnoreCase("done")) break;
            sandwich.addSauce(sauce);
        }

        return sandwich;
    }
}