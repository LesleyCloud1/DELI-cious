package com.pluralsight;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    @Test void comboIncludesPremiumIngredientsAndSides() {
        Sandwich sandwich = new Sandwich("wheat", 8, true);
        sandwich.addMeat("turkey", true);
        sandwich.addCheese("cheddar", true);
        sandwich.addTopping("lettuce");
        sandwich.addSauce("mustard");
        Order order = new Order();
        order.addSandwich(sandwich);
        order.addDrink("medium", "tea");
        order.addChips("plain");
        assertEquals(16.10, order.getTotal(), 0.0001);
        assertTrue(order.getOrderSummary().contains("turkey"));
    }
    @Test void unsupportedSizeCannotCreateFreeSandwich() {
        assertThrows(IllegalArgumentException.class, () -> new Sandwich("white", 6, false));
    }
    @Test void invalidDrinkDoesNotChangeOrder() {
        Order order = new Order();
        assertThrows(IllegalArgumentException.class, () -> order.addDrink("huge", "tea"));
        assertEquals(0, order.getTotal());
        assertFalse(order.getOrderSummary().contains("huge"));
    }
}
