package org.springframework.debug.test.aop;

import org.springframework.stereotype.Controller;

/**
 * @create 2024/1/23 11:10
 * @description
 */

@Controller
public class AopController {
  public void testAop(String message) {
    System.out.println("testAop message: " + message);
  }
}
