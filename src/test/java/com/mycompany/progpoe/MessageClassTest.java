/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package com.mycompany.progpoe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class MessageClassTest {

    @Test
    public void testValidMessageLength() {
        MessageClass outer = new MessageClass();

        MessageClass.Message msg = outer.new Message(
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?",
                0
        );

        assertEquals("Message ready to send.",
                msg.validateMessage());
    }
    
@Test
public void testInvalidMessageLength() {
    MessageClass outer = new MessageClass();

    String longMessage = "A".repeat(300);

    MessageClass.Message msg = outer.new Message(
            "+27718693002",
            longMessage,
            1
    );

    assertEquals(
            "Message exceeds 250 characters by 50; please reduce the size.",
            msg.validateMessage()
    );
}

@Test
public void testValidRecipientCell() {
    MessageClass outer = new MessageClass();

    MessageClass.Message msg = outer.new Message(
            "+27718693002",
            "Hello, how are you?",
            2
    );

    assertEquals(
            "Cell phone number successfully captured.",
            msg.checkRecipientCell()
    );
}


@Test
public void testInvalidRecipientCell() {
    MessageClass outer = new MessageClass();

    MessageClass.Message msg = outer.new Message(
            "08575975889",
            "Hello, how are you?",
            3
    );

    assertEquals(
            "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
            msg.checkRecipientCell()
    );
}



@Test
public void testCreateMessageHash() {
    MessageClass outer = new MessageClass();

    MessageClass.Message msg = outer.new Message(
            "+27718693002",
            "Hi Mike, can you join us for dinner tonight?",
            0
    );

    String hash = msg.createMessageHash();

    assertEquals(true, msg.checkMessageID());
    assertEquals("0:HITONIGHT", hash.substring(3));
}

@Test
public void testCheckMessageID() {
    MessageClass outer = new MessageClass();

    MessageClass.Message msg = outer.new Message(
            "+27718693002",
            "Hello, how are you?",
            5
    );

    assertEquals(true, msg.checkMessageID());
}


@Test
public void testSendMessage() {
    MessageClass outer = new MessageClass();

    MessageClass.Message msg = outer.new Message(
            "+27718693002",
            "Hi Mike, can you join us for dinner tonight?",
            0
    );

    String result = msg.SentMessage("Send");

    assertEquals("Message successfully sent", result);
}


@Test
public void testStoreMessage() {
    MessageClass outer = new MessageClass();

    MessageClass.Message msg = outer.new Message(
            "+27718693002",
            "Hi Mike, can you join us for dinner tonight?",
            0
    );

    String result = msg.SentMessage("Store");

    assertEquals("Message successfully stored", result);
}


@Test
public void testDisregardMessage() {
    MessageClass outer = new MessageClass();

    MessageClass.Message msg = outer.new Message(
            "+27718693002",
            "Hi Mike, can you join us for dinner tonight?",
            0
    );

    String result = msg.SentMessage("Disregard");

    assertEquals("Press 0 to delete the message", result);
}


@Test
public void testInvalidMessageChoice() {
    MessageClass outer = new MessageClass();

    MessageClass.Message msg = outer.new Message(
            "+27718693002",
            "Hi Mike, can you join us for dinner tonight?",
            0
    );

    String result = msg.SentMessage("Wrong");

    assertEquals("Invalid choice", result);
}


@Test
public void testPrintMessages() {
    MessageClass outer = new MessageClass();

    MessageClass.Message msg = outer.new Message(
            "+27718693002",
            "Hi Mike, can you join us for dinner tonight?",
            0
    );

    msg.createMessageHash();

    String result = msg.printMessages();

    assertTrue(result.contains("Message ID:"));
    assertTrue(result.contains("Message Hash:"));
    assertTrue(result.contains("Recipient: +27718693002"));
    assertTrue(result.contains("Message: Hi Mike, can you join us for dinner tonight?"));
}


@Test
public void testReturnTotalMessages() {
    MessageClass outer = new MessageClass();

    MessageClass.Message msg = outer.new Message(
            "+27718693002",
            "Hi Mike, can you join us for dinner tonight?",
            0
    );

    assertTrue(msg.returnTotalMessagess() > 0);
}



}

