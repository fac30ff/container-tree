package com.globallogic.test.tree;

public interface TreeObserver<T> {
  void handle(PropertyChangedEvent<T> event);
}
