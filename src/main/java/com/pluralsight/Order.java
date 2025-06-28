package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Sandwich> sandwiches;
    private List<String> drinks;
    private List<String> chips;
    private double totalPrice;

    //constructor initializes the order list
    public Order() {
        sandwiches = new ArrayList<>();
        drinks = new ArrayList<>();
        chips = new ArrayList<>();
        totalPrice = 0.0;
    }
    //add sandwich to the order and update total
    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
        totalPrice += sandwich.getTotalPrice();
    }
    //add drink by name and update total based on size
    public void addDrink(String size, String flavor) {
        drinks.add(size + " " + flavor);

        double price = switch (size.toLowerCase()) {
            case "small" -> 2.00;
            case "medium" -> 2.50;
            case "large" -> 3.00;
            default -> 0.0;
        };
        totalPrice += price;
    }
    //add chips and update total
    public void addChips(String chipType) {
        chips.add(chipType);
        totalPrice += 1.50;
    }
    //returns the total price of the order
    public double getTotal() {
        return totalPrice;
    }
    //returns summary of the full order
    public String getOrderSummary() {
        StringBuilder summary = new StringBuilder("Order Summary:\n");

        for (Sandwich s : sandwiches) {
            summary.append(s.getSummary()).append("\n\n");
        }

        if (!drinks.isEmpty()) {
            summary.append("Drinks: ").append(drinks).append("\n");
        }
        if (!chips.isEmpty()) {
            summary.append("Chips: ").append(chips).append("\n");
        }

        summary.append(String.format("Total Price: $%.2f", totalPrice));
        return summary.toString();
    }
}
