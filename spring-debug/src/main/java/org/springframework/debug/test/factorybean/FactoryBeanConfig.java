package org.springframework.debug.test.factorybean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @create 2023/12/25 19:26
 * @description
 */
@Configuration
@ComponentScan(basePackages = "org.springframework.debug.test.factorybean")
public class FactoryBeanConfig {

  @Bean
  public DemoBean demoBean() {
    System.out.println("FactoryBeanConfig.demoBean init");
    return new DemoBean();
  }
}
