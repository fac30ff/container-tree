package com.globallogic.test.tree;

import java.util.ArrayList;
import java.util.List;

public class Main {
    List<NumberList> list = new ArrayList<>();

    public static void main(String[] args) {
        GeneralTree<Integer> tree = new GeneralTree<>();
        GeneralNode<Integer> root1 = tree.phantomPoint().add(11);

    }

    int size() {
       return 0;
    }

    private class NumberList {
        private int i;
        List<NumberList> list = new ArrayList<>();
    }
}
