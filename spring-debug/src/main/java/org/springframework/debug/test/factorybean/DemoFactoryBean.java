package org.springframework.debug.test.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @create 2023/12/25 19:24
 * @description
 */
@Component
public class DemoFactoryBean implements FactoryBean<DemoBean> {
	@Override
	public DemoBean getObject() throws Exception {
		System.out.println("getObject return new DemoBean()");
		return new DemoBean();
	}

	@Override
	public Class<?> getObjectType() {
		return DemoBean.class;
	}

	@Override
	public boolean isSingleton() {
		return FactoryBean.super.isSingleton();
	}
}
