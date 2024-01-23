package org.springframework.debug.test.beanpostprocessor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @create 2024/1/18 17:35
 * @description
 */
public class Main {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(DemoBeanPostProcessorConfig.class);
    Object demoBeanPostProcessor = context.getBean("demoBeanPostProcessor");
    System.out.println(demoBeanPostProcessor);
  }
}
