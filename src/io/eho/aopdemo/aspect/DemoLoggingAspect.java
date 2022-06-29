package io.eho.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import io.eho.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class DemoLoggingAspect {
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	// add a new advice for @Around
	@Around("execution(* io.eho.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(
			ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		// print out method we're advising on
		String method = proceedingJoinPoint.getSignature().toShortString();
		logger.info("\n====>>> Executing @Around on method: " + method);
		
		// get begin timestamp
		long begin = System.currentTimeMillis();
		
		// execute method
		Object result = null;
		
		// 1.rethrow the exception
		try {
			result = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			// log the exception
			logger.warning(e.getMessage());
			
			// rethrow the exception
			throw e;
		}
		
		// 2. handle the exception
//		try {
//			result = proceedingJoinPoint.proceed();
//		} catch (Throwable e) {
//			// log the exception
//			logger.warning(e.getMessage());
//			
//			// give user custom message
//			result = "Major incident! No worries though, we'll get you there";
//		}
		
		// get end timestamp
		long end = System.currentTimeMillis();
		
		// compute duration
		long duration = end - begin;
		logger.info("\n====>>> Duration: " + duration / 1000.0 + " seconds");
		
		return result;
		
	}

	// add a new advice for @After
	@After("execution(* io.eho.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {

		// print out which method we are advising on
		String method = joinPoint.getSignature().toShortString();
		logger.info("\n====>>> Executing @After (finally) on method: " + method);

	}

	// add a new advice for @AfterThrowing
	@AfterThrowing(pointcut = "execution(* io.eho.aopdemo.dao.AccountDAO.findAccounts(..))", throwing = "e")
	public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable e) {

		// print out which method we are advising on
		String method = joinPoint.getSignature().toShortString();
		logger.info("\n====>>> Executing @AfterThrowing on method: " + method);

		// log the exception
		logger.info("\n====>>> The exception is: " + e);

	}

	// add a new advice for @AfterReturning on findAccounts()
	@AfterReturning(pointcut = "execution(* io.eho.aopdemo.dao.AccountDAO.findAccounts(..))", returning = "result")
	public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {

		// print out the method we're advising on
		String method = joinPoint.getSignature().toShortString();
		logger.info("\n====>>> Executing @AfterReturning on method: " + method);

		// print out the results of the method call
		logger.info("\n====>>> result is: " + result);

		// let's post process the data ... modify it :)

		// convert the account names to UPPERCASE
		convertAccountNamesToUpperCase(result);

		// print out updated results
		logger.info("\n====>>> result is: " + result);
	}

	private void convertAccountNamesToUpperCase(List<Account> result) {
		// loop through accounts
		for (Account a : result) {
			// get uppercase version of the name
			String upperName = a.getName().toUpperCase();

			// update the name on the account
			a.setName(upperName);

		}

	}

	@Before("io.eho.aopdemo.aspect.ShareAOPExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint joinPoint) {
		logger.info("\n=====>>> Executing @Before advice on method");

		// display the method signature
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		logger.info("Method: " + methodSignature);

		// display the method arguments
		// 1 get args
		Object[] args = joinPoint.getArgs();

		// 2 loop thru args
		for (Object arg : args) {
			logger.info(arg.toString()); // logger will only print String objects

			if (arg instanceof Account) {
				// downcast and print Account specific stuff
				Account account = (Account) arg;
				logger.info("account name: " + account.getName());
				logger.info("account level: " + account.getLevel());

			}
		}
	}

}
