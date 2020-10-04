package com.zkejid.constructor.webstaticresources.api.v1.test;

import com.zkejid.constructor.webstaticresources.api.v1.ResourceNotAvailableException;
import com.zkejid.constructor.webstaticresources.api.v1.ResourceRegistryApi;
import com.zkejid.constructor.webstaticresources.api.v1.UrlPathAlreadyUsedException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The list of checks. Each implementation of {@link ResourceRegistryApi} should pass
 * given checks to match requirements of the API.
 */
public abstract class ResourceRegistryApiChecklist {

  private static FileSystem fileSystem;

  public abstract ResourceRegistryApi getApi();

  @BeforeAll
  public static void setUpClass() throws URISyntaxException, IOException {
    final URI resource = ResourceRegistryApi.class.getClassLoader().getResource("testpath").toURI();
    fileSystem = FileSystems.newFileSystem(resource, new HashMap<>());
  }

  @AfterAll
  public void tearDownClass() throws IOException {
    fileSystem.close();
  }

  @DisplayName("Test itself is able to create implementation")
  @Test
  void _testIsWorking() {
    final ResourceRegistryApi resourceRegistryApi = getApi();

    Assertions.assertNotNull(resourceRegistryApi, "Expect not null value");
  }

  @DisplayName("Test null file path to resource file fails")
  @Test
  void addResource_nullFilePath_IllegalArgumentException() {
    final ResourceRegistryApi resourceRegistryApi = getApi();

    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      resourceRegistryApi.addResource(null, "testpath");
    });
  }

  @DisplayName("Test null url path to resource file fails")
  @Test
  void addResource_nullUrlPath_IllegalArgumentException() {
    final ResourceRegistryApi resourceRegistryApi = getApi();

    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      resourceRegistryApi.addResource(getResource("testpath"), null);
    });
  }

  @DisplayName("Test existing resource file is applicable")
  @Test
  void addResource_validArgs_success() {
    final ResourceRegistryApi resourceRegistryApi = getApi();

    resourceRegistryApi.addResource(getResource("testpath"), "testpath");
  }

  @DisplayName("Test non existing resource file fails")
  @Test
  void addResource_nonExistingFile_FileNotFoundException() {
    final ResourceRegistryApi resourceRegistryApi = getApi();

    Assertions.assertThrows(ResourceNotAvailableException.class, () -> {
      resourceRegistryApi.addResource(Paths.get("nonexistsingtestpath"), "testpath");
    });
  }

  @DisplayName("Test addResource fails if url path already used")
  @Test
  void addResource_urlPathUsedTwice_UrlPathAlreadyUsed() {
    final ResourceRegistryApi resourceRegistryApi = getApi();

    resourceRegistryApi.addResource(getResource("testpath1"), "testpath");

    Assertions.assertThrows(UrlPathAlreadyUsedException.class, () -> {
      resourceRegistryApi.addResource(getResource("testpath2"), "testpath");
    });
  }

  @DisplayName("Test file can be bound to two url paths")
  @Test
  void addResource_oneFileBoundToTwoUrlPaths_success() {
    final ResourceRegistryApi resourceRegistryApi = getApi();

    resourceRegistryApi.addResource(getResource("testpath"), "testpath1");
    resourceRegistryApi.addResource(getResource("testpath"), "testpath2");
  }

  private Path getResource(String path) {
    try {
      return Paths.get(this.getClass().getClassLoader().getResource(path).toURI());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}
