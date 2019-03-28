package com.globallogic.test.tree;

public final class GeneralTree<T> {
    private final GeneralNode<T> zeroNode = new ContainerNode<>(null);

    public GeneralNode<T> phantomPoint() {
        return zeroNode;
    }

    public GeneralNode<T> subtree(T t) {
        return new GeneralTree<T>().phantomPoint();
    }


    @Override
    public String toString() {
        return "";
    }
}
