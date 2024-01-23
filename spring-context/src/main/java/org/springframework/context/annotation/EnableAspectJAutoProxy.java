/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.context.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Enables support for handling components marked with AspectJ's {@code @Aspect} annotation,
 * similar to functionality found in Spring's {@code <aop:aspectj-autoproxy>} XML element.
 * To be used on @{@link Configuration} classes as follows:
 *
 * <pre class="code">
 * &#064;Configuration
 * &#064;EnableAspectJAutoProxy
 * public class AppConfig {
 *
 *     &#064;Bean
 *     public FooService fooService() {
 *         return new FooService();
 *     }
 *
 *     &#064;Bean
 *     public MyAspect myAspect() {
 *         return new MyAspect();
 *     }
 * }</pre>
 *
 * Where {@code FooService} is a typical POJO component and {@code MyAspect} is an
 * {@code @Aspect}-style aspect:
 *
 * <pre class="code">
 * public class FooService {
 *
 *     // various methods
 * }</pre>
 *
 * <pre class="code">
 * &#064;Aspect
 * public class MyAspect {
 *
 *     &#064;Before("execution(* FooService+.*(..))")
 *     public void advice() {
 *         // advise FooService methods as appropriate
 *     }
 * }</pre>
 *
 * In the scenario above, {@code @EnableAspectJAutoProxy} ensures that {@code MyAspect}
 * will be properly processed and that {@code FooService} will be proxied mixing in the
 * advice that it contributes.
 *
 * <p>Users can control the type of proxy that gets created for {@code FooService} using
 * the {@link #proxyTargetClass()} attribute. The following enables CGLIB-style 'subclass'
 * proxies as opposed to the default interface-based JDK proxy approach.
 *
 * <pre class="code">
 * &#064;Configuration
 * &#064;EnableAspectJAutoProxy(proxyTargetClass=true)
 * public class AppConfig {
 *     // ...
 * }</pre>
 *
 * <p>Note that {@code @Aspect} beans may be component-scanned like any other.
 * Simply mark the aspect with both {@code @Aspect} and {@code @Component}:
 *
 * <pre class="code">
 * package com.foo;
 *
 * &#064;Component
 * public class FooService { ... }
 *
 * &#064;Aspect
 * &#064;Component
 * public class MyAspect { ... }</pre>
 *
 * Then use the @{@link ComponentScan} annotation to pick both up:
 *
 * <pre class="code">
 * &#064;Configuration
 * &#064;ComponentScan("com.foo")
 * &#064;EnableAspectJAutoProxy
 * public class AppConfig {
 *
 *     // no explicit &#064Bean definitions required
 * }</pre>
 *
 * <b>Note: {@code @EnableAspectJAutoProxy} applies to its local application context only,
 * allowing for selective proxying of beans at different levels.</b> Please redeclare
 * {@code @EnableAspectJAutoProxy} in each individual context, e.g. the common root web
 * application context and any separate {@code DispatcherServlet} application contexts,
 * if you need to apply its behavior at multiple levels.
 *
 * <p>This feature requires the presence of {@code aspectjweaver} on the classpath.
 * While that dependency is optional for {@code spring-aop} in general, it is required
 * for {@code @EnableAspectJAutoProxy} and its underlying facilities.
 *
 * @author Chris Beams
 * @author Juergen Hoeller
 * @since 3.1
 * @see org.aspectj.lang.annotation.Aspect
 *
 *
1. @EnableAspectJAutoProxy (proxyTargetClass = true)开始Aop功能。实际上在引入aspectj包之后, Spring默认会通过AopAutoConfiguration配置类开启AOP功能

2. @EnableAspectJAutoProxy (proxyTargetClass = true)注解通过@Import(AspectJAutoProxyRegistrar.class)引入了AspectJAutoProxyRegistrar类

3. AspectJAutoProxyRegistrar中注册了自动代理创建器AnnotationAwareAspectJAutoProxyCreator。之后的操作都在AnnotationAwareAspectJAutoProxyCreator中进行。

4. AnnotationAwareAspectJAutoProxyCreator实现了SmartInstantiationAwareBeanPostProcessor接口, 所以会在Bean创建的时候,
发现并记录了合适的所有的Advisor并进行拦截代理。

4.1. 发现所有的Advisor : 这里分为两步。
第一步是扫描BeanFactory中的所有Advisor类型的bean。这里直接通过BeanFactory获取即可。假设这里获取到的Advisor集合为Advisors1。
第二步则是通过扫描@Aspect注解找到切面类,随后筛选切面类中的方法。找到被@Around、@Before等注解修饰的通知(Advice) 进行解析，
并封装成不同的Advice类型(这里不包括@Pointcut注解的解析), @Pointcut会被解析成切入点(Pointcut类)。
随后Advice和Pointcut会被一起封装成一个Advisor (顾问,也即是Advisor) 。
也就是说,在这里Spring封装出了Pointcut类(实际上是AspectJExpressionPointcut)和
Advice类(这里的Advice根据注解使用的不同分为多个种类,如AspectJAroundAdvice、AspectJMethodBeforeAdvice等)以及
包含他俩的Advisor(实际上是InstantiationModelAwarePointcutAdvisorImpl)。
这个过程是在ReflectiveAspectJAdvisorFactory#getAdvisor和InstantiationModelAwarePointcutAdvisorImpl构造函数中完成的。
这里就动态解析出了@Aspect 注解下的切入点,并被封装成了Advisor集合。假设这里的Advisor集合为Advisors2。
这两步结束后，就解析出来了当前所有的Advisors =Advisors1 + Advisors2;

注：可以简单的理解为一个Advisor即一个增强操作,一个Advisor包含Advice和Pointcut。
Advice定义了具体增强操作,如前置,后置，环绕等,
Pointcut定义了织入规则(即哪些类可以被代理),满足规则的bean方法才能被增强。

4.2. 筛选所有合适的Advisor:上一-步筛选出来的Advisor可能并不适用于当前bean,所以需要筛选出合适于当前bean的Advisor。
比如@Pointcut ("execution (* com.demo.service.impl.UserServiceImpl.findAll())");切入的是UserServiceImpl,而当前的bean如果是
RoleServiceImpl就不应该被此Advisor所增强。



5. 第四步结束后，这里已经筛选出来了适合当前bean所使用的Advisor,下面需要创建代理类。创建代理则是委托给了ProxyFactory来完成。
ProxyFactory 使用 Advisor 中的 Advice 创建出来了代理增强类并注入到Spring容器中。

ProxyFactory中有筛选出来的Advisor集合,即Advisors。
ProxyFactory根据情况选择Jdk代理(JdkDynamicAopProxy)或者Cglib代理(ObjenesisCglibAopProxy)来完成创建代理类的操作。
最终执行的代理方法,实际上都是MethodInterceptor#invoke方法。

这里需要注意的是:对于AspectJAroundAdvice、AspectJAfterThrowingAdvice他们直接实现了MethodInterceptor接口,所以可以直接使用,
但是对于AspectJMethodBeforeAdvice、AspectJAfterAdvice、AspectJAfterReturningAdvice则需要进行一个适配。
这个适配的是在创建代理类的过程中。在AdvisedSupport#getInterceptorsAndDynamicInterceptionAdvice方法中完成。
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AspectJAutoProxyRegistrar.class)
public @interface EnableAspectJAutoProxy {

	/**
	 * Indicate whether subclass-based (CGLIB) proxies are to be created as opposed
	 * to standard Java interface-based proxies. The default is {@code false}.
	 */
	// 是否代理目标对象：即是否使用cglib 代理
	boolean proxyTargetClass() default false;

	/**
	 * Indicate that the proxy should be exposed by the AOP framework as a {@code ThreadLocal}
	 * for retrieval via the {@link org.springframework.aop.framework.AopContext} class.
	 * Off by default, i.e. no guarantees that {@code AopContext} access will work.
	 * @since 4.3.1
	 */
	// 是否暴露代理对象
	boolean exposeProxy() default false;

}
