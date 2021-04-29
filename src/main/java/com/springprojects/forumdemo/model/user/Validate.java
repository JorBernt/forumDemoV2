package com.springprojects.forumdemo.model.user;

public class Validate {

    public static boolean validateUser(User user) {
        boolean userOK = validateUserName(user.getUsername());
        boolean emailOK = validateEmail(user.getEmail());
        boolean passOK = validatePassword(user.getPassword());
        return userOK && emailOK && passOK;
    }

    public static boolean validateUserName(String input) {
        String regex = "(?!.*\\.\\.)(?!.*\\.$)[^\\W][\\w.]{0,29}";
        return input.matches(regex);
    }


    public static boolean validateEmail(String input) {
        return input.contains("@");
    }

    public static boolean validatePassword(String input) {
        String regex = "(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}";
        return input.matches(regex);
    }

    public static boolean validateLogin(LoginCredentials credentials) {
        return true;
    }
}
