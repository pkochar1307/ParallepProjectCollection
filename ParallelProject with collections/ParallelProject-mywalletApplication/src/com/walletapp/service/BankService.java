package com.walletapp.service;

import java.util.List;

import com.walletapp.dto.Customer;
import com.walletapp.exception.BankException;
import com.walletapp.exception.InvalidAmount;
import com.walletapp.exception.InvalidPhoneNumber;
import com.walletapp.exception.NameException;

public interface BankService {
	
	public Customer createAccount(Customer c) throws BankException;
	public double showBalance (String mobileno) throws InvalidPhoneNumber, BankException;
	public Customer fundTransfer (String sourceMobileNo,String targetMobileNo, double amount) throws BankException;
	public Customer depositAmount (String mobileNo, double amount ) throws InvalidPhoneNumber, InvalidAmount, BankException;
	public Customer withdrawAmount(String mobileNo, double amount) throws BankException;
	public boolean validateUserName(String name) throws NameException;
	public boolean validatePhoneNumber(String mobNo) throws InvalidPhoneNumber;
	public boolean validateTargetMobNo(String targetMobNo) throws InvalidPhoneNumber;
	public boolean validateAmount(double amt) throws InvalidAmount;
	public boolean validateAll(Customer c) throws BankException, NameException, InvalidAmount, InvalidPhoneNumber;
	
}
