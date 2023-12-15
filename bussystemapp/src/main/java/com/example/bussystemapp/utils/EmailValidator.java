package com.example.bussystemapp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    public static final Pattern  VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String email){

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

}
