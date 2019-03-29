package com.globallogic.test.tree;

import com.globallogic.test.tree.exception.AddingChildIsProhibitedForThisNodeException;
import com.globallogic.test.tree.exception.NoElementFoundException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Iteration7<T> {
    private Container<T> root;

    public Iteration7() {
        root = new Container<>();
    }

    public void insert(T element) {
        Container<T> current = root;

    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        int count = 0;
        if (root == null) {
            return 0;
        }
        count = count + size(root);
        return count;
    }

    private void traverse(Container<T> current) {
        current.children.forEach(this::traverse);
    }

    private int size(Container<T> current) {
        return current == null ? 0 : current.children.size();
    }

    public boolean contains(T element) {

    }

    private boolean contains(Container<T> current, T element) {
        if (current == null) {
            return false;
        }

        if (element.equals(current.object)) {
            return true;
        }


    }


    public boolean add(T element) throws AddingChildIsProhibitedForThisNodeException {
        root = add(root, element);
        return true;
    }

    private Container<T> add(Container<T> current, T element) throws AddingChildIsProhibitedForThisNodeException {
        if (current == null) {
            return new Container<>(element);
        }
        if (current.allowAddChildren()) {
            current.children.add(new Container<>(current, element));
        } else {
            throw new AddingChildIsProhibitedForThisNodeException();
        }
        return current;
    }

    public T remove(T element) throws NoElementFoundException {
        if(root == null) {
            throw new NoElementFoundException();
        }

        return element;
    }

    private Container<T> traverseLevelOrder(Container<T> current) throws NoElementFoundException {
        if (current == null) {
            throw new NoElementFoundException();
        }
        Queue<Container<T>> containers = new LinkedList<>();
        containers.add(root);
        while (!containers.isEmpty()) {
            Container<T> container = containers.remove();
            System.out.println(" " + container.object);
            if (container.children != null) {
                containers.addAll(container.children);
            }
        }
    }




    private static class Container<T> {
        private Container<T> parent;
        private List<Container<T>> children = new ArrayList<>();
        private T object;
        private int maxChildren = Integer.MAX_VALUE;
        private int size;

        Container() {
            this(null);
        }

        Container(T object) {
            this.object = object;
            this.parent = null;
        }

        Container(Container<T> parent, T object) {
            this.parent = parent;
            this.object = object;
        }

        Container(Container<T> parent, int maxChildren) {
            this.object = null;
            this.parent = parent;
            this.maxChildren = maxChildren;
        }

        Container(T object, int maxChildren) {
            this(object);
            this.maxChildren = maxChildren;
        }

        Container(Container<T> parent, T object, int maxChildren) {
            this.object = object;
            this.parent = parent;
            this.maxChildren = maxChildren;
        }

        Container<T> getParent() {
            return parent;
        }

        void setParent(Container<T> parent) {
            this.parent = parent;
        }

        List<Container<T>> getChildren() {
            return children;
        }

        void setChildren(List<Container<T>> children) {
            this.children = children;
        }

        T getObject() {
            return object;
        }

        void setObject(T object) {
            this.object = object;
        }

        int getMaxChildren() {
            return maxChildren;
        }

        void setMaxChildren(int maxChildren) {
            this.maxChildren = maxChildren;
        }

        int getSize() {
            return size;
        }

        private void setSize(int size) {
            this.size = size;
        }

        void addChild(T element) {

        }

        boolean isAncestor(Container<T> node) {
            if (node == null) {
                return false;
            }
            for (Container<T> ancestor = this; ancestor.getParent() != null; ancestor = ancestor.getParent()) {
                if (ancestor == node) {
                    return true;
                }
            }
            return false;
        }

        boolean isDescendant(Container<T> node) {
            if (node == null) {
                return false;
            }
            return node.isAncestor(this);
        }

        boolean allowAddChildren() {
            return maxChildren < children.size();
        }

        Container<T> removeSelf() {
            this.parent = null;
            return this;
        }
    }
}
