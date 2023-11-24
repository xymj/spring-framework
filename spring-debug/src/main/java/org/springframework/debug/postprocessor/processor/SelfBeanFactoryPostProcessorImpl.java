package org.springframework.debug.postprocessor.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @create 2023/11/23 20:55
 * @description
 */
@Component
public class SelfBeanFactoryPostProcessorImpl implements BeanFactoryPostProcessor {
  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
      throws BeansException {

    System.out.println("BeanFactoryPostProcessor.postProcessBeanFactory ---> start");
    BeanDefinition beanFactoryPostProcessorBean =
        beanFactory.getBeanDefinition("beanFactoryPostProcessorBean");
    MutablePropertyValues propertyValues = beanFactoryPostProcessorBean.getPropertyValues();
    propertyValues.addPropertyValue("name",
        "name is beanFactoryPostProcessorBean, modify by BeanFactoryPostProcessor.postProcessBeanFactory");
    System.out.println("BeanFactoryPostProcessor.postProcessBeanFactory ---> end");
  }
}
