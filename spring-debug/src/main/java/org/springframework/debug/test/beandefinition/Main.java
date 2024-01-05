package org.springframework.debug.test.beandefinition;

import org.springframework.beans.factory.support.ChildBeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @create 2023/12/26 15:53
 * @description
 */
public class Main {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(DemoConfig.class);
    DemoBean demoBean = (DemoBean) context.getBean("demoBean");
    System.out.println("demoBean: " + demoBean);
	  System.out.println("=========================================");

    // 演示 RootBeanDefinition 的使用，通过它，提供了创建一个 bean 所需的所有信息
    RootBeanDefinition rbd = new RootBeanDefinition();
    rbd.setBeanClass(DemoBean.class);
    rbd.getPropertyValues().add("name", "test-root");
    rbd.getPropertyValues().add("type", "cat");
    context.registerBeanDefinition("catRoot", rbd);
    DemoBean catRoot = (DemoBean) context.getBean("catRoot");
    System.out.println("catRoot: " + catRoot);
    System.out.println("=========================================");

    // 创建一个 ChildBeanDefinition 总是要依赖于另外一个 bean定义，也就是该孩子 bean定义的双亲bean定义
    // 此例子演示 ChildBeanDefinition 的使用，该例子中，所创建的 ChildBeanDefinition 使用了上面定义的名
    // 为catRoot的 RootBeanDefinition。
    // 实际上 ChildBeanDefinition 的双亲parent bean定义的类型不必要是 RootBeanDefinition,也可以是
    // ChildBeanDefinition 或者 GenericBeanDefinition
    ChildBeanDefinition cbd = new ChildBeanDefinition("catRoot");
    cbd.getPropertyValues().add("name", "test-child");
    context.registerBeanDefinition("catChild", cbd);
    Object catChild = context.getBean("catChild");
    System.out.println("catChild: " + catChild);
    DemoBean catRoot2 = (DemoBean) context.getBean("catRoot");
    System.out.println("catRoot2: " + catRoot2);
    System.out.println("=========================================");

    // 基于 GenericBeanDefinition 的双亲bean定义
    GenericBeanDefinition gbd = new GenericBeanDefinition();
    gbd.setBeanClass(DemoBean.class);
    gbd.getPropertyValues().add("name", "test-generic-parent");
    gbd.getPropertyValues().add("type", "dog");
    context.registerBeanDefinition("dogGenericParent", gbd);
    Object dogGenericParent = context.getBean("dogGenericParent");
    System.out.println("dogGenericParent: " + dogGenericParent);

    GenericBeanDefinition gbdChildren = new GenericBeanDefinition();
    gbdChildren.setParentName("dogGenericParent");
    gbdChildren.getPropertyValues().add("name", "test-generic-child");
    context.registerBeanDefinition("dogGenericChild", gbdChildren);
    Object dogGenericChild = context.getBean("dogGenericChild");
    System.out.println("dogGenericChild: " + dogGenericChild);
    Object dogGenericParent2 = context.getBean("dogGenericParent");
    System.out.println("dogGenericParent2: " + dogGenericParent2);


  }
}
