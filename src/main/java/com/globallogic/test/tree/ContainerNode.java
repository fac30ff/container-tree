package com.globallogic.test.tree;

import com.globallogic.test.tree.exception.CannotAcceptNullException;
import com.globallogic.test.tree.exception.ThisIsNoChildException;
import com.globallogic.test.tree.exception.ThisNodeAlreadyAncestorOfCurrentNodeException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public final class ContainerNode<T> implements GeneralNode<T> {
    private List<GeneralNode<T>> children;
    private int maxChildren = Integer.MAX_VALUE;
    private GeneralNode<T> parent;
    private int size;
    private T value;

    public ContainerNode() {
        this(null);
    }

    public ContainerNode(T value) {
        this.parent = null;
        this.value = value;
    }

    public ContainerNode(ContainerNode<T> parent, T value) {
        this.parent = parent;
        this.value = value;
    }

    public ContainerNode(int maxChildren, T value) {
        super();
        this.maxChildren = maxChildren;
        this.parent = null;
        this.value = value;
    }

    public ContainerNode(int maxChildren, ContainerNode<T> parent, T value) {
        this.maxChildren = maxChildren;
        this.parent = parent;
        this.value = value;
    }

    public int getMaxChildren() {
        return maxChildren;
    }

    public void setMaxChildren(int maxChildren) {
        this.maxChildren = maxChildren;
    }

    @Override
    public Iterator<? extends GeneralNode<T>> children() {
        return children.iterator();
    }

    @Override
    public GeneralNode<T> getChild(int childIndex) {
        return children.get(childIndex);
    }

    @Override
    public int getChildrenCount() {
        return children.size();
    }

    @Override
    public int getIndex(GeneralNode<T> node) {
        Objects.requireNonNull(node, new CannotAcceptNullException().getMessage());
        if (node.getParent() != this) {
            return -1;
        }
        return children.indexOf(node);
    }

    @Override
    public GeneralNode<T> getParent() {
        return parent;
    }

    @Override
    public void add(GeneralNode<T> node) throws ThisNodeAlreadyAncestorOfCurrentNodeException {
        if (isAncestor(node) && isDescendant(node)) {
            throw new ThisNodeAlreadyAncestorOfCurrentNodeException();
        }
        if (children == null) {
            children = new ArrayList<>();
        }
        if (allowAddChildren()) {
            children.add(node);
            size = getChildrenCount();
        }
    }

    @Override
    public GeneralNode<T> add(T value) {
        return this;
    }

    @Override
    public void insert(GeneralNode<T> child, int index) throws ThisIsNoChildException {
        Objects.requireNonNull(child, new CannotAcceptNullException().getMessage());
        GeneralNode<T> old = child.getParent();
        if (old != null) {
            old.remove(child);
        }
        child.setParent(this);
        if (children == null) {
            children = new ArrayList<>();
        }
        if (allowAddChildren()) {
            children.add(index, child);
            size = getChildrenCount();
        }
    }

    @Override
    public void remove(int index) {
        ContainerNode<T> child = (ContainerNode<T>) getChild(index);
        children.remove(index);
        child.setParent(null);
    }

    @Override
    public void remove(GeneralNode<T> node) throws ThisIsNoChildException {
        Objects.requireNonNull(node, new CannotAcceptNullException().getMessage());
        if (children.contains(node)) {
            children.remove(node);
        } else {
            throw new ThisIsNoChildException();
        }
    }

    @Override
    public void removeFromParent() throws ThisIsNoChildException {
        this.getParent().remove(this);
        this.parent = null;
        this.children.clear();
    }

    @Override
    public void setParent(GeneralNode<T> newParent) {
        Objects.requireNonNull(newParent, new CannotAcceptNullException().getMessage());
        this.parent = newParent;

    }

    @Override
    public void setValue(T t) {
        this.value = t;
    }

    private boolean isLeaf() {
        return getChildrenCount() == 0;
    }

    private boolean isRoot() {
        return getParent() == null;
    }

    @Override
    public boolean isAncestor(GeneralNode<T> node) {
        if (node == null) {
            return false;
        }
        for (GeneralNode<T> ancestor = this; ancestor.getParent() != null; ancestor = ancestor.getParent()) {
            if (ancestor == node) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isDescendant(GeneralNode<T> node) {
        if (node == null) {
            return false;
        }
        return node.isAncestor(this);
    }

    private boolean allowAddChildren() {
        return maxChildren < children.size();
    }

    @Override
    public String toString() {
        return Objects.toString(value);
    }
}

