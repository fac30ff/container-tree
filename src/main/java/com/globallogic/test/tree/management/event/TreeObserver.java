package com.globallogic.test.tree.management.event;

public interface TreeObserver<T> {
  void handle(PropertyChangedEvent<T> event);
}
