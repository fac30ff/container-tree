package com.globallogic.test.tree.management.event;

import com.globallogic.test.tree.management.event.constants.TreeEvent;

import java.util.ArrayList;
import java.util.List;

public class ObservableContainer<T> {
  private List<TreeObserver<T>> observers = new ArrayList<>();

  public void subscribe(TreeObserver<T> observer) {
    observers.add(observer);
  }

  public void propertyChanged(T source, TreeEvent event, Object newValue) {
    for (TreeObserver<T> to : observers) {
      to.handle(new PropertyChangedEvent<>(source, event, newValue));
    }
  }
}