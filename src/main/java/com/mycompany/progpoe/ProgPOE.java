/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.progpoe;

import java.util.Scanner;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Student
 */
public class ProgPOE {

    public static void main(String[] args) {
        



        Scanner input = new Scanner(System.in);
        System.out.println("\n---- Register ----");

        System.out.println("Please enter your first name: ");
        String FirstName = input.nextLine();
        System.out.print("Please enter your last name: ");
        String LastName = input.nextLine();

        System.out.print("Please enter your Cellphone number (use the correct country code):");
        String cellNum = input.nextLine();

        System.out.print("Please enter your username (make use of an underscore):");
        String username = input.nextLine();

        System.out.print("Please enter your password (make use of a capital letter, a number, and a special character):");
        String password = input.nextLine();

        Login login = new Login(cellNum, username, password, FirstName, LastName);
        System.out.println(login.registerUser());

        if (login.checkCellPhoneNumber()) {
            System.out.println("Cell phone number is successfully added.");
        } else {
            System.out.println("Cell phone number incorrectly formatted or does not contain international code");
        }

        if (login.checkUserName()) {
            System.out.println("Username successfully captured");
        } else {
            System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length");
        }

        if (login.CheckPasswordComplexity()) {
            System.out.println("Password is successfully captured.");
        } else {
            System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character");
        }

        System.out.println("\n---- Login ----");

        System.out.print("Enter your username: ");
        String loginUsername = input.nextLine();

        System.out.print("Enter your password: ");
        String loginPassword = input.nextLine();

       System.out.println(login.returnLoginStatus(loginUsername, loginPassword));



if (login.loginUser(loginUsername, loginPassword)) {
    System.out.println("Welcome to QuickChat.");

    boolean running = true;

    while (running) {
        System.out.println("\n---- QuickChat Menu ----");
        System.out.println("1. Send Messages");
        System.out.println("2. Show recently sent messages");
        System.out.println("3. Quit");
        System.out.print("Enter your choice: ");

        String choice = input.nextLine();

        switch (choice) {
            
case "1":
    System.out.print("How many messages would you like to send? ");
    int numberOfMessages;

    try {
        numberOfMessages = Integer.parseInt(input.nextLine());

        if (numberOfMessages <= 0) {
            System.out.println("Please enter a number greater than zero.");
            break;
        }

        for (int i = 0; i < numberOfMessages; i++) {
            System.out.println("\nMessage " + (i + 1)
                    + " of " + numberOfMessages);

            System.out.print("Enter recipient cellphone number: ");
            String recipient = input.nextLine();

            System.out.print("Enter your message: ");
            String message = input.nextLine();

            MessageClass outer = new MessageClass();
            MessageClass.Message msg =
                    outer.new Message(recipient, message, i);

            if (message.length() >= 250) {
                System.out.println(
                        "Please enter a message of less than 250 characters.");
                i--;
                continue;
            }

            System.out.println(msg.checkRecipientCell());

            if (!recipient.matches("^\\+27\\d{9}$")) {
                i--;
                continue;
            }

            System.out.println("Message ready to send.");
            System.out.println("Message ID and hash: "
                    + msg.createMessageHash());

            System.out.println("1. Send Message");
            System.out.println("2. Disregard Message");
            System.out.println("3.  Store Message in a JSON file");
            System.out.print("Choose an option: ");

            String messageChoice = input.nextLine();

            switch (messageChoice) {
                case "1":
                    System.out.println(msg.SentMessage("Send"));
                    System.out.println(msg.printMessages());
                    break;

                case "2":
                    System.out.println(msg.SentMessage("Disregard"));
                    break;

                case "3":
                    System.out.println(msg.storeMessage());
                    break;

                default:
                    System.out.println("Invalid choice. Message not processed.");
                    i--;
                    break;
            }
        }

    } catch (NumberFormatException e) {
        System.out.println("Please enter a valid whole number.");
    }
    break;


            case "2":
                System.out.println("Coming Soon.");
                break;

            case "3":
                running = false;
                System.out.println("Thank you for using QuickChat.");
                break;

            default:
                System.out.println("Invalid option. Please choose 1, 2, or 3.");
        }
    }

} else {
    System.out.println("Login failed. Messaging is unavailable.");
}


    }
}

    

