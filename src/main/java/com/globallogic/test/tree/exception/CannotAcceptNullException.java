package com.globallogic.test.tree.exception;

public class CannotAcceptNullException extends Exception {
    public CannotAcceptNullException() {
        System.out.println("You are trying pass and invalid (\"null\") value");
    }

    @Override
    public String getMessage() {
        return "You are trying pass and invalid (\"null\") value";
    }
}
