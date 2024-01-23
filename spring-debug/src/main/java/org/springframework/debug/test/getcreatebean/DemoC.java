package org.springframework.debug.test.getcreatebean;

import javax.annotation.Resource;

/**
 * @create 2024/1/17 15:30
 * @description
 */
public class DemoC {
  private String name = "DemoC";

  // @Resource默认按照ByName自动注入，由J2EE提供，需要导入包javax.annotation.Resource。
  // @Resource有两个重要的属性：name和type，而Spring将@Resource注解的name属性解析为bean的名字，而type属性则解析为bean的类型。
  // 所以，如果使用name属性，则使用byName的自动注入策略，而使用type属性时则使用byType自动注入策略。如果既不制定name也不制定type属性，这时将通过反射机制使用byName自动注入策略
  @Resource(name = "demoD")
  //@Resource(type = DemoD.class)
  private DemoD demoD;
}
