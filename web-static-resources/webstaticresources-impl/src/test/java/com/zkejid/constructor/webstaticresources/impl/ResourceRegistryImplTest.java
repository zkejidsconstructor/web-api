package com.zkejid.constructor.webstaticresources.impl;

import com.zkejid.constructor.core.api.v1.ConstructionException;
import com.zkejid.constructor.core.api.v1.CoreLogging;
import com.zkejid.constructor.webstaticresources.api.v1.ResourceRegistryApi;
import com.zkejid.constructor.webstaticresources.api.v1.test.ResourceRegistryApiChecklist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ResourceRegistryImplTest extends ResourceRegistryApiChecklist {

  private String lastMessage;

  @BeforeEach
  public void setUp() {
    lastMessage = null;
  }

  @Override
  public ResourceRegistryApi getApi() {
    final ResourceRegistryImpl resourceRegistry = new ResourceRegistryImpl();
    resourceRegistry.putImplementation(CoreLogging.class, new CoreLogging() {
      @Override
      public void log(String message) {
        lastMessage = message;
      }
    });
    return resourceRegistry;
  }

  @Test
  public void verifyNecessaryInterfaces_noLoggingSpecified_ConstructionException() {
    final ResourceRegistryImpl impl = new ResourceRegistryImpl();

    Assertions.assertThrows(ConstructionException.class, impl::verifyNecessaryInterfaces);
  }
}