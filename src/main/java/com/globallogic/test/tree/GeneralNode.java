package com.globallogic.test.tree;

import com.globallogic.test.tree.exception.ThisIsNoChildException;

import java.util.Iterator;

public interface GeneralNode<T> {
    Iterator<? extends GeneralNode<T>> children();
    GeneralNode<T> getChild(int childIndex);
    int getChildrenCount();
    int getIndex(GeneralNode<T> node);
    GeneralNode<T> getParent();
    void insert(GeneralNode<T> child, int index) throws ThisIsNoChildException;
    boolean isAncestor(GeneralNode<T> node);
    public boolean isDescendant(GeneralNode<T> node);
    void remove(int index);
    void remove(GeneralNode<T> node) throws ThisIsNoChildException;
    void removeFromParent() throws ThisIsNoChildException;
    void setParent(GeneralNode<T> newParent);
    void setValue(T t);
}
