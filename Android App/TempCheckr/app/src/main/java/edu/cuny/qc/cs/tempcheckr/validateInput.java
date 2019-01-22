package edu.cuny.qc.cs.tempcheckr;

import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import java.util.regex.Pattern;

public class validateInput {

    // Regular Expression
    // you can change the expression based on your need
    private static final String ZIPCODE_REGEX = "^[0-9]{5}(?:-[0-9]{4})?$";
    private static final String CITY_REGEX = "^([a-zA-Z\\u0080-\\u024F]+(?:. |-| |'))*[a-zA-Z\\u0080-\\u024F]*$";

    // Error Messages
    private static final String REQUIRED_MSG = "Please enter a location to proceed";
    private static final String INVALID_MSG = "Invalid Format";

    //private static final String PHONE_MSG = "###-#######";

    // call this method when you need to check email validation
    public static boolean isZipCode(AutoCompleteTextView editText, boolean required) {
        return isValid(editText, ZIPCODE_REGEX, INVALID_MSG, required);
    }

    // call this method when you need to check phone number validation
    public static boolean isCity(AutoCompleteTextView editText, boolean required) {
        return isValid(editText, CITY_REGEX, INVALID_MSG, required);
    }

    // return true if the input field is valid, based on the parameter passed
    public static boolean isValid(AutoCompleteTextView editText, String regex, String errMsg, boolean required) {

        String text = editText.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        editText.setError(null);

        // text required and editText is blank, so return false
        if ( required && !hasText(editText) ) return false;

        // pattern doesn't match so returning false
        if (required && !Pattern.matches(regex, text)) {
            editText.setError(errMsg);
            return false;
        };

        return true;
    }

    // check the input field has any text or not
    // return true if it contains text otherwise false
    public static boolean hasText(AutoCompleteTextView editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
            editText.setError(REQUIRED_MSG);
            return false;
        }

        return true;
    }
}