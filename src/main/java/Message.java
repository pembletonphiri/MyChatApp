import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

public class Message {

    String messageID;
    int messageNumber;
    String recipient;
    String messageText;
    String messageHash;
    static int totalMessages = 0;

//new arrays
static java.util.ArrayList<Message> sentMessages = new java.util.ArrayList<>();
static java.util.ArrayList<Message> storedMessages = new java.util.ArrayList<>();
static java.util.ArrayList<Message> disregardedMessages = new java.util.ArrayList<>();

static java.util.ArrayList<String> messageHashes = new java.util.ArrayList<>();
static java.util.ArrayList<String> messageIDs = new java.util.ArrayList<>();

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
public static void displayReport() {

    if (sentMessages.isEmpty()) {
        System.out.println("No messages have been sent.");
        return;
    }

    for (Message message : sentMessages) {

        System.out.println("----------------------------");
        System.out.println("Message Hash : " + message.messageHash);
        System.out.println("Recipient    : " + message.recipient);
        System.out.println("Message      : " + message.messageText);
    }
}
public static void displaySenderRecipient() {

    if (storedMessages.isEmpty()) {
        System.out.println("No stored messages available.");
        return;
    }

    System.out.println("\n=== STORED MESSAGES ===");

    for (Message msg : storedMessages) {

        System.out.println("------------------------");
        System.out.println("Sender    : QuickChat");
        System.out.println("Recipient : " + msg.recipient);

    }
}
public static void displayLongestMessage() {

    if (storedMessages.isEmpty()) {

        System.out.println("No stored messages available.");
        return;
    }

    Message longest = storedMessages.get(0);

    for (Message msg : storedMessages) {

        if (msg.messageText.length() > longest.messageText.length()) {
            longest = msg;
        }
    }

    System.out.println("\n=== LONGEST MESSAGE ===");
    System.out.println(longest.messageText);
}
public static void searchMessageID(Scanner input) {

    if (storedMessages.isEmpty()) {

        System.out.println("No stored messages available.");
        return;
    }

    input.nextLine();

    System.out.print("Enter Message ID: ");
    String id = input.nextLine();

    boolean found = false;

    for (Message msg : storedMessages) {

        if (msg.messageID.equals(id)) {

            System.out.println("\nRecipient: " + msg.recipient);
            System.out.println("Message: " + msg.messageText);

            found = true;
            break;
        }
    }

    if (!found) {

        System.out.println("Message ID not found.");
    }
}
public static void searchRecipient(Scanner input) {

    if (storedMessages.isEmpty()) {

        System.out.println("No stored messages available.");
        return;
    }

    input.nextLine();

    System.out.print("Enter Recipient Number: ");
    String recipientNumber = input.nextLine();

    boolean found = false;

    for (Message msg : storedMessages) {

        if (msg.recipient.equals(recipientNumber)) {

            System.out.println(msg.messageText);
            found = true;
        }
    }

    if (!found) {

        System.out.println("Recipient not found.");
    }
}
public static void deleteMessage(Scanner input) {

    if (storedMessages.isEmpty()) {

        System.out.println("No stored messages available.");
        return;
    }

    input.nextLine();

    System.out.print("Enter Message Hash: ");
    String hash = input.nextLine();

    boolean deleted = false;

    for (int i = 0; i < storedMessages.size(); i++) {

        if (storedMessages.get(i).messageHash.equals(hash)) {

            storedMessages.remove(i);

            System.out.println("Message successfully deleted.");
            deleted = true;
            break;
        }
    }

    if (!deleted) {

        System.out.println("Message hash not found.");
    }
}
public static void loadStoredMessages() {

    try {

        File file = new File("messages.json");
        Scanner fileReader = new Scanner(file);

        storedMessages.clear();

        Message currentMessage = null;

        while (fileReader.hasNextLine()) {

            String line = fileReader.nextLine().trim();

            if (line.startsWith("{")) {

                currentMessage = new Message();

            } else if (line.startsWith("\"MessageID\"")) {

                currentMessage.messageID =
                        line.split(":")[1].replace("\"", "").replace(",", "").trim();

            } else if (line.startsWith("\"MessageHash\"")) {

                currentMessage.messageHash =
                        line.split(":")[1].replace("\"", "").replace(",", "").trim();

            } else if (line.startsWith("\"Recipient\"")) {

                currentMessage.recipient =
                        line.split(":")[1].replace("\"", "").replace(",", "").trim();

            } else if (line.startsWith("\"Message\"")) {

                currentMessage.messageText =
                        line.substring(line.indexOf(":") + 1)
                            .replace("\"", "")
                            .replace(",", "")
                            .trim();

            } else if (line.startsWith("}")) {

                if (currentMessage != null) {

                    storedMessages.add(currentMessage);
                }
            }
        }

        fileReader.close();

        System.out.println("Stored messages loaded successfully.");

    } catch (Exception e) {

        System.out.println("No stored messages found.");
    }
}
}
