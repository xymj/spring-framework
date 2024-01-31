package org.springframework.debug.test.transactionaop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TransactionAopConfig.class);
		DbDemoService dbDemoService = (DbDemoService) context.getBean("dbDemoService");
		dbDemoService.query();

		dbDemoService.update(1, "A_1-1");

		dbDemoService.insert();
	}
}
