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

    }
}
