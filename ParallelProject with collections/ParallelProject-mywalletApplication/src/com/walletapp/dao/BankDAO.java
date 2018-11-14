package com.walletapp.dao;

import java.util.List;
import java.util.Set;

import com.walletapp.dto.Customer;
import com.walletapp.exception.BankException;
import com.walletapp.exception.InvalidPhoneNumber;

public interface BankDAO {
	
	public Customer createAccount(Customer c) throws BankException;
	public Customer getAccount(String mobileno);
	public boolean setAccount(String mobileNo, double amount);

}
