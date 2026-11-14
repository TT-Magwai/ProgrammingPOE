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
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
   
   public Message(String messageID, String messageHash,
        String recipient, String message) {

    this.messageID = messageID;
    this.messageHash = messageHash;
    this.recipient = recipient;
    this.message = message;
    this.messageNumber = 0;

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

} /*Message Ends here
....
....
....
.....
Message Ends here*/




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

    for (Message msg : sentMessages) {
        if (msg.getRecipient().equals(searchRecipient)) {
            results.append("\nRecipient: ").append(msg.getRecipient());
            results.append("\nMessage: ").append(msg.getMessage());
            results.append("\n----------------------");
            found = true;
        }
    }

    for (Message msg : storedMessages) {
        if (msg.getRecipient().equals(searchRecipient)) {
            results.append("\nRecipient: ").append(msg.getRecipient());
            results.append("\nMessage: ").append(msg.getMessage());
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

        if (searchHash != null && searchHash.equals(storedMessages[i].getMessageHash()))  {

            Message[] updatedMessages =
                    new Message[storedMessages.length - 1];

            for (int j = 0, k = 0; j < storedMessages.length; j++) {
                if (j != i) {
                    updatedMessages[k++] = storedMessages[j];
                }
            }

            try {
                StringBuilder json = new StringBuilder("[\n");

                for (int j = 0; j < updatedMessages.length; j++) {
                    Message msg = updatedMessages[j];
                    if (msg.getMessageHash() == null || msg.getMessageHash().isEmpty()) {
    msg.createMessageHash();
}

                    json.append("  {\n")
                        .append("    \"Message ID\": \"")
                        .append(escapeJsonValue(msg.getMessageID()))
                        .append("\",\n")
                        .append("    \"Message Hash\": \"")
                        .append(escapeJsonValue(msg.getMessageHash()))
                        .append("\",\n")
                        .append("    \"Recipient\": \"")
                        .append(escapeJsonValue(msg.getRecipient()))
                        .append("\",\n")
                        .append("    \"Message\": \"")
                        .append(escapeJsonValue(msg.getMessage()))
                        .append("\"\n")
                        .append("  }");

                    if (j < updatedMessages.length - 1) {
                        json.append(",");
                    }

                    json.append("\n");
                }

                json.append("]\n");

                Files.writeString(
                        Paths.get("messages.json"),
                        json.toString(),
                        StandardCharsets.UTF_8
                );

                storedMessages = updatedMessages;

                messageIDs = new String[storedMessages.length];
                messageHashes = new String[storedMessages.length];

                for (int j = 0; j < storedMessages.length; j++) {
                    messageIDs[j] = storedMessages[j].getMessageID();
                    messageHashes[j] = storedMessages[j].getMessageHash();
                }

                return "Message successfully deleted.";

            } catch (IOException e) {
                return "Error deleting message: " + e.getMessage();
            }
        }
    }

    return "Message hash not found.";
}


private String escapeJsonValue(String value) {
    return value.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
}

public String displayMessageReport() {

    if (storedMessages.length == 0) {
        return "No stored messages available.";
    }

    StringBuilder report = new StringBuilder();

    report.append("\n========== MESSAGE REPORT ==========\n");

    for (Message msg : storedMessages) {
        report.append("\nMessage ID: ")
                .append(msg.getMessageID());

        report.append("\nMessage Hash: ")
                .append(msg.getMessageHash());

        report.append("\nRecipient: ")
                .append(msg.getRecipient());

        report.append("\nMessage: ")
                .append(msg.getMessage());

        report.append("\n------------------------------------");
    }

    return report.toString();
}

public String loadStoredMessages() {

    try {
        Path filePath = Paths.get("messages.json");

        if (!Files.exists(filePath)) {
            return "No messages.json file found.";
        }

        String json = Files.readString(
                filePath, StandardCharsets.UTF_8);

        ArrayList<Message> loadedMessages = new ArrayList<>();

        Pattern objectPattern =
                Pattern.compile("\\{(.*?)\\}", Pattern.DOTALL);

        Pattern fieldPattern =
                Pattern.compile("\"(Message ID|Message Hash|Recipient|Message)\"\\s*:\\s*\"((?:\\\\.|[^\"\\\\])*)\"");

        Matcher objectMatcher = objectPattern.matcher(json);

        while (objectMatcher.find()) {

            String object = objectMatcher.group();

            Matcher fieldMatcher = fieldPattern.matcher(object);

            String id = "";
            String hash = "";
            String recipient = "";
            String message = "";

            while (fieldMatcher.find()) {

                String key = fieldMatcher.group(1);
                String value = unescapeJson(fieldMatcher.group(2));

                switch (key) {
                    case "Message ID":
                        id = value;
                        break;

                    case "Message Hash":
                        hash = value;
                        break;

                    case "Recipient":
                        recipient = value;
                        break;

                    case "Message":
                        message = value;
                        break;
                }
            }

            if (!id.isEmpty() && !hash.isEmpty()
                    && !recipient.isEmpty()) {

                loadedMessages.add(
                        new Message(id, hash, recipient, message)
                );
            }
        }

        storedMessages = loadedMessages.toArray(new Message[0]);

        messageIDs = new String[storedMessages.length];
        messageHashes = new String[storedMessages.length];

        for (int i = 0; i < storedMessages.length; i++) {
            messageIDs[i] = storedMessages[i].getMessageID();
            messageHashes[i] = storedMessages[i].getMessageHash();
        }

        return "Stored messages successfully loaded.";

    } catch (IOException e) {
        return "Error loading messages: " + e.getMessage();
    }
}

private String unescapeJson(String value) {

    return value.replace("\\\"", "\"")
            .replace("\\n", "\n")
            .replace("\\r", "\r")
            .replace("\\t", "\t")
            .replace("\\\\", "\\");
}

public String[] getSentMessageTexts() {

    String[] messages = new String[sentMessages.length];

    for (int i = 0; i < sentMessages.length; i++) {
        messages[i] = sentMessages[i].getMessage();
    }

    return messages;
}

public String[] getDisregardedMessageTexts() {
    String[] messages = new String[disregardedMessages.length];

    for (int i = 0; i < disregardedMessages.length; i++) {
        messages[i] = disregardedMessages[i].getMessage();
    }

    return messages;
}

public String[] getMessageIDs() {
    return messageIDs;
}

public String[] getMessageHashes() {
    return messageHashes;
}

public String[] getStoredMessageTexts() {
    String[] messages = new String[storedMessages.length];

    for (int i = 0; i < storedMessages.length; i++) {
        messages[i] = storedMessages[i].getMessage();
    }

    return messages;
}

}//MessageClass Ends here
