package com.pluralsight;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.Scanner;
//this class handles all the user interaction through the console
public class UserInterface {
    private Scanner scanner;

    //Constructor
    public UserInterface() {
        scanner = new Scanner(System.in); //Scanner reads user input
    }
    //This method displays the main home screen
    public void showHomeScreen() {
        while (true) {
            //print menu options
            System.out.println("Welcome to DELI-cious!");
            System.out.println("1) New Order");
            System.out.println("0) Exit");

            //Ask user for input
            System.out.print("Please choose a option: ");
            String choice = scanner.nextLine();

            //Decide what to do based on user input
            if (choice.equals("1")) {
                System.out.println("Starting new order...\n");
                //Show menu here
            } else if (choice.equals("0")) {
                System.out.println("Have a blessed day, thanks for visiting DELI-cious!");
                break; //exits the loop and end program
            } else {
                System.out.println("Invalid input please try again.\n");
            }

        }
    }

}
