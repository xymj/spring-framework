package org.springframework.debug.test.beandefinition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @create 2023/12/26 15:54
 * @description
 */
@Configuration
public class DemoConfig {

  @Bean
  public DemoBean demoBean() {
    return new DemoBean();
  }
}
