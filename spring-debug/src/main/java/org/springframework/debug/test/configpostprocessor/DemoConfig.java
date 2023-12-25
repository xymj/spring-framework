package org.springframework.debug.test.configpostprocessor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @create 2023/12/25 17:20
 * @description
 */
@Configuration
public class DemoConfig {

  @Bean
  public DemoService demoService() {
    return new DemoServiceImpl();
  }

  @Bean
  public DemoController demoController1() {
    DemoService demoService = demoService();
    System.out.println("demoController1 demoService: " + demoService);
    return new DemoController();
  }

  @Bean
  public DemoController demoController2() {
    DemoService demoService = demoService();
    System.out.println("demoController2 demoService: " + demoService);
    return new DemoController();
  }
}
