/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progpoe;
import java.util.Arrays;



import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 *
 * @author Student
 */
public class MessageClass {
    
private static Message[] sentMessages = new Message[0];
private static Message[] disregardedMessages = new Message[0];
private static Message[] storedMessages = new Message[0];

private static String[] messageHashes = new String[0];
private static String[] messageIDs = new String[0];

public class Message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;

   public Message(String recipient, String message, int messageNumber) {
    this.messageID = String.valueOf(
            1000000000L + (long) (Math.random() * 9000000000L)
    );

    this.recipient = recipient;
    this.message = message;
    this.messageNumber = messageNumber;

    totalMessages++;
}

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    
public String checkRecipientCell() {
    if (recipient.matches("^\\+27\\d{9}$")) {
        return "Cell phone number successfully captured.";
    } else {
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }
}

    

public String validateMessage() {
    if (message.length() <= 250) {
        return "Message ready to send.";
    } else {
        int excess = message.length() - 250;

        return "Message exceeds 250 characters by "
                + excess + "; please reduce the size.";
    }
}


public String createMessageHash() {
    String[] words = message.trim().split("\\s+");

    String firstWord = words[0].replaceAll("[^A-Za-z0-9]", "");
    String lastWord = words[words.length - 1]
            .replaceAll("[^A-Za-z0-9]", "");

    messageHash = messageID.substring(0, 2) + ":"
            + messageNumber + ":"
            + (firstWord + lastWord).toUpperCase();

    return messageHash;
}


public String SentMessage(String choice) {
    if (choice.equalsIgnoreCase("Send")) {
        return "Message successfully sent";
    } else if (choice.equalsIgnoreCase("Store")) {
        return "Message successfully stored";
    } else if (choice.equalsIgnoreCase("Disregard")) {
        return "Press 0 to delete the message";
    } else {
        return "Invalid choice";
    }
}

public String printMessages() {
    return "========================"
            + "\nMessage details"
            + "\n==========================="
            + "\nMessage ID: " + messageID
            + "\nMessage Hash: " + messageHash
            + "\nRecipient: " + recipient
            + "\nMessage: " + message;
}

public int returnTotalMessagess() {
    return totalMessages;
}



public String storeMessage() {
    try {
        Path filePath = Paths.get("messages.json");

        if (messageHash == null || messageHash.isEmpty()) {
            createMessageHash();
        }

        String jsonMessage =
                "  {\n"
                + "    \"Message ID\": \"" + escapeJson(messageID) + "\",\n"
                + "    \"Message Hash\": \"" + escapeJson(messageHash) + "\",\n"
                + "    \"Recipient\": \"" + escapeJson(recipient) + "\",\n"
                + "    \"Message\": \"" + escapeJson(message) + "\"\n"
                + "  }";

        String existingJson = "";

        if (Files.exists(filePath)) {
            existingJson = Files.readString(
                    filePath, StandardCharsets.UTF_8).trim();
        }

        String updatedJson;

        if (existingJson.isEmpty() || existingJson.equals("[]")) {
            updatedJson = "[\n" + jsonMessage + "\n]\n";
        } else if (existingJson.startsWith("[")
                && existingJson.endsWith("]")) {

            String contents = existingJson.substring(
                    1, existingJson.length() - 1).trim();

            if (contents.isEmpty()) {
                updatedJson = "[\n" + jsonMessage + "\n]\n";
            } else {
                updatedJson = "[\n"
                        + contents + ",\n"
                        + jsonMessage + "\n]\n";
            }
        } else {
            return "Error: messages.json does not contain a valid JSON array.";
        }

        Files.writeString(
                filePath, updatedJson, StandardCharsets.UTF_8);

        return "Message successfully stored";

    } catch (IOException e) {
        return "Error storing message: " + e.getMessage();
    }
}

private String escapeJson(String value) {
    return value.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
}

public String getMessageID() {
    return messageID;
}

public String getMessageHash() {
    return messageHash;
}

public String getRecipient() {
    return recipient;
}

public String getMessage() {
    return message;
}

} //Message Ends here




private static int totalMessages = 0;

public void addSentMessage(Message msg) {
    sentMessages = Arrays.copyOf(sentMessages, sentMessages.length + 1);
    sentMessages[sentMessages.length - 1] = msg;
}

public void addDisregardedMessage(Message msg) {
    disregardedMessages = Arrays.copyOf(
            disregardedMessages, disregardedMessages.length + 1);
    disregardedMessages[disregardedMessages.length - 1] = msg;
}

public void addStoredMessage(Message msg) {
    storedMessages = Arrays.copyOf(storedMessages, storedMessages.length + 1);
    storedMessages[storedMessages.length - 1] = msg;
}

public void addMessageDetails(Message msg) {
    messageIDs = Arrays.copyOf(messageIDs, messageIDs.length + 1);
    messageIDs[messageIDs.length - 1] = msg.getMessageID();

    messageHashes = Arrays.copyOf(messageHashes, messageHashes.length + 1);
    messageHashes[messageHashes.length - 1] = msg.createMessageHash();
}

public String displayStoredSenderRecipient(String sender) {
    if (storedMessages.length == 0) {
        return "No stored messages available.";
    }

    StringBuilder output = new StringBuilder();

    for (Message msg : storedMessages) {
        output.append("\nSender: ").append(sender);
        output.append("\nRecipient: ").append(msg.getRecipient());
        output.append("\n-------------------------");
    }

    return output.toString();
   
}

public String displayLongestStoredMessage() {
    if (storedMessages.length == 0) {
        return "No stored messages available.";
    }

    Message longest = storedMessages[0];

    for (Message msg : storedMessages) {
        if (msg.getMessage().length()
                > longest.getMessage().length()) {
            longest = msg;
        }
    }

    return "Longest stored message: "
            + longest.getMessage();
}

public String searchMessageByID(String searchID) {

    for (Message msg : storedMessages) {
        if (msg.getMessageID().equals(searchID)) {
            return "Message found!"
                    + "\nRecipient: " + msg.getRecipient()
                    + "\nMessage: " + msg.getMessage();
        }
    }

    return "Message ID not found.";
}

public String searchMessagesByRecipient(String searchRecipient) {

    StringBuilder results = new StringBuilder();
    boolean found = false;

    for (Message msg : storedMessages) {
        if (msg.getRecipient().equals(searchRecipient)) {

            results.append("\nRecipient: ")
                    .append(msg.getRecipient());

            results.append("\nMessage: ")
                    .append(msg.getMessage());

            results.append("\n----------------------");

            found = true;
        }
    }

    if (!found) {
        return "No messages found for this recipient.";
    }

    return results.toString();
}

public String deleteMessageByHash(String searchHash) {

    for (int i = 0; i < storedMessages.length; i++) {

        if (storedMessages[i].getMessageHash().equals(searchHash)) {

            Message[] updatedMessages =
                    new Message[storedMessages.length - 1];

            for (int j = 0, k = 0; j < storedMessages.length; j++) {
                if (j != i) {
                    updatedMessages[k++] = storedMessages[j];
                }
            }

            storedMessages = updatedMessages;

            return "Message successfully deleted.";
        }
    }

    return "Message hash not found.";
}


}//MessageClass Ends here
