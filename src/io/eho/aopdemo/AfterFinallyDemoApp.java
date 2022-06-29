package io.eho.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.eho.aopdemo.dao.AccountDAO;

public class AfterFinallyDemoApp {

	public static void main(String[] args) {

		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

		// get the bean from spring container
		AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

		// call the method to find accounts
		List<Account> accounts = null;

		try {
			// add a boolean flag to simulate exception
			boolean tripWire = false;
			accountDAO.findAccounts(tripWire);
		} catch (Exception e) {
			System.out.println("\n\nMain program ... caught exception: " + e);
		}

		// print results
		System.out.println("\n\nMain program: AfterThrowingDemoApp");
		System.out.println("--------");
		System.out.println(accounts);
		System.out.println("\n");

		// close the context
		context.close();

	}

}
