package com.zkejid.constructor.webstaticresources.api.v1;

import java.nio.file.Path;

public interface ResourceRegistryApi {

  void addResource(Path filePath, String defaultUrlPath)
      throws UrlPathAlreadyUsedException, ResourceNotAvailableException;
}
