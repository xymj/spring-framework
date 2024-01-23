package org.springframework.debug.test.getcreatebean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @create 2024/1/17 15:30
 * @description
 */
@Data
public class DemoB {
  private String name = "DemoB";

  @Autowired
  @Qualifier("demoC")
  private DemoC demoC;
}
