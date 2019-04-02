package com.globallogic.test.tree;

import com.globallogic.test.tree.exception.AddingChildIsProhibitedForThisNodeException;
import org.junit.jupiter.api.BeforeAll;

public class ContainerTreeTest {
  private ContainerTree<Integer> containers;

  @BeforeAll
  void setUp() throws AddingChildIsProhibitedForThisNodeException {
    containers = new ContainerTree<>();
    containers.add(1);
    containers.add(2);
    containers.add(3);
    containers.add(4);
    containers.add(5);
  }
}
