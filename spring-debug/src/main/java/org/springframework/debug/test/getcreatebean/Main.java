package org.springframework.debug.test.getcreatebean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @create 2024/1/17 15:31
 * @description
 */
public class Main {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(BeanConfig.class);
    Object demoA = context.getBean("demoA");
    System.out.println("demoA: " + demoA);
    Object demoB = context.getBean("demoB");
    System.out.println("demoB: " + demoB);
    Object demoC = context.getBean("demoC");
    System.out.println("demoC: " + demoC);
    Object demoD = context.getBean("demoD");
    System.out.println("demoD: " + demoD);
  }
}
