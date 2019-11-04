package com.studios.androidservice.utils;

import android.text.Editable;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class TextUtils {

    public static boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    public static boolean isValidPhone(String s) {
        if (s.isEmpty()) {
            return false;
        } else if (s.length() < 10) {
            return false;
        } else {
            switch (s.substring(0, 3)) {
                case "985":
                case "984":
                case "986":
                case "980":
                case "981":
                case "982":
                case "972":
                case "961":
                case "962":
                case "988":
                case "974":
                case "976":
                    return true;
                default:
                    return false;
            }
        }
    }

}
