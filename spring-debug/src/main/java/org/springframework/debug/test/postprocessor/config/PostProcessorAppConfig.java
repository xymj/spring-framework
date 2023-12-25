package org.springframework.debug.test.postprocessor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.debug.test.postprocessor.bean.BeanFactoryPostProcessorBean;

/**
 * @create 2023/11/24 11:17
 * @description
 */
@ComponentScan(basePackages = "org.springframework.debug.test.postprocessor.*")
public class PostProcessorAppConfig {

  @Bean
  public BeanFactoryPostProcessorBean beanFactoryPostProcessorBean() {
    return new BeanFactoryPostProcessorBean();
  }
}
