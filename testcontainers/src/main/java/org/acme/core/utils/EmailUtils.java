package org.acme.core.utils;

public class EmailUtils {

    private static final String EMAIL_REGEX = "^(.+)@(\\S+)$";

    private EmailUtils() {
    }

    public static boolean isValidEmail(String email){
        if (email == null)
            return false;
        return email.matches(EMAIL_REGEX);
    }
}
