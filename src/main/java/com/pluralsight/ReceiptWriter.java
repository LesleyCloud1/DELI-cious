package com.pluralsight;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptWriter {
    public static boolean saveReceipt(Order order) {
        try {
            Path directory = Path.of("receipts");
            Files.createDirectories(directory);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
            // A unique suffix prevents two checkouts in one second overwriting each other.
            Path receipt = Files.createTempFile(directory, timestamp + "-", ".txt");
            Files.writeString(receipt, order.getOrderSummary());
            return true;
        } catch (IOException e) {
            System.out.println("Error writing receipt: " + e.getMessage());
            return false;
        }
    }
}
