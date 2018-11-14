package com.walletapp.dao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.walletapp.dto.Customer;
import com.walletapp.exception.BankException;

public class BankDAOImpl implements BankDAO {

	Map<String, Customer> map;

	public BankDAOImpl() {
		map = new HashMap<String, Customer>();
		map.put("7038216458", new Customer("Priya", "7038216458", 1000));
		map.put("9404278150", new Customer("Pooja", "9404278150", 6000));
	}

	@Override
	public Customer createAccount(Customer c1) {
		// TODO Auto-generated method stub
		map.put(c1.getMobileNumber(), c1);
		return c1;
	}

	@Override
	public Customer getAccount(String mobileno) {
		// TODO Auto-generated method stub
		Customer cShow = map.get(mobileno);
		return cShow;
	}

	@Override
	public boolean setAccount(String mobileNo, double amount) {
		// TODO Auto-generated method stub
		Customer cSet = map.get(mobileNo);
		cSet.setAmount(amount);
		System.out.println(cSet);
		map.put(mobileNo, cSet);
		return true;
	}


}
