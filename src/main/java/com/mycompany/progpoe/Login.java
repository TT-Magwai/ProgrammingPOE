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

    
}

