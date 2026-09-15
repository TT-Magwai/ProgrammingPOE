/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progpoe;

/**
 *
 * @author Student
 */
public class Login {
    
    public String FirstName;
    public String LastName;       
    public String cellNum;
    public String username;
    public String password;
    
     public Login(String cellNum,String username,String password, String FirstName, String LastName){
    
    this.cellNum= cellNum;
    this.username=username;
    this.password=password;
    this.LastName=LastName;
    this.FirstName=FirstName;
    }
  
    public boolean checkUserName(){
      return username.contains("_") && username.length() <=5;
    }
    
    public boolean CheckPasswordComplexity(){
         return password.length() >= 8
                && password.matches(".*[A-Z].*")
                && password.matches(".*[0-9].*")
                && password.matches(".*[^a-zA-Z0-9].*");
    }
    
    
    public boolean checkCellPhoneNumber(){
    return cellNum.startsWith("+27") && cellNum.length()==12;
    }
    
    
    public String registerUser() {

    if (!checkUserName()) {
        return "Username is incorrectly formatted.";
    }

    if (!CheckPasswordComplexity()) {
        return "Password does not meet complexity requirements.";
    }

    return "User registered successfully.";
}
    public boolean loginUser(String loginUsername, String loginPassword) {
    return loginUsername.equals(username)
            && loginPassword.equals(password);

}


public String returnLoginStatus(String loginUsername, String loginPassword) {

    if (loginUser(loginUsername, loginPassword)) {
        return "Login successful.";
    } else {
        return "Login failed.";
    }
    

}
public String usernameStatus() {
    if (checkUserName()) {
        return "Username successfully captured";
    } else {
        return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length";
    }
}

public String passwordStatus() {
    if (CheckPasswordComplexity()) {
        return "Password is successfully captured.";
    } else {
        return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character";
    }
}

public String cellPhoneStatus() {
    if (checkCellPhoneNumber()) {
        return "Cell phone number is successfully added.";
    } else {
        return "Cell phone number incorrectly formatted or does not contain international code";
    }
}
    
}

