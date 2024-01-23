package org.springframework.debug.test.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @create 2024/1/23 11:09
 * @description
 */
@Component
@Aspect
public class AopDemo {

  @Pointcut("execution(* org.springframework.debug.test.aop.AopController.testAop(..)) && args(message)")
  public void pointCut(String message) {
    System.out.println("AopDemo.pointCut message: " + message);
  }

  @Before("pointCut(message)")
  public void before(String message) {
    System.out.println("AopDemo.before message: " + message);
  }

  @After("pointCut(message)")
  public void after(String message) {
    System.out.println("AopDemo.after message: " + message);
  }
}
