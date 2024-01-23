package org.springframework.debug.test.beanpostprocessor;

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @create 2024/1/18 17:34
 * @description
 */
@Component
public class DemoBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

  // BeanPostProcessor 提供的方法，在bean初始化前调用，这时候的bean大体已经创建完成了，在完成一些其他属性的注入
  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException {
    System.out.println("DemoBeanPostProcessor.postProcessBeforeInitialization ---> 4");
    return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInitialization(bean,
        beanName);
  }

  // BeanPostProcessor 提供的方法，在bean初始化后调用，这时候的bean已经创建完成
  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("DemoBeanPostProcessor.postProcessAfterInitialization ---> 5");
    return InstantiationAwareBeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
  }

  // InstantiationAwareBeanPostProcessor 提供的方法
  // 此时bean还没创建，可以通过这方法代替Spring容器创建的方法
  @Override
  public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName)
      throws BeansException {
    System.out.println("DemoBeanPostProcessor.postProcessBeforeInstantiation ---> 1");
    return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInstantiation(beanClass,
        beanName);
  }

  // InstantiationAwareBeanPostProcessor 提供的方法， 返回的值代表是否需要继续注入属性，
  // 如果返回true，则会调用postProcessProperties和postProcessPropertyValues 来注入属性
  // 此时bean已经创建，属性尚未注入
  @Override
  public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
    System.out.println("DemoBeanPostProcessor.postProcessAfterInstantiation ---> 2");
    return InstantiationAwareBeanPostProcessor.super.postProcessAfterInstantiation(bean, beanName);
  }

  // InstantiationAwareBeanPostProcessor 提供的方法，可以在这个方法中进行bean属性的注入
  // 只有postProcessAfterInstantiation 返回true 时 且 postProcessProperties 返回 null 时调用
  @Override
  public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds,
      Object bean, String beanName) throws BeansException {
    System.out.println("DemoBeanPostProcessor.postProcessPropertyValues ---> 3");
    return InstantiationAwareBeanPostProcessor.super.postProcessPropertyValues(pvs, pds, bean,
        beanName);
  }
}
