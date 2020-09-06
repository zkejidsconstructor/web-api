package com.zkejid.projectgroupname.projectname.api.v1.test;

import com.zkejid.projectgroupname.projectname.api.v1.Api;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The list of checks. Each implementation of {@link Api} should pass
 * given checks to match requirements of the API.
 */
public abstract class ApiChecklist {

  public abstract Api getApi();

  @DisplayName("Test itself is able to create implementation")
  @Test
  void _testIsWorking() {
    final Api api = getApi();

    Assertions.assertNotNull(api, "Expect not null value");
  }
}
