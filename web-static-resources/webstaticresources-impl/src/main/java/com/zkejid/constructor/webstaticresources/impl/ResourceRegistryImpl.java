package com.zkejid.constructor.webstaticresources.impl;

import com.zkejid.constructor.core.api.v1.ConstructionException;
import com.zkejid.constructor.core.api.v1.ConstructorPart;
import com.zkejid.constructor.core.api.v1.CoreLogging;
import com.zkejid.constructor.webstaticresources.api.v1.ResourceNotAvailableException;
import com.zkejid.constructor.webstaticresources.api.v1.ResourceRegistryApi;
import com.zkejid.constructor.webstaticresources.api.v1.UrlPathAlreadyUsedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ResourceRegistryImpl implements ResourceRegistryApi, ConstructorPart {

  private Map<String, Path> registryMap = new HashMap<>();
  private CoreLogging[] logging;

  @Override
  public Set<Class<?>> getInterfacesNecessary() {
    return Set.of(CoreLogging.class);
  }

  @Override
  public Set<Class<?>> getInterfacesProvided() {
    return Set.of(ResourceRegistryApi.class);
  }

  @Override
  public Object getImplementation(Class<?> anInterface) throws ConstructionException {
    return this;
  }

  @Override
  public void putImplementation(Class<?> interfaceNecessary, Object... implementation) {
    if (CoreLogging.class.equals(interfaceNecessary)) {
      logging = new CoreLogging[implementation.length];
      for (int i = 0; i < implementation.length; i++) {
        logging[i] = (CoreLogging) implementation[i];
      }
    }
  }

  @Override
  public void verifyNecessaryInterfaces() throws ConstructionException {
    if (logging == null || logging.length == 0) {
      throw new ConstructionException("No logging specified");
    }
  }

  @Override
  public void addResource(Path filePath, String defaultUrlPath) throws UrlPathAlreadyUsedException {
    if (filePath == null) {
      throw new IllegalArgumentException("File path should be specified");
    }
    if (defaultUrlPath == null) {
      throw new IllegalArgumentException("Url path should be specified");
    }
    final Path oldValue = registryMap.put(defaultUrlPath, filePath);
    if (oldValue != null) {
      throw new UrlPathAlreadyUsedException("url " + defaultUrlPath + "already used by " + oldValue);
    }
    if (!Files.exists(filePath)) {
      throw new ResourceNotAvailableException(filePath.toString());
    }
    for (CoreLogging coreLogging : logging) {
      coreLogging.log("filePath=" + filePath + ",defaultUrlPath="+defaultUrlPath);
    }
  }
}
