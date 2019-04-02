package com.globallogic.test.tree.management.search;

import com.globallogic.test.tree.exception.NoElementFoundException;

import java.util.List;
import java.util.function.Predicate;

public interface SearchEngineInterface<T> {
    List<T> subElements(T t);
    List<T> filter(Predicate<? super T> t) throws NoElementFoundException;
    T search(T t) throws NoElementFoundException;
}
