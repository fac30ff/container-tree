package com.globallogic.test.tree.exception;

public class AddingChildIsProhibitedForThisNodeException extends Exception {
    public AddingChildIsProhibitedForThisNodeException() {
        System.out.println("Adding child not possible due to rich limit for this node");
    }

    @Override
    public String getMessage() {
        return "Adding child not possible due to rich limit for this node";
    }
}
