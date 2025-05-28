package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    private String breadType; //they choose the type of bread that they would like (white, wheat, rye, or wrap).
    private int size; //Customers can order sandwiches in 3 sizes (4", 8" and 12"
    private boolean isToasted;

    private List<String> meats;
    private List<String> cheeses;
    private List<String> regularToppings;
    private List<String> sauces;

    private double totalPrice;

    //Constructor creates a sandwich with selected options
    public Sandwich(String breadType, int size, boolean isToasted) {
        this.breadType = breadType;
        this.size = size;
        this.isToasted = isToasted;
        this.meats = new ArrayList<>();
        this.cheeses = new ArrayList<>();
        this.regularToppings = new ArrayList<>();
        this.sauces = new ArrayList<>();

        //base price depending on size
        if (size == 4) totalPrice = 5.50;
        else if (size == 8) totalPrice = 7.00;
        else if (size == 12) totalPrice = 8.50;
    }
    //Premium meat prices
    public void addMeat(String meat, boolean extra) {
        meats.add(meat);
        if (extra) meats.add("Extra " + meat); //optional

        double price = switch (size) {
            case 4 -> 1.00;
            case 8 -> 2.00;
            case 12 -> 3.00;
            default -> 0.0;
        };
        totalPrice += price;

    }
}
