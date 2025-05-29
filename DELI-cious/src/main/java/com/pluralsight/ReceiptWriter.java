package com.pluralsight;

import com.pluralsight.Order;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptWriter {

    //Static method that saves an order to a receipt file
    public static void saveReceipt(Order order) {
        //generate a timestamp-based file name
        String timestamp = generateTimestamp();
        String filename = "receipts/" + timestamp + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
        //write the order summary to the file
        writer.write(order.getOrderSummary());
    } catch (IOException e) {
        System.out.println("Error writing receipt: " + e.getMessage());
    }
}
//Helper method to generate timestamp like 20250523-151122
private static String generateTimestamp() {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
    return now.format(formatter);
}
}
