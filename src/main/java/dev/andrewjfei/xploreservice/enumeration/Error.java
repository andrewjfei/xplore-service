package dev.andrewjfei.xploreservice.enumeration;

public enum Error {
    UNEXPECTED_ERROR(0, "An unexpected error has occurred."),
    USERNAME_NOT_FOUND(1, "The supplied username does not exist."),
    INCORRECT_PASSWORD(2, "The password provided is incorrect."),
    UNSUCCESSFUL_AUTHENTICATION(3, "Authentication failed due to incorrect credentials.");

    public final int code;
    public final String message;

    Error(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
