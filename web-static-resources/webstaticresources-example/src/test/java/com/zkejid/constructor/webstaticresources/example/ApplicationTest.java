package com.zkejid.constructor.webstaticresources.example;

import com.zkejid.constructor.core.impl.Constructor;
import com.zkejid.constructor.core.impl.PartProvider;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ApplicationTest {

  @Test
  public void main_validArgs_success() {
    final PartProvider partProvider = new PartProvider();
    final Constructor constructor = new Constructor(partProvider);

    constructor.main(new String[]{"-f", getResource("testfile"), "-u", "/hello/world"});
  }

  @Test
  public void main_noArguments_IllegalArgumentException() {
    final PartProvider partProvider = new PartProvider();
    final Constructor constructor = new Constructor(partProvider);
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> constructor.main(new String[]{})
    );
  }

  private String getResource(String path) {
    try {
      return Paths.get(this.getClass().getClassLoader().getResource(path).toURI()).toString();
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

}