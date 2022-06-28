package io.eho.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.eho.aopdemo.dao.AccountDAO;

public class AfterReturningDemoApp {

	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class); 
		
		// get the bean from spring container
		AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		// call the method to find accounts
		List<Account> accounts = accountDAO.findAccounts();
		
		// print results
		System.out.println("\n\nMain program: AfterReturningDemoApp");
		System.out.println("--------");
		System.out.println(accounts);
		System.out.println("\n");
		
		
		// close the context
		context.close();

	}

}
