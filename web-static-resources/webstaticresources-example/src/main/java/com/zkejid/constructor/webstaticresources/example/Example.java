package com.zkejid.constructor.webstaticresources.example;

import com.zkejid.constructor.cliarguments.api.v1.Argument;
import com.zkejid.constructor.cliarguments.api.v1.ArgumentsFactory;
import com.zkejid.constructor.cliarguments.api.v1.ArgumentsParser;
import com.zkejid.constructor.cliarguments.api.v1.ParseResult;
import com.zkejid.constructor.core.api.v1.ConstructionException;
import com.zkejid.constructor.core.api.v1.ConstructorPart;
import com.zkejid.constructor.core.api.v1.CoreLogging;
import com.zkejid.constructor.core.api.v1.EntryPoint;
import com.zkejid.constructor.stringvalue.api.v1.InputValueType;
import com.zkejid.constructor.stringvalue.api.v1.StringValue;
import com.zkejid.constructor.webstaticresources.api.v1.ResourceRegistryApi;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

public class Example implements EntryPoint, CoreLogging, ConstructorPart {

  private ArgumentsFactory argumentsFactory;
  private ResourceRegistryApi resourceRegistryApi;

  @Override
  public Set<Class<?>> getInterfacesNecessary() {
    return Set.of(ArgumentsFactory.class, ResourceRegistryApi.class);
  }

  @Override
  public Set<Class<?>> getInterfacesProvided() {
    return Set.of(EntryPoint.class, CoreLogging.class);
  }

  @Override
  public Object getImplementation(Class<?> aClass) throws ConstructionException {
    if (EntryPoint.class.equals(aClass)) {
      return this;
    } else if (CoreLogging.class.equals(aClass)) {
      return this;
    } else {
      throw new ConstructionException("Module does not provide " + aClass);
    }
  }

  @Override
  public void putImplementation(Class<?> aClass, Object... objects) {
    if (ArgumentsFactory.class.equals(aClass)) {
      if (objects.length != 1) {
        throw new ConstructionException("Expects exactly 1 implementation. Got " + objects.length);
      }
      argumentsFactory = (ArgumentsFactory) objects[0];
    } else if (ResourceRegistryApi.class.equals(aClass)) {
      if (objects.length != 1) {
        throw new ConstructionException("Expects exactly 1 implementation. Got " + objects.length);
      }
      resourceRegistryApi = (ResourceRegistryApi) objects[0];
    }
  }

  @Override
  public void verifyNecessaryInterfaces() throws ConstructionException {
    if (argumentsFactory == null) {
      throw new ConstructionException("Expects ArgumentsFactory provided");
    }
    if (resourceRegistryApi == null) {
      throw new ConstructionException("Expects ResourceRegistryApi provided");
    }
  }

  @Override
  public void main(String[] strings) {
    final ArgumentsParser parser = argumentsFactory.createParser();
    final Argument filePath = parser.addProperty("f", "file-path");
    final Argument urlPath = parser.addProperty("u", "url-path");
    final ParseResult parseResult = parser.parse(strings);

    final StringValue filePathValue = parseResult.getArgumentsParsed().get(filePath);
    if (filePathValue.getInputValueType() != InputValueType.SPECIFIED) {
      throw new IllegalArgumentException("URL path does not specified");
    }
    final Path filePathTyped = Paths.get(filePathValue.getValue());

    final StringValue urlPathValue = parseResult.getArgumentsParsed().get(urlPath);
    if (urlPathValue.getInputValueType() != InputValueType.SPECIFIED) {
      throw new IllegalArgumentException("URL path does not specified");
    }

    resourceRegistryApi.addResource(filePathTyped, urlPathValue.getValue());
  }

  @Override
  public void log(String message) {
    System.out.println(message);
  }
}
