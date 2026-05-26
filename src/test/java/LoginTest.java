import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {

    @Test
    public void testUserNameCorrectlyFormatted() {

        Login user = new Login();

        user.username = "kyl_1";

        assertTrue(user.checkUserName());
    }

    @Test
    public void testUserNameIncorrectlyFormatted() {

        Login user = new Login();

        user.username = "kyle!!!!";

        assertFalse(user.checkUserName());
    }

    @Test
    public void testPasswordMeetsComplexity() {

        Login user = new Login();

        user.password = "Ch&Usec@ke99!";

        assertTrue(user.checkPasswordComplexity());
    }

    @Test
    public void testPasswordDoesNotMeetComplexity() {

        Login user = new Login();

        user.password = "password";

        assertFalse(user.checkPasswordComplexity());
    }

    @Test
    public void testCellPhoneNumberCorrectlyFormatted() {

        Login user = new Login();

        user.cellphone = "+27838968976";

        assertTrue(user.checkCellPhoneNumber());
    }

    @Test
    public void testCellPhoneNumberIncorrectlyFormatted() {

        Login user = new Login();

        user.cellphone = "0838968976";

        assertFalse(user.checkCellPhoneNumber());
    }
}