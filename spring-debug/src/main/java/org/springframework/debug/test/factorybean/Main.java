package org.springframework.debug.test.factorybean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @create 2023/12/25 19:27
 * @description
 */
public class Main {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(FactoryBeanConfig.class);
    DemoBean demoBean = (DemoBean) context.getBean("demoBean");
    System.out.println("demoBean = " + demoBean);

    // getObject return new DemoBean()
    // demoFactoryBean = org.springframework.debug.test.factorybean.DemoBean@7f9fcf7f
    // demoFactoryBean2 = org.springframework.debug.test.factorybean.DemoFactoryBean@2357d90a
    Object demoFactoryBean = context.getBean("demoFactoryBean");
    System.out.println("demoFactoryBean = " + demoFactoryBean);
    Object demoFactoryBean2 = context.getBean("&demoFactoryBean");
    System.out.println("demoFactoryBean2 = " + demoFactoryBean2);

  }
}
