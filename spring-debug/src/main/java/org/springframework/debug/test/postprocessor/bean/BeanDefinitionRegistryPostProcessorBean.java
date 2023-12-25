package org.springframework.debug.test.postprocessor.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @create 2023/11/23 20:47
 * @description
 */
@Getter
@Setter
public class BeanDefinitionRegistryPostProcessorBean {

	private String name = "default name";

	public BeanDefinitionRegistryPostProcessorBean() {
		System.out.println("BeanDefinitionRegistryPostProcessorBean init");
	}


	public void print() {
		System.out.println("BeanDefinitionRegistryPostProcessorBean print name:" + name);
	}
}
