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
}
