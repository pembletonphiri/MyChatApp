import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {

        Message msg = new Message();

        msg.messageText = "Hi Mike, can you join us for dinner tonight?";

        assertEquals(
                "Message ready to send.",
                msg.checkMessageLength());
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

        assertEquals(
                "Cell number successfully captured.",
                msg.checkRecipientCell());
    }

    @Test
    public void testRecipientIncorrectlyFormatted() {

        Message msg = new Message();

        msg.recipient = "0838968976";

        assertEquals(
                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                msg.checkRecipientCell());
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
}