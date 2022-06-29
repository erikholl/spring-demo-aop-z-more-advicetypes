package io.eho.aopdemo;

import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.eho.aopdemo.service.TrafficFortuneService;

public class AroundHandleExceptionDemoApp {
	
	private static Logger logger = 
			Logger.getLogger(AroundHandleExceptionDemoApp.class.getName());

	public static void main(String[] args) {

		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

		// get the bean from spring container
		TrafficFortuneService tfs = context.getBean("trafficFortuneService", TrafficFortuneService.class);
		
		logger.info("\nMain program: AroundDemoApp");
		logger.info("Calling getFortune()");

		boolean tripWire = true;
		String data = tfs.getFortune(tripWire);
		
		logger.info("\nMy fortune is: " + data);
		
		logger.info("finished");

		// close the context
		context.close();

	}

}
