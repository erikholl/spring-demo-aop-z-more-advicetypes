package io.eho.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import io.eho.aopdemo.Account;

@Component
public class AccountDAO {
	
	private String name;
	private String serviceCode;
	
	public String getName() {
		System.out.println(getClass() + ": in getName()");
		return name;
	}

	public void setName(String name) {
		System.out.println(getClass() + ": in setName()");
		this.name = name;
	}
	
	public List<Account> findAccounts() {
		
		List<Account> accounts = new ArrayList<>();
		
		// create sample accounts
		Account a1 = new Account("Erik", "Silver");
		Account a2 = new Account("Luca", "Gold");
		Account a3 = new Account("Madhu", "Platinum");
		
		// add accounts to list
		accounts.add(a1);
		accounts.add(a2);
		accounts.add(a3);
		
		return accounts;
	}

	public void addAccount(Account account, boolean vipFlag) {
		
		System.out.println(getClass() + ": Doing DB work: add account");
	}
	
	public boolean doWork() {
		
		System.out.println(getClass() + ": doing work");
		
		return true;
	}

	public String getServiceCode() {
		System.out.println(getClass() + ": in getServiceCode()");
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		System.out.println(getClass() + ": in setServiceCode()");
		this.serviceCode = serviceCode;
	}

}
