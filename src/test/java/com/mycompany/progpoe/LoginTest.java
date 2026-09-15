/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.progpoe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class LoginTest {
    
    Login obj = new Login("+27838968976", "kyl_1", "Ch&k3@ke99!", "Kyle", "Smith");
    
    
   
    // ASSERT EQUALS TESTS
    
    
    @Test
    public void TestValidUsernameMessage(){
        String expected = "Username successfully captured";
        String results = obj.usernameStatus();
        assertEquals(expected, results);
    }
    
    @Test
    public void TestInvalidUsernameMessage(){
        Login invalidObj = new Login(
                "+27838968976",
                "kyle!!!!!",
                "Ch&k3@ke99!",
                "Kyle",
                "Smith"
        );
        
        String expected = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length";
        String results = invalidObj.usernameStatus();
        assertEquals(expected, results);
    }
    
    @Test
    public void TestValidPasswordMessage(){
        String expected = "Password is successfully captured.";
        String results = obj.passwordStatus();
        assertEquals(expected, results);
    }
    
    @Test
    public void TestInvalidPasswordMessage(){
        Login invalidObj = new Login(
                "+27838968976",
                "kyl_1",
                "password",
                "Kyle",
                "Smith"
        );
        
        String expected = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character";
        String results = invalidObj.passwordStatus();
        assertEquals(expected, results);
    }
    
    @Test
    public void TestValidCellPhoneMessage(){
        String expected = "Cell phone number is successfully added.";
        String results = obj.cellPhoneStatus();
        assertEquals(expected, results);
    }
    
    @Test
    public void TestInvalidCellPhoneMessage(){
        Login invalidObj = new Login(
                "08966553",
                "kyl_1",
                "Ch&k3@ke99!",
                "Kyle",
                "Smith"
        );
        
        String expected = "Cell phone number incorrectly formatted or does not contain international code";
        String results = invalidObj.cellPhoneStatus();
        assertEquals(expected, results);
    }
    
    
    
    // ASSERT TRUE / FALSE TESTS
    
    
    @Test
    public void TestSuccessfulLogin(){
        String loginUsername = "kyl_1";
        String loginPassword = "Ch&k3@ke99!";
        
        boolean results = obj.loginUser(loginUsername, loginPassword);
        assertTrue(results);
    }
    
    @Test
    public void TestUnsuccessfulLogin(){
        String loginUsername = "kyl_1";
        String loginPassword = "wrongPassword";
        
        boolean results = obj.loginUser(loginUsername, loginPassword);
        assertFalse(results);
    }
    
    @Test
    public void TestValidUsername(){
        boolean results = obj.checkUserName();
        assertTrue(results);
    }
    
    @Test
    public void TestInvalidUsername(){
        Login invalidObj = new Login(
                "+27838968976",
                "kyle!!!!!",
                "Ch&k3@ke99!",
                "Kyle",
                "Smith"
        );
        
        boolean results = invalidObj.checkUserName();
        assertFalse(results);
    }
    
    @Test
    public void TestValidPassword(){
        boolean results = obj.CheckPasswordComplexity();
        assertTrue(results);
    }
    
    @Test
    public void TestInvalidPassword(){
        Login invalidObj = new Login(
                "+27838968976",
                "kyl_1",
                "password",
                "Kyle",
                "Smith"
        );
        
        boolean results = invalidObj.CheckPasswordComplexity();
        assertFalse(results);
    }
    
    @Test
    public void TestValidCellPhoneNumber(){
        boolean results = obj.checkCellPhoneNumber();
        assertTrue(results);
    }
    
    @Test
    public void TestInvalidCellPhoneNumber(){
        Login invalidObj = new Login(
                "08966553",
                "kyl_1",
                "Ch&k3@ke99!",
                "Kyle",
                "Smith"
        );
        
        boolean results = invalidObj.checkCellPhoneNumber();
        assertFalse(results);
    }
}