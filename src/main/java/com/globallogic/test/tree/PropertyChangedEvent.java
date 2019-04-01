package com.globallogic.test.tree;

public class PropertyChangedEvent<T> {
  private T source;
  private TreeEvent event;
  private Object newValue;

  public PropertyChangedEvent(T source, TreeEvent event, Object newValue) {
    this.source = source;
    this.event = event;
    this.newValue = newValue;
  }

}
