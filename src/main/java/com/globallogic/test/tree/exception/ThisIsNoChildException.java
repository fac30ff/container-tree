package com.globallogic.test.tree.exception;

public class ThisIsNoChildException extends Exception {
    public ThisIsNoChildException() {
        System.out.println("This node is not a child");
    }

    @Override
    public String getMessage() {
        return "This node is not a child";
    }
}
