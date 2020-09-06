package com.zkejid.constructor.webstaticresources.api.v1.test;

import com.zkejid.constructor.webstaticresources.api.v1.ResourceRegistryApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The list of checks. Each implementation of {@link ResourceRegistryApi} should pass
 * given checks to match requirements of the API.
 */
public abstract class ResourceRegistryApiChecklist {

  public abstract ResourceRegistryApi getApi();

  @DisplayName("Test itself is able to create implementation")
  @Test
  void _testIsWorking() {
    final ResourceRegistryApi resourceRegistryApi = getApi();

    Assertions.assertNotNull(resourceRegistryApi, "Expect not null value");
  }

  @DisplayName("Test not null file path to resource file")
  @Test
  void addResource_nullFilePath_IllegalArgumentException() {
    final ResourceRegistryApi resourceRegistryApi = getApi();

    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      resourceRegistryApi.addResource(null, "testpath");
    });
  }

  @DisplayName("Test not null url path to resource file")
  @Test
  void addResource_nullUrlPath_IllegalArgumentException() {
    final ResourceRegistryApi resourceRegistryApi = getApi();

    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      resourceRegistryApi.addResource("testpath", null);
    });
  }
}
