package com.nashss.se.taskmaster.utils;

import java.util.regex.Pattern;



public final class EmailUtil {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern INVALID_CHARACTER = Pattern.compile("[\"'\\\\]");
    private static final Pattern EMAIL_MUST_CONTAIN = Pattern.compile(EMAIL_REGEX);
    
    private EmailUtil() {

    }

    /**
     * Checks the given email against a pattern of appropriate characters
     * @param email the email to check validity of
     * @return True, if the email contains any of the valid pattern. False, if it contains invalid characters or is null/empty.
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        } else {
            return !INVALID_CHARACTER.matcher(email).find() && EMAIL_MUST_CONTAIN.matcher(email).find();
        }
    }
}
