import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Login user = new Login();

        System.out.println("=== QUICKCHAT REGISTRATION ===");

        // USERNAME
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

            int option;

            System.out.println("\n=== QUICKCHAT MENU ===");

            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");

            System.out.print("Choose an option: ");
            option = input.nextInt();

            switch (option) {

                case 1:

                    Message msg = new Message();

                    input.nextLine();

                    System.out.print("Enter recipient number: ");
                    msg.recipient = input.nextLine();

                    if (msg.checkRecipientCell()) {

                        System.out.println("Cell number captured successfully.");

                    } else {

                        System.out.println("Cell number incorrectly formatted.");
                        break;
                    }

                    System.out.print("Enter your message: ");
                    msg.messageText = input.nextLine();

                    if (msg.checkMessageLength()) {

                        System.out.println("Message captured successfully.");

                    } else {

                        int extraCharacters = msg.messageText.length() - 250;

                        System.out.println("Message exceeds 250 characters by " + extraCharacters + " characters.");

                        break;
                    }

                    // Generate Message ID
                    msg.generateMessageID();

                    // Display Message ID
                    System.out.println("Message ID: " + msg.messageID);

                    // Set message number
                    msg.messageNumber = 0;

                    System.out.println("Message Hash: " + msg.createMessageHash());

                    // MESSAGE OPTIONS
                    System.out.println("\nChoose what to do with the message:");
                    System.out.println("1. Send Message");
                    System.out.println("2. Discard Message");
                    System.out.println("3. Store Message");

                    System.out.print("Enter option: ");
                    int messageChoice = input.nextInt();

                    System.out.println(msg.sentMessage(messageChoice));
                    // Store message in JSON file
                    if (messageChoice == 3) {

                        msg.storeMessageToJSON();
                    }

                    // Show message details only if sent
                    if (messageChoice == 1) {

                        System.out.println("\n=== MESSAGE DETAILS ===");

                        System.out.println(msg.printMessages());

                        System.out.println("\nTotal messages sent: " + msg.returnTotalMessages());
                    }

                case 2:
                    System.out.println("Coming Soon.");
                    break;

                case 3:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

    }
}