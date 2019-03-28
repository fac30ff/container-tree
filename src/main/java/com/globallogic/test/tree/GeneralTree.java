package com.globallogic.test.tree;

import java.util.List;
import java.util.function.Predicate;

public final class GeneralTree<T> implements SearchEngineInterface<T>{
    private final GeneralNode<T> zeroNode = new ContainerNode<>(null);

    public GeneralNode<T> phantomPoint() {
        return zeroNode;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public T subTree(T t) {
        return null;
    }

    @Override
    public List<T> filter(Predicate<T> t) {
        return null;
    }

    @Override
    public T search(T t) {
        return null;
    }
}
