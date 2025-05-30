package com.pluralsight;

package com.pluralsight.util;

package com.pluralsight.util;

/**
 * This class contains all predefined options used in the DELI-cious ordering system.
 * It centralizes bread types, toppings, drink flavors, and chip types for reuse throughout the app.
 */
public class MenuOptions {
    //Bread types available for sandwiches
    public static final String[] BREAD_TYPES = {
            "White", "Wheat", "Rye", "Wrap"
    };

    //Premium meat options
    public static final String[] MEATS = {
            "Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon"
    };

    //Premium cheese options
    public static final String[] CHEESES = {
            "American", "Provolone", "Cheddar", "Swiss"
    };

    //Regular toppings (included with no extra cost)
    public static final String[] REGULAR_TOPPINGS = {
            "Lettuce", "Peppers", "Onions", "Tomatoes", "Jalapeños", "Cucumbers", "Pickles", "Guacamole", "Mushrooms"
    };

    //Available sauces
    public static final String[] SAUCES = {
            "Mayo", "Mustard", "Ketchup", "Ranch", "Thousand Islands", "Vinaigrette"
    };

    //Available drink flavors
    public static final String[] DRINK_FLAVORS = {
            "Cola", "Lemonade", "Iced Tea", "Orange Soda"
    };

    //Available chip types
    public static final String[] CHIP_TYPES = {
            "Classic", "BBQ", "Sour Cream & Onion", "Salt & Vinegar"
    };
}
