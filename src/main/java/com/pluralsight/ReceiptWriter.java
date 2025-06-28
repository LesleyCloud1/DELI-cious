package com.pluralsight;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptWriter {

    //Static method that saves an order to a receipt file
    public static void saveReceipt(Order order) {
        //Ensure the 'receipts' folder exists before trying to write to it
        File dir = new File("receipts");
        if (!dir.exists()) {
            dir.mkdirs(); //Create the folder if it doesn't already exist
        }

        //Generate a timestamp-based file name (e.g., 20250530-091957.txt)
        String timestamp = generateTimestamp();
        String filename = "receipts/" + timestamp + ".txt";

        //Try writing the order details to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(order.getOrderSummary());
        } catch (IOException e) {
            System.out.println("Error writing receipt: " + e.getMessage());
        }
    }

    //Helper method to generate a timestamp like 20250530-091957
    private static String generateTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        return now.format(formatter);
    }
}