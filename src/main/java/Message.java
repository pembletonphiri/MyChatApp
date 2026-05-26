import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class Message {

    String messageID;
    int messageNumber;
    String recipient;
    String messageText;
    String messageHash;
    static int totalMessages = 0;

    // Generate Message ID
    public String generateMessageID() {

        Random random = new Random();

        int number = 100000000;

        messageID = String.valueOf(number + random.nextInt(900000000));

        return messageID;
    }

    // Check recipient format
    public boolean checkRecipientCell() {

        if (recipient.startsWith("+27") && recipient.length() == 12) {

            return true;

        } else {

            return false;
        }
    }

    // Check message length
    public boolean checkMessageLength() {

        if (messageText.length() <= 250) {

            return true;

        } else {

            return false;
        }
    }
        // Create message hash
    public String createMessageHash() {

        // First 2 digits of Message ID
        String firstTwoDigits = messageID.substring(0, 2);

        // Split message into words
        String[] words = messageText.split(" ");

        // First word
        String firstWord = words[0];

        // Last word
        String lastWord = words[words.length - 1];

        // Build hash
        messageHash = firstTwoDigits + ":" +
                      messageNumber + ":" +
                      firstWord.toUpperCase() +
                      lastWord.toUpperCase();

        return messageHash;
    }
        // Send / Store / Discard message
    public String sentMessage(int choice) {

        switch (choice) {

            case 1:
                totalMessages++;
                return "Message successfully sent.";

            case 2:
                return "Press 0 to delete the message.";

            case 3:
                return "Message successfully stored.";

            default:
                return "Invalid option.";
        }
    }
        // Return total sent messages
    public int returnTotalMessages() {

        return totalMessages;
    }
        // Display full message details
    public String printMessages() {

        return "Message ID: " + messageID +
               "\nMessage Hash: " + messageHash +
               "\nRecipient: " + recipient +
               "\nMessage: " + messageText;
    }
        // Store message in JSON format
    public void storeMessageToJSON() {

        try {

            FileWriter file = new FileWriter("messages.json", true);

            file.write("{\n");
            file.write("\"MessageID\": \"" + messageID + "\",\n");
            file.write("\"MessageHash\": \"" + messageHash + "\",\n");
            file.write("\"Recipient\": \"" + recipient + "\",\n");
            file.write("\"Message\": \"" + messageText + "\"\n");
            file.write("}\n\n");

            file.close();

        } catch (IOException e) {

            System.out.println("Error writing to file.");
        }
    }

}
