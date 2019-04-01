package com.globallogic.test.tree.eventmanagment;

public interface TreeObserver<T> {
  void handle(PropertyChangedEvent<T> event);
}
