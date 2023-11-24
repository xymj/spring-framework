package org.springframework.debug.postprocessor.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @create 2023/11/23 20:47
 * @description
 */
@Getter
@Setter
public class BeanFactoryPostProcessorBean {

  private String name = "default name";

  public BeanFactoryPostProcessorBean() {
    System.out.println("BeanFactoryPostProcessorBean init");
  }


  public void print() {
    System.out.println("BeanFactoryPostProcessorBean print name:" + name);
  }
}
