package io.eho.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.eho.aopdemo.service.TrafficFortuneService;

public class AroundDemoApp {

	public static void main(String[] args) {

		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

		// get the bean from spring container
		TrafficFortuneService tfs = context.getBean("trafficFortuneService", TrafficFortuneService.class);
		
		System.out.println("\nMain program: AroundDemoApp");
		System.out.println("Calling getFortune()");

		String data = tfs.getFortune();
		
		System.out.println("\nMy fortune is: " + data);
		
		System.out.println("finished");

		// close the context
		context.close();

	}

}
