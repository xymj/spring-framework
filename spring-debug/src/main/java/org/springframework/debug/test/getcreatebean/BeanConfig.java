package org.springframework.debug.test.getcreatebean;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @create 2024/1/17 15:33
 * @description
 */
@Configuration
public class BeanConfig {

  @Bean(autowire = Autowire.BY_TYPE)
  public DemoA demoA() {
    return new DemoA();
  }

  @Bean
  public DemoB demoB() {
    return new DemoB();
  }

  @Bean
  public DemoC demoC() {
    return new DemoC();
  }

  @Bean
  public DemoD demoD() {
    return new DemoD();
  }
}
