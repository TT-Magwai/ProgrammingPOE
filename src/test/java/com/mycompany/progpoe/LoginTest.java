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
        String username = "kyl_1";
        boolean results = obj.checkUserName();
        assertTrue(results);
    }
    
      @Test
    public void TestInvalidUsername(){
        String username = "kyle!!!!!";
        boolean results = obj.checkUserName();
        assertFalse(results);
    }
    
    
}
