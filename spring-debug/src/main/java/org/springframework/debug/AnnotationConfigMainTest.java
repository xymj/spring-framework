package org.springframework.debug;

import org.springframework.debug.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.debug.service.UserService;

public class AnnotationConfigMainTest {
  public static void main(String[] args) {
    System.out.println("start main");
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(AppConfig.class);
    UserService userService = (UserService) applicationContext.getBean("userService");
    userService.test();
    System.out.println("orderService: " + applicationContext.getBean("orderService"));
    System.out.println("orderService1: " + applicationContext.getBean("orderService1"));

  }
}
