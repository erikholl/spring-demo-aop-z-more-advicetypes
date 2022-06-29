package io.eho.aopdemo;

import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.eho.aopdemo.service.TrafficFortuneService;

public class AroundWithLoggerDemoApp {
	
	private static Logger logger = 
			Logger.getLogger(AroundWithLoggerDemoApp.class.getName());

	public static void main(String[] args) {

		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

		// get the bean from spring container
		TrafficFortuneService tfs = context.getBean("trafficFortuneService", TrafficFortuneService.class);
		
		logger.info("\nMain program: AroundDemoApp");
		logger.info("Calling getFortune()");

		String data = tfs.getFortune();
		
		logger.info("\nMy fortune is: " + data);
		
		logger.info("finished");

		// close the context
		context.close();

	}

}
