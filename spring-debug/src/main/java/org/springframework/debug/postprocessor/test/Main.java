package org.springframework.debug.postprocessor.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.debug.postprocessor.bean.BeanDefinitionRegistryPostProcessorBean;
import org.springframework.debug.postprocessor.bean.BeanFactoryPostProcessorBean;
import org.springframework.debug.postprocessor.config.PostProcessorAppConfig;

/**
 * @create 2023/11/24 11:28
 * @description
 */
public class Main {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(PostProcessorAppConfig.class);
    BeanFactoryPostProcessorBean beanFactoryPostProcessorBean =
        (BeanFactoryPostProcessorBean) context.getBean("beanFactoryPostProcessorBean");
    BeanDefinitionRegistryPostProcessorBean beanDefinitionRegistryPostProcessorBean =
        (BeanDefinitionRegistryPostProcessorBean) context
            .getBean("beanDefinitionRegistryPostProcessorBean");

    beanFactoryPostProcessorBean.print();
    System.out.println("======================================================================");
    beanDefinitionRegistryPostProcessorBean.print();
  }
}
