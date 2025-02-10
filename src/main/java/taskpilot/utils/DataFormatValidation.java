package taskpilot.utils;

public class DataFormatValidation{

    public static boolean validation(String email, String username, String password) {

        boolean isEmailValid = email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        boolean isUPvalid = (username.length() >= 4 && password.length() >= 8);

        return (isEmailValid && isUPvalid);
    }
}
