package org.springframework.debug.test.postprocessor.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.debug.test.postprocessor.bean.BeanDefinitionRegistryPostProcessorBean;
import org.springframework.stereotype.Component;

/**
 * @create 2023/11/23 20:55
 * @description
 */
@Component
public class SelfBeanDefinitionRegistryPostProcessorImpl
    implements BeanDefinitionRegistryPostProcessor {

  @Override
  public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
      throws BeansException {
    System.out.println(
        "BeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry ---> start");
    RootBeanDefinition rootBeanDefinition =
        new RootBeanDefinition(BeanDefinitionRegistryPostProcessorBean.class);
    registry.registerBeanDefinition("beanDefinitionRegistryPostProcessorBean", rootBeanDefinition);
    System.out
        .println("BeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry ---> end");

  }

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
      throws BeansException {
    System.out.println("BeanDefinitionRegistryPostProcessor.postProcessBeanFactory ---> start");
    BeanDefinition beanDefinitionRegistryPostProcessorBean =
        beanFactory.getBeanDefinition("beanDefinitionRegistryPostProcessorBean");
    MutablePropertyValues propertyValues =
        beanDefinitionRegistryPostProcessorBean.getPropertyValues();
    propertyValues.addPropertyValue("name",
        "name is beanDefinitionRegistryPostProcessorBean, modify by BeanDefinitionRegistryPostProcessor.postProcessBeanFactory");
    System.out.println("BeanDefinitionRegistryPostProcessor.postProcessBeanFactory ---> end");
  }

}
