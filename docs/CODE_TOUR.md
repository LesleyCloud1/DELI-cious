# DELI-cious: a plain-language code tour

## What the program does

You are building a small checkout system for a sandwich shop. The program keeps the current order in memory while the customer chooses items, then writes a receipt to a file. There is no real payment processing.

## Follow one order

`Application.main()` creates the user interface. `UserInterface.showHomeScreen()` repeats the main menu until the customer exits. Starting an order creates a fresh `Order`. Choosing a sandwich enters `buildSandwich()`, which collects bread, size, toasting, ingredients, and optional sides.

The resulting `Sandwich` is added to the `Order`. At checkout, `getOrderSummary()` formats the items and total. `ReceiptWriter.saveReceipt()` creates the receipts directory if needed, writes a uniquely named file, and tells the menu whether the save succeeded.

All Java files are under `src/main/java/com/pluralsight/`. `MenuOptions.java` declares the `com.pluralsight.util` package even though its source file is currently in the parent folder; Maven compiles it, but moving it into a matching `util/` directory would improve organization.

## Classes, objects, and constructors

A class is the recipe; an object is one actual instance made from it. `Sandwich` is the class. A particular eight-inch wheat sandwich is an object. Its constructor sets the initial bread, size, toasted flag, ingredient lists, and base price.

Each ingredient list is an `ArrayList`: a collection that can grow as choices are added. `addMeat()` and `addCheese()` update the lists and prices. Their `switch` expressions choose a price for the sandwich size. Regular toppings and sauces add no cost.

## Why composition matters

An `Order` contains sandwiches, drinks, and chips. This is composition: making a larger object from smaller parts. It is different from inheritance, where one class is a specialized kind of another. The current sandwich app does not need to claim inheritance to demonstrate good object modeling.

The price belongs to the model, not just the menu text. That means tests can construct an order directly without typing through every prompt. One test builds an eight-inch sandwich with extra turkey and cheese, a medium drink, and chips, and expects $16.10.

## Validation and failures

Numeric prompts read a whole line and try to parse an integer. When parsing fails, the prompt retries. Sandwich sizes must be 4, 8, or 12; drinks must use a supported size. These checks prevent a typo from creating an item with a zero price.

An I/O error means the operating system could not read or write a file. The receipt writer returns false on a write error so the menu does not report a successful save. Unique filenames prevent two checkouts in one second from overwriting each other.

## Important limitations

Prices use `double`, which can have binary rounding error; formatted output hides small differences but does not remove them. A future money type should use decimal arithmetic. `Order` accumulates the sandwich price when it is added, so changing the sandwich afterward would not automatically update that total. The UI currently finishes a sandwich before adding it. Empty-order checkout and end-of-input behavior still need work.

## Concepts to explain in an interview

Encapsulation keeps ingredient and price state together. Composition models an order containing items. Separation of responsibilities keeps menu interaction, pricing, and file output in different classes. A regression test protects behavior that previously failed, such as unsupported item sizes.

## Try it yourself

Run `mvn test`, then start the app using the README. Build a plain sandwich, then repeat with one extra meat. Predict the difference before checkout. Open the receipt and find where each printed line comes from in `getOrderSummary()` and `getSummary()`.

Explain aloud: “The interface gathers choices, the models calculate the order, and the receipt writer stores the result.”
