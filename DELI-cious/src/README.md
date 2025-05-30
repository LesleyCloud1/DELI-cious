
# 🥪 DELI-cious - Capstone Java CLI Ordering App

**Build Your Sandwich, Byte by Byte!**

DELI-cious is a command-line Java application that simulates a point-of-sale system for a sandwich shop. Users can create customized sandwich orders, add drinks and chips, and save receipts. It demonstrates solid object-oriented programming (OOP) practices and is designed for beginner Java developers.

## 🚀 Features

- Fully interactive CLI interface for ordering
- Custom sandwich builder:
    - Bread type
    - Size (4", 8", 12")
    - Toasted or not
    - Add meats, cheeses, regular toppings, and sauces
- Add drinks and chips separately
- NEW! 🧃 Combo Option – automatically adds drink and chips with sandwich
- Save order receipt to file, named with timestamp
- Emotive and user-friendly interface with emojis 😄

## 🧾 Sample Menu Output

```
==========================================
          🥪  WELCOME TO DELI-cious  🥪
       Build Your Sandwich, Byte by Byte!
==========================================
What would you like to do?
1) 🛒 Start a New Order
0) ❌ Exit
```

## 🧠 Key OOP Concepts Used

- Encapsulation (Order, Sandwich classes)
- Composition (Sandwich contains meats, toppings, etc.)
- Separation of Concerns (UserInterface handles interaction, Order handles data)

## ✨ Special Code Highlight

```java
System.out.print("Would you like to make it a combo? (yes/no): ");
if (scanner.nextLine().equalsIgnoreCase("yes")) {
    displayDrinkOptions(order);
    displayChipOptions(order);
}
```

This snippet prompts the user to upgrade their sandwich to a combo, enhancing usability while practicing clean method design.

## 📁 Screenshots

_Add screenshots here by uploading to GitHub or embedding locally_

## 📚 Requirements

- Java 17
- IntelliJ IDEA Community Edition

## 📦 Running the App

1. Clone the repo
2. Open in IntelliJ
3. Run `Application.java`
