package com.globallogic.test.tree.exception;

public class ThisNodeAlreadyAncestorOfCurrentNodeException extends Exception {
    public ThisNodeAlreadyAncestorOfCurrentNodeException() {
        System.out.println("Alert!: Your action may cause cyclic dependencies");
    }

    @Override
    public String getMessage() {
        return "You are trying to add this node as child but this node is already ancestor of this node";
    }
}
