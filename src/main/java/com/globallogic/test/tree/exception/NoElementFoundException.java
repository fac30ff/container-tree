package com.globallogic.test.tree.exception;

public class NoElementFoundException extends Exception {
    public NoElementFoundException() {
        System.out.println("No such element found in container");
    }

    @Override
    public String getMessage() {
        return "No such element found in container";
    }
}
