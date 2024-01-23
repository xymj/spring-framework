package org.springframework.debug.test.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @create 2024/1/23 11:09
 * @description
 */
@Configuration
@EnableAspectJAutoProxy // 开启代理，自动不会开启，此注解会引入AspectJAutoProxyRegistrar
@ComponentScan(basePackages = "org.springframework.debug.test.aop")
public class AopConfig {
}
