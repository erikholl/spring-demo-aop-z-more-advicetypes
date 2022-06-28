package io.eho.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
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
	
	// add a new advice for @AfterReturning on findAccounts()
	
	@AfterReturning(
			pointcut="execution(* io.eho.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning="result")
	public void afterReturningFindAccountsAdvice(
			JoinPoint joinPoint, List<Account> result) {
		
		// print out the method we're advising on
		String method = joinPoint.getSignature().toShortString();
		System.out.println("\n====>>> Executing @AfterReturning on method: " + method);
		
		// print out the results of the method call
		System.out.println("\n====>>> result is: " + result);
		
		// let's post process the data ... modify it :)
		
		// convert the account names to UPPERCASE
		convertAccountNamesToUpperCase(result);
		
		// print out updated results
		System.out.println("\n====>>> result is: " + result);
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
		System.out.println("\n=====>>> Executing @Before advice on method");
		
		// display the method signature
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		System.out.println("Method: " + methodSignature);
		
		// display the method arguments
			// 1 get args
		Object[] args = joinPoint.getArgs();
		
			// 2 loop thru args
		for (Object arg : args) {
			System.out.println(arg);
			
			if (arg instanceof Account) {
				// downcast and print Account specific stuff
				Account account = (Account) arg;
				System.out.println("account name: " + account.getName());
				System.out.println("account level: " + account.getLevel());
				
			}
		}
	}

}
