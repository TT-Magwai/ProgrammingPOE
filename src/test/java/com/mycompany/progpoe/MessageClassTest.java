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

@Test
public void testSentMessagesArray() {

    MessageClass outer = new MessageClass();

    MessageClass.Message msg = outer.new Message(
            "+27834557896",
            "Did you get the cake?",
            0
    );

    outer.addSentMessage(msg);

    assertTrue(
            java.util.Arrays.asList(outer.getSentMessageTexts())
                    .contains("Did you get the cake?")
    );
}

@Test
public void testDisplayLongestStoredMessage() {

    MessageClass outer = new MessageClass();

    MessageClass.Message msg1 = outer.new Message(
            "+27834557896",
            "Did you get the cake?",
            0
    );

    MessageClass.Message msg2 = outer.new Message(
            "+27838884567",
            "Where are you? You are late! I have asked you to be on time.",
            1
    );

    outer.addStoredMessage(msg1);
    outer.addStoredMessage(msg2);

    String result = outer.displayLongestStoredMessage();

    assertTrue(result.contains(
            "Where are you? You are late! I have asked you to be on time."
    ));
}

@Test
public void testSearchMessageByID() {
    MessageClass outer = new MessageClass();

    MessageClass.Message msg = outer.new Message(
            "+27838884567",
            "Where are you?",
            2
    );

    outer.addStoredMessage(msg);

    String result = outer.searchMessageByID(msg.getMessageID());

    assertTrue(result.contains("+27838884567"));
    assertTrue(result.contains("Where are you?"));
}

@Test
public void testSearchMessagesByRecipient() {
    MessageClass outer = new MessageClass();

    MessageClass.Message msg1 = outer.new Message(
            "+27838884567",
            "Where are you?",
            1
    );

    MessageClass.Message msg2 = outer.new Message(
            "+27838884567",
            "I am leaving now.",
            2
    );

    outer.addStoredMessage(msg1);
    outer.addStoredMessage(msg2);

    String result = outer.searchMessagesByRecipient("+27838884567");

    assertTrue(result.contains("Where are you?"));
    assertTrue(result.contains("I am leaving now."));
}

@Test
public void testDeleteMessageByHash() {
    MessageClass outer = new MessageClass();

    MessageClass.Message msg = outer.new Message(
            "+27838884567",
            "This message must be deleted.",
            3
    );
    msg.createMessageHash();
    outer.addStoredMessage(msg);

    String result = outer.deleteMessageByHash(msg.getMessageHash());

    assertEquals("Message successfully deleted.", result);
}

@Test
public void testDisplayMessageReport() {
    MessageClass outer = new MessageClass();

    MessageClass.Message msg = outer.new Message(
            "+27838884567",
            "This is a report test.",
            4
    );

    msg.createMessageHash();
    outer.addStoredMessage(msg);

    String result = outer.displayMessageReport();

    assertTrue(result.contains(msg.getMessageID()));
    assertTrue(result.contains(msg.getMessageHash()));
    assertTrue(result.contains("+27838884567"));
    assertTrue(result.contains("This is a report test."));
}

@Test
public void testLoadStoredMessages() {
    MessageClass outer = new MessageClass();

    String result = outer.loadStoredMessages();

    assertTrue(result.contains("successfully"));
}

@Test
public void testDisplayStoredSenderRecipient() {
    MessageClass outer = new MessageClass();

    MessageClass.Message msg = outer.new Message(
            "+27838884567",
            "Hello there.",
            5
    );

    outer.addStoredMessage(msg);

    String result = outer.displayStoredSenderRecipient("John Smith");

    assertTrue(result.contains("John Smith"));
    assertTrue(result.contains("+27838884567"));
}

@Test
public void testDisregardedMessagesArray() {
    MessageClass outer = new MessageClass();

    MessageClass.Message msg = outer.new Message(
            "+27834557896",
            "I do not want to send this message.",
            6
    );

    outer.addDisregardedMessage(msg);

    assertTrue(
            java.util.Arrays.asList(outer.getDisregardedMessageTexts())
                    .contains("I do not want to send this message.")
    );
}

@Test
public void testMessageIDsAndHashesArrays() {
    MessageClass outer = new MessageClass();

    MessageClass.Message msg = outer.new Message(
            "+27834557896",
            "Testing message arrays.",
            7
    );

    msg.createMessageHash();
    outer.addMessageDetails(msg);

    assertTrue(
            java.util.Arrays.asList(outer.getMessageIDs())
                    .contains(msg.getMessageID())
    );

    assertTrue(
            java.util.Arrays.asList(outer.getMessageHashes())
                    .contains(msg.getMessageHash())
    );
}

@Test
public void testStoreMessageInJSON() {
    MessageClass outer = new MessageClass();

    MessageClass.Message msg = outer.new Message(
            "+27838884567",
            "Testing JSON storage.",
            8
    );

    msg.createMessageHash();

    String result = msg.storeMessage();

    assertEquals("Message successfully stored", result);

    outer.loadStoredMessages();

    String searchResult = outer.searchMessageByID(msg.getMessageID());

    assertTrue(searchResult.contains("Testing JSON storage."));
}

@Test
public void testStoredMessagesArray() {
    MessageClass outer = new MessageClass();

    MessageClass.Message msg = outer.new Message(
            "+27838884567",
            "Testing stored messages array.",
            9
    );

    outer.addStoredMessage(msg);

    assertTrue(
            java.util.Arrays.asList(outer.getStoredMessageTexts())
                    .contains("Testing stored messages array.")
    );
}

@Test
public void testSearchSentMessageByRecipient() {
    MessageClass outer = new MessageClass();

    MessageClass.Message msg = outer.new Message(
            "+27839999999",
            "This is a sent message.",
            10
    );

    outer.addSentMessage(msg);

    String result = outer.searchMessagesByRecipient("+27839999999");

    assertTrue(result.contains("This is a sent message."));
}



}

