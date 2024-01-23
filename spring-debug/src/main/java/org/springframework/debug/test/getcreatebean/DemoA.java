package org.springframework.debug.test.getcreatebean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @create 2024/1/17 15:30
 * @description
 */
@Data
public class DemoA {
  private String name = "DemoA";

  // @Autowired注解是按照类型（byType）装配依赖对象，默认情况下它要求依赖对象必须存在，如果允许null值，可以设置它的required属性为false。
  // 如果我们想使用按照名称（byName）来装配，可以结合@Qualifier注解一起使用。(通过类型匹配找到多个candidate,在没有@Qualifier、@Primary注解的情况下，会使用对象名作为最后的fallback匹配)
  @Autowired
  // @Qualifier("demoB")
  private DemoB demoB;
}
