package com.zkejid.constructor.webstaticresources.example;

import com.zkejid.constructor.core.impl.Constructor;
import com.zkejid.constructor.core.impl.PartProvider;

public class Application {

  public static void main(String[] args) {
    final PartProvider partProvider = new PartProvider();
    final Constructor constructor = new Constructor(partProvider);
    constructor.main(args);
  }
}
