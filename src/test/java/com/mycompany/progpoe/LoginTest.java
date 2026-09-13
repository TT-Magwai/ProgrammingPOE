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
    
    Login obj = new Login("+27821234567", "kyl_1", "Ch&k3@ke99!", "Kyle", "Smith");
    
    @Test
    public void TestValidUsername(){
        boolean results = obj.checkUserName();
        assertTrue(results);
    }
    
    @Test
    public void TestInvalidUsername(){
        Login invalidObj = new Login(
                "+27821234567",
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
                "+27821234567",
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
                "0821234567",
                "kyl_1",
                "Ch&k3@ke99!",
                "Kyle",
                "Smith"
        );
        
        boolean results = invalidObj.checkCellPhoneNumber();
        assertFalse(results);
    }
    
    @Test
    public void TestSuccessfulRegistration(){
        String expected = "User registered successfully.";
        String results = obj.registerUser();
        assertEquals(expected, results);
    }
    
    @Test
    public void TestUnsuccessfulRegistration(){
        Login invalidObj = new Login(
                "+27821234567",
                "kyle!!!!!",
                "password",
                "Kyle",
                "Smith"
        );
        
        String expected = "Username is incorrectly formatted.";
        String results = invalidObj.registerUser();
        assertEquals(expected, results);
    }
    
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
    public void TestSuccessfulLoginStatus(){
        String loginUsername = "kyl_1";
        String loginPassword = "Ch&k3@ke99!";
        
        String expected = "Login successful.";
        String results = obj.returnLoginStatus(loginUsername, loginPassword);
        assertEquals(expected, results);
    }
    
    @Test
    public void TestUnsuccessfulLoginStatus(){
        String loginUsername = "kyl_1";
        String loginPassword = "wrongPassword";
        
        String expected = "Login failed.";
        String results = obj.returnLoginStatus(loginUsername, loginPassword);
        assertEquals(expected, results);
    }
}