package com.zkejid.projectgroupname.projectname.impl;

import com.zkejid.constructor.core.api.v1.ConstructionException;
import com.zkejid.constructor.core.api.v1.ConstructorPart;
import com.zkejid.projectgroupname.projectname.api.v1.Api;
import java.util.Collections;
import java.util.Set;

public class ConstructorPartImpl implements ConstructorPart {

  @Override
  public Set<Class<?>> getInterfacesNecessary() {
    return Collections.emptySet();
  }

  @Override
  public Set<Class<?>> getInterfacesProvided() {
    return Set.of(Api.class);
  }

  @Override
  public Object getImplementation(Class<?> anInterface) throws ConstructionException {
    if (Api.class.equals(anInterface)) {
      return new ApiImpl();
    } else {
      throw new ConstructionException("Module does not provide implementation of " + anInterface);
    }
  }

  @Override
  public void putImplementation(Class<?> interfaceNecessary, Object... implementation) {
    // No-op
  }

  @Override
  public void verifyNecessaryInterfaces() throws ConstructionException {
    // No-op
  }
}
