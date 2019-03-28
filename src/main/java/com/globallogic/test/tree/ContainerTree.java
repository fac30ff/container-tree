package com.globallogic.test.tree;

import java.util.ArrayList;
import java.util.List;

public class ContainerTree<T> implements GeneralTree<T> {
    private Node root;
    private int size;

    private static class Node<T> {
        T value;
        private List<Node<T>> children;

        Node(T value) {
            this.value = value;
            children = new ArrayList<>();
        }
    }

    private Node addNode(Node object, T value) {

    }
}
