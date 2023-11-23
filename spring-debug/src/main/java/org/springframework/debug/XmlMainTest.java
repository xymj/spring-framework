package org.springframework.debug;


import org.springframework.debug.bean.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @create 2023/11/1 19:39
 * @description
 */
public class XmlMainTest {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
		Person person = (Person) context.getBean("person");
		System.out.println(person);
	}
}
