package org.springframework.debug.test.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @create 2024/1/23 11:10
 * @description
 */
public class Main {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(AopConfig.class);
    AopController aopController = (AopController) context.getBean("aopController");
    aopController.testAop("hello");
  }
}
