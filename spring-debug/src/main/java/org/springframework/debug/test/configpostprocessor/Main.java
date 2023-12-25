package org.springframework.debug.test.configpostprocessor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @create 2023/12/25 17:24
 * @description
 */
public class Main {
  public static void main(String[] args) {
    // Configuration注解通过cglib代理配置类，来拦截@Bean修饰的方法。这么做的目的是为了在配置类中多次调用@Bean方法返回的是同一个结果
	// demoController1 demoService: org.springframework.debug.test.configpostprocessor.DemoServiceImpl@679b62af
	// demoController2 demoService: org.springframework.debug.test.configpostprocessor.DemoServiceImpl@679b62af
//     AnnotationConfigApplicationContext context =
//     new AnnotationConfigApplicationContext(DemoConfig.class);


    // 如果使用@Component注解修饰Config类。则两次demoService()方法返回的结果则不相同。
    // 因为被@Component注解修饰的bean并不会调用ConfigurationClassPostProcessor#postProcessBeanFactory方法来进行方法代理
	// demoController1 demoService: org.springframework.debug.test.configpostprocessor.DemoServiceImpl@4b553d26
	// demoController2 demoService: org.springframework.debug.test.configpostprocessor.DemoServiceImpl@69a3d1d
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(DemoComponent.class);
    DemoController demoController1 = (DemoController) context.getBean("demoController1");
    System.out.println("demoController1 = " + demoController1);
    DemoController demoController2 = (DemoController) context.getBean("demoController2");
    System.out.println("demoController2 = " + demoController2);
  }
}
