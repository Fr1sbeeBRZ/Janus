package fr1sbee.dev.janus.exceptions;

public class UserException extends Exception {

    public UserException(){
        super("A user-related error has occurred");
    }

    public UserException(final String message) {
        super(message);
    }

}
