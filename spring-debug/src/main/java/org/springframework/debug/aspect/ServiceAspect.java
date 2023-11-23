package org.springframework.debug.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @create 2023/11/11 14:29
 * @description
 */

@Component
@Aspect
public class ServiceAspect {

	@Pointcut("execution(* org.springframework.debug.service.UserService.test(..))")
	public void testPointcut() {
		System.out.println("ServiceAspect.testPointcut");
	}

	@Before("testPointcut()")
	public void testBefore() {
        System.out.println("ServiceAspect.testBefore");
    }

	@After("testPointcut()")
	public void testAfter() {
        System.out.println("ServiceAspect.testAfter");
    }

	@AfterReturning("testPointcut()")
	public void testReturn() {
        System.out.println("ServiceAspect.testReturn");
    }

	@AfterThrowing("testPointcut()")
    public void testThrow() {
        System.out.println("ServiceAspect.testThrow");
    }

	@Around("testPointcut()")
	public void testAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("ServiceAspect.testAround before");
		pjp.proceed();
		System.out.println("ServiceAspect.testAround after");
    }

}
