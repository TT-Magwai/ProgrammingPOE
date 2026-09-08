/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.progpoe;

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class ProgPOE {

    public static void main(String[] args) {
        



        Scanner input = new Scanner(System.in);

        System.out.println("Please enter your first name: ");
        String FirstName = input.nextLine();
        System.out.print("Please enter your last name: ");
        String LastName = input.nextLine();

        System.out.print("Please enter your Cellphone number:");
        String cellNum = input.nextLine();

        System.out.print("Please enter your username:");
        String username = input.nextLine();

        System.out.print("Please enter your password:");
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
    System.out.println("Welcome " + FirstName + " " + LastName
            + ", it is great to see you again.");
}

    }
}

    

