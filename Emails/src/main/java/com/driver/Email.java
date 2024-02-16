package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }


    public void changePassword(String oldPassword, String newPassword) {
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character

        boolean characters = false;
        boolean upperCase = false;
        boolean lowerCase = false;
        boolean digit = false;
        boolean specialChar = false;
        if (this.password.equals(oldPassword)) {
            if (newPassword.length() >= 8) characters = true;

            for (int i = 0; i < newPassword.length(); i++) {

                char ch = newPassword.charAt(i);

                if (ch >= 'a' && ch <= 'z') {
                    lowerCase = true;
                } else if (ch >= 'A' && ch <= 'Z') {
                    upperCase = true;
                } else if (Character.getNumericValue(ch) >= 0 && Character.getNumericValue(ch) <= 9) {
                    digit = true;
                } else {
                    specialChar = true;
                }
            }
            if (characters && upperCase && lowerCase && digit && specialChar) {
                this.password = newPassword;
            }
        }
    }
    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
