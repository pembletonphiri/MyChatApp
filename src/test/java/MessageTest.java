import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {

        Message msg = new Message();

        msg.messageText = "Hi Mike, can you join us for dinner tonight?";

        assertTrue(msg.checkMessageLength());

    }

    @Test
    public void testMessageLengthFailure() {

        Message msg = new Message();

        msg.messageText = "This is a very long message that should fail because it is intentionally made longer than 250 characters. "
                + "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore "
                + "et dolore magna aliqua. Ut enim ad minim veniam.";

        boolean result = msg.checkMessageLength();

        assertFalse(result);
    }

    @Test
    public void testRecipientCorrectlyFormatted() {

        Message msg = new Message();

        msg.recipient = "+27838968976";

        assertTrue(msg.checkRecipientCell());
    }

    @Test
    public void testRecipientIncorrectlyFormatted() {

        Message msg = new Message();

        msg.recipient = "0838968976";

        assertFalse(msg.checkRecipientCell());
    }

    @Test
    public void testMessageHashCreated() {

        Message msg = new Message();

        msg.messageID = "001234567";
        msg.messageNumber = 0;
        msg.messageText = "Hi Tonight";

        assertEquals(
                "00:0:HITONIGHT",
                msg.createMessageHash());
    }

    @Test
    public void testSentMessageOption() {

        Message msg = new Message();

        assertEquals(
                "Message successfully sent.",
                msg.sentMessage(1));
    }

    @Test
    public void testDiscardMessageOption() {

        Message msg = new Message();

        assertEquals(
                "Press 0 to delete the message.",
                msg.sentMessage(2));
    }

    @Test
    public void testStoreMessageOption() {

        Message msg = new Message();

        assertEquals(
                "Message successfully stored.",
                msg.sentMessage(3));
    }

    @Test
    public void testSentMessagesArrayPopulated() {

        Message.sentMessages.clear();

        Message msg1 = new Message();
        msg1.messageText = "Did you get the cake?";

        Message msg2 = new Message();
        msg2.messageText = "It is dinner time!";

        Message.sentMessages.add(msg1);
        Message.sentMessages.add(msg2);

        assertEquals(2, Message.sentMessages.size());
        assertEquals("Did you get the cake?", Message.sentMessages.get(0).messageText);
        assertEquals("It is dinner time!", Message.sentMessages.get(1).messageText);
    }

    @Test
    public void testLongestMessage() {

        Message.storedMessages.clear();

        Message msg1 = new Message();
        msg1.messageText = "Hello";

        Message msg2 = new Message();
        msg2.messageText = "Where are you? You are late! I have asked you to be on time.";

        Message.storedMessages.add(msg1);
        Message.storedMessages.add(msg2);

        Message longest = Message.storedMessages.get(0);

        for (Message msg : Message.storedMessages) {

            if (msg.messageText.length() > longest.messageText.length()) {
                longest = msg;
            }
        }

        assertEquals(
                "Where are you? You are late! I have asked you to be on time.",
                longest.messageText);
    }

    @Test
    public void testSearchMessageID() {

        Message.storedMessages.clear();

        Message msg = new Message();
        msg.messageID = "123456789";
        msg.recipient = "+27831234567";
        msg.messageText = "Hello";

        Message.storedMessages.add(msg);

        boolean found = false;

        for (Message message : Message.storedMessages) {

            if (message.messageID.equals("123456789")) {
                found = true;
                break;
            }
        }

        assertTrue(found);
    }

    @Test
    public void testSearchRecipient() {

        Message.storedMessages.clear();

        Message msg = new Message();
        msg.recipient = "+27831234567";
        msg.messageText = "Hello";

        Message.storedMessages.add(msg);

        boolean found = false;

        for (Message message : Message.storedMessages) {

            if (message.recipient.equals("+27831234567")) {
                found = true;
            }
        }

        assertTrue(found);
    }

    @Test
    public void testDeleteMessageByHash() {

        Message.storedMessages.clear();

        Message msg = new Message();
        msg.messageHash = "00:0:HELLOWORLD";

        Message.storedMessages.add(msg);

        Message.storedMessages.remove(0);

        assertEquals(0, Message.storedMessages.size());
    }

    @Test
    public void testDisplayReportArray() {

        Message.sentMessages.clear();

        Message msg = new Message();

        msg.messageHash = "00:0:HELLOWORLD";
        msg.recipient = "+27831234567";
        msg.messageText = "Hello";

        Message.sentMessages.add(msg);

        assertEquals(1, Message.sentMessages.size());
        assertEquals("Hello", Message.sentMessages.get(0).messageText);
    }
}