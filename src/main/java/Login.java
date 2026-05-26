public class Login {

    String username;
    String password;
    String cellphone;

    String enteredUsername;
    String enteredPassword;

    // Username validation
    public boolean checkUserName() {

        if (username.contains("_") && username.length() <= 5) {
            return true;
        } else {
            return false;
        }
    }

    // Password validation
    public boolean checkPasswordComplexity() {

        boolean hasCapitalLetter = false;
        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;

        // Check each character
        for (int i = 0; i < password.length(); i++) {

            char ch = password.charAt(i);

            if (Character.isUpperCase(ch)) {
                hasCapitalLetter = true;
            }

            if (Character.isDigit(ch)) {
                hasNumber = true;
            }

            if (!Character.isLetterOrDigit(ch)) {
                hasSpecialCharacter = true;
            }
        }

        // Final validation
        if (password.length() >= 8 &&
                hasCapitalLetter &&
                hasNumber &&
                hasSpecialCharacter) {

            return true;

        } else {

            return false;
        }
    }
        // Cellphone validation
    public boolean checkCellPhoneNumber() {

        // Must start with +27 and be 12 characters long
        if (cellphone.startsWith("+27") && cellphone.length() == 12) {

            return true;

        } else {

            return false;
        }
    }
        // Registration method
    public String registerUser() {

        if (checkUserName() &&
                checkPasswordComplexity() &&
                checkCellPhoneNumber()) {

            return "User has been registered successfully.";

        } else {

            return "Registration failed. Please check your details.";
        }
    }
        // Login verification
    public boolean loginUser() {

        if (enteredUsername.equals(username) &&
                enteredPassword.equals(password)) {

            return true;

        } else {

            return false;
        }
    }
        // Login status message
    public String returnLoginStatus() {

        if (loginUser()) {

            return "Welcome " + username + ", it is great to see you again.";

        } else {

            return "Username or password incorrect, please try again.";
        }
    }

}