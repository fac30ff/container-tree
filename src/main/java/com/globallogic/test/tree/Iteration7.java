package com.globallogic.test.tree;

import java.util.ArrayList;
import java.util.List;

public class Iteration7<T> {
  private Container<T> current;

  public boolean add(T element) {
    if (current == null) {
      current = new Container<>(element);
    } else {
      if (current.getChildren().contains(element)) {
        return false;
      }
    }
    return true;
  }

  private static class Container<T> {
    private Container<T> parent;
    private List<Container<T>> children = new ArrayList<>();
    private T object;
    private int maxChildren = Integer.MAX_VALUE;
    private int size;

    public Container() {
      this(null);
    }

    public Container(T object) {
      this.object = object;
      this.parent = null;
    }

    public Container(Container<T> parent, int maxChildren) {
      this.object = null;
      this.parent = parent;
      this.maxChildren = maxChildren;
    }

    public Container(T object, int maxChildren) {
      this(object);
      this.maxChildren = maxChildren;
    }

    public Container(Container<T> parent, T object, int maxChildren) {
      this.object = object;
      this.parent = parent;
      this.maxChildren = maxChildren;
    }

    public Container<T> getParent() {
      return parent;
    }

    public void setParent(Container<T> parent) {
      this.parent = parent;
    }

    public List<Container<T>> getChildren() {
      return children;
    }

    public void setChildren(List<Container<T>> children) {
      this.children = children;
    }

    public T getObject() {
      return object;
    }

    public void setObject(T object) {
      this.object = object;
    }

    public int getMaxChildren() {
      return maxChildren;
    }

    public void setMaxChildren(int maxChildren) {
      this.maxChildren = maxChildren;
    }

    public int getSize() {
      return size;
    }

    private void setSize(int size) {
      this.size = size;
    }
  }
}
