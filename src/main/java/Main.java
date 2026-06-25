import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

       Scanner input = new Scanner(System.in);
       
       // Load stored messages from the JSON file
              Message.loadStoredMessages();
              Login user = new Login();

        System.out.println("=== QUICKCHAT REGISTRATION ===");

        // Username details
        System.out.print("Enter username: ");
        user.username = input.nextLine();

        if (user.checkUserName()) {

            System.out.println("Username successfully captured.");

        } else {

            System.out.println(
                    "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
        }

        // PASSWORD
        System.out.print("Enter password: ");
        user.password = input.nextLine();

        if (user.checkPasswordComplexity()) {

            System.out.println("Password successfully captured.");

        } else {

            System.out.println(
                    "Password is not correctly formatted; please make sure that the password contains at least eight characters, a capital letter, a number, and a special character.");
        }

        // CELLPHONE
        System.out.print("Enter cellphone number: ");
        user.cellphone = input.nextLine();

        if (user.checkCellPhoneNumber()) {

            System.out.println("Cell phone number successfully added.");

        } else {

            System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
        }

        // REGISTRATION RESULT
        System.out.println("\n" + user.registerUser());

        // LOGIN SECTION
        System.out.println("\n=== LOGIN ===");

        System.out.print("Enter username: ");
        user.enteredUsername = input.nextLine();

        System.out.print("Enter password: ");
        user.enteredPassword = input.nextLine();

        // LOGIN RESULTS
        System.out.println(user.returnLoginStatus());

        // SHOW MENU ONLY IF LOGIN IS SUCCESSFUL
if (user.loginUser()) {

    boolean running = true;

    while (running) {

        System.out.println("\n=== QUICKCHAT MENU ===");
        System.out.println("1. Send Messages");
        System.out.println("2. Show Report");
        System.out.println("3. Stored Messages");
        System.out.println("4. Quit");

        System.out.print("Choose an option: ");
        int option = input.nextInt();

        switch (option) {

            case 1:

                Message msg = new Message();

                input.nextLine();

                System.out.print("Enter recipient's number: ");
                msg.recipient = input.nextLine();

                if (!msg.checkRecipientCell()) {

                    System.out.println("Cell number incorrectly formatted.");
                    break;
                }

                System.out.println("Cell number captured successfully.");

                System.out.print("Enter your message: ");
                msg.messageText = input.nextLine();

                if (!msg.checkMessageLength()) {

                    int extraCharacters = msg.messageText.length() - 250;

                    System.out.println("Message exceeds 250 characters by "
                            + extraCharacters + " characters.");

                    break;
                }

                System.out.println("Message captured successfully.");

                msg.generateMessageID();

                System.out.println("Message ID: " + msg.messageID);

                msg.messageNumber = Message.totalMessages;

                System.out.println("Message Hash: " + msg.createMessageHash());

                System.out.println("\nChoose what to do with the message:");
                System.out.println("1. Send Message");
                System.out.println("2. Discard Message");
                System.out.println("3. Store Message");

                System.out.print("Enter option: ");

                int messageChoice = input.nextInt();

                System.out.println(msg.sentMessage(messageChoice));
                // Send Message
if (messageChoice == 1) {

    Message.sentMessages.add(msg);
    Message.messageHashes.add(msg.messageHash);
    Message.messageIDs.add(msg.messageID);

    System.out.println("\n=== MESSAGE DETAILS ===");
    System.out.println(msg.printMessages());
    System.out.println("\nTotal messages sent: " + msg.returnTotalMessages());
}

// Discard Message
else if (messageChoice == 2) {

    Message.disregardedMessages.add(msg);

    System.out.println("Message discarded.");
}

// Store Message
else if (messageChoice == 3) {

    Message.storedMessages.add(msg);
    Message.messageHashes.add(msg.messageHash);
    Message.messageIDs.add(msg.messageID);

    msg.storeMessageToJSON();

    System.out.println("Message stored successfully.");
}
else {

    System.out.println("Invalid option.");
}

break;

case 2:

    Message.displayReport();

    break;

case 3:

System.out.println("\n=== STORED MESSAGES ===");
System.out.println("1. Display Sender and Recipient");
System.out.println("2. Display Longest Message");
System.out.println("3. Search by Message ID");
System.out.println("4. Search by Recipient");
System.out.println("5. Delete Message by Hash");
System.out.println("6. Back");
    

    System.out.print("Choose an option: ");
    int storedChoice = input.nextInt();

    switch (storedChoice) {

        case 1:
            Message.displaySenderRecipient();
            break;

        case 2:
            Message.displayLongestMessage();
            break;

        case 3:
            Message.searchMessageID(input);
            break;

        case 4:
            Message.searchRecipient(input);
            break;
            
        case 5:
            Message.deleteMessage(input);
            break;

        case 6:
            break;

        default:
            System.out.println("Invalid option.");
    }

    break;

case 4:

    running = false;
    System.out.println("Goodbye!");

    break;

default:

    System.out.println("Invalid option.");
}
}
}
}
}