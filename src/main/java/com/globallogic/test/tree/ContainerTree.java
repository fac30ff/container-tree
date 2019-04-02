package com.globallogic.test.tree;

import com.globallogic.test.tree.exception.CannotAcceptNullException;
import com.globallogic.test.tree.management.event.ObservableContainer;
import com.globallogic.test.tree.management.search.SearchEngineInterface;
import com.globallogic.test.tree.management.event.constants.TreeEvent;
import com.globallogic.test.tree.exception.AddingChildIsProhibitedForThisNodeException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.function.Predicate;

public class ContainerTree<T> extends ObservableContainer<T> {
  private Container<T> root;

  public ContainerTree() {
    root = new Container<>();
  }

  public ContainerTree(Container<T> t) {
    this.root = t;
  }

  public boolean insert(T parent, T element) throws AddingChildIsProhibitedForThisNodeException {
    if (parent == null || element == null) {
      propertyChanged(null, TreeEvent.FailedToInsertContainer, null);
      return false;
    }
    if (root == null) {
      propertyChanged(element, TreeEvent.NewRootContainerAdded, element);
      return add(element);
    }
    Container<T> p = traverseToMainRoot(root, parent);
    if (p != null) {
      propertyChanged(parent, TreeEvent.NewChildContainerAdded, element);
      p.addChild(element);
      return true;
    }
    return false;
  }

  public boolean isEmpty() {
    return root == null;
  }

  public int size() {
    int count = 0;
    if (root == null) {
      return 0;
    }
    count = traverse(root, count);
    return count;
  }

  private int traverse(Container<T> current, int count) {
    for (Container<T> c : current.children) {
      count++;
      traverse(c, count);
    }
    return count;
  }

  private int size(Container<T> current) {
    return current == null ? 0 : current.children.size();
  }

  public boolean contains(T element) {
    return contains(root, element);
  }

  private boolean contains(Container<T> current, T element) {
    if (current == null) {
      return false;
    }
    return traverseToMainRoot(current, element) != null;
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

  public T remove(T element) {
    if (root == null) {
      return null;
    }
    if (root.object.equals(element)) {
      root.removeSelf();
    } else {
      if (root.parent == null) {
        root = traverseLevelOrder(root, element);
        root.removeSelf();
      } else {
        root = traverseToMainRoot(root, element);
        root.removeSelf();
      }
    }
    return element;
  }

  private Container<T> traverseToMainRoot(Container<T> current, T element) {
    while (current.hasParent()) {
      current = current.getParent();
    }
    current = traverseLevelOrder(current, element);
    return current;
  }

  private Container<T> traverseLevelOrder(Container<T> current, T element) {
    Queue<Container<T>> containers = new LinkedList<>();
    containers.add(root);
    while (!containers.isEmpty()) {
      Container<T> container = containers.remove();
      if (container.object.equals(element)) {
        return container;
      }
      if (container.children != null) {
        containers.addAll(container.children);
      }
    }
    return null;
  }

  public ContainerTree<T> subTree(T element) {
    Objects.requireNonNull(element, new CannotAcceptNullException().getMessage());
    if(root == null) {
      return null;
    }
    if (root.getObject().equals(element)) {
      return this;
    }
    Container<T> p = traverseToMainRoot(root, element);
    if (p == null) {
      return null;
    }
    p.changeParent(null);
    return new ContainerTree<>(p);
  }

  private List<Container<T>> allChildren(T element) {
    return Collections.emptyList();
  }

  private List<Container<T>> allChildren(Container<T> current, List<Container<T>> list) {
    return Collections.emptyList();
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

    void changeParent(Container<T> newParent) {
      if (this.getParent() == null) {
        this.setParent(newParent);
      }
      Container<T> p = this.getParent();
      p.removeChild(this);
      this.setParent(newParent);
    }

    private void removeChild(Container<T> child) {
      children.remove(child);
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
      children.add(new Container<>(this, element));
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
      return maxChildren <= children.size();
    }

    Container<T> removeSelf() {
      Container<T> temp = this;
      this.parent = null;
      this.object = null;
      removeChildren(this.children);
      return temp;
    }

    private void removeChildren(List<Container<T>> children) {
      if (children != null && children.isEmpty()) {
        children.forEach(e -> removeChildren(Collections.singletonList(e.removeSelf())));
      }
    }

    boolean hasParent() {
      return parent != null;
    }

    private boolean isLeaf() {
      return getChildren() == null || getChildren().isEmpty();
    }

    private boolean isRoot() {
      return getParent() == null;
    }
  }

  private class ContainerSearchEngine implements SearchEngineInterface<T> {

    @Override
    public List<T> subElements(T t) {
      Container<T> p = traverseToMainRoot(t);
      LinkedList
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
}
