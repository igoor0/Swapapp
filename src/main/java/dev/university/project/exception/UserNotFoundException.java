package dev.university.project.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super("User not found with email: " + message);
    }

}
