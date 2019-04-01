package com.globallogic.test.tree;

import java.util.ArrayList;
import java.util.List;

public class ObservableContainer<T> {
  private List<TreeObserver<T>> observers = new ArrayList<>();
  public void subscribe(TreeObserver<T> observer) {
    observers.add(observer);
  }
  public void propertyChanged(T source, TreeEvent event, Object newValue) {
    for (TreeObserver<T> co : observers) {
      co.handle(new PropertyChangedEvent<>(source, event, newValue));
    }
  }
}
