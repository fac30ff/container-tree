package com.globallogic.test.tree.management.search;

import java.util.List;
import java.util.function.Predicate;

public interface SearchEngineInterface<T> {
    T subTree(T t);
    List<T> filter(Predicate<T> t);
    T search(T t);
}
