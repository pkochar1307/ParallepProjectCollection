package com.walletapp.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.walletapp.dao.BankDAO;
import com.walletapp.dao.BankDAOImpl;
import com.walletapp.dto.Customer;
import com.walletapp.exception.BankException;
import com.walletapp.exception.InvalidAmount;
import com.walletapp.exception.InvalidPhoneNumber;
import com.walletapp.exception.NameException;

public class BankServiceImpl implements BankService {

	BankDAO dao;

	public BankServiceImpl() {
		dao = new BankDAOImpl();
	}

	@Override
	public boolean validateAll(Customer c) throws BankException, NameException, InvalidAmount, InvalidPhoneNumber {

		boolean b = false;
		
		if (validateUserName(c.getCustomerName()) == true
				&& validatePhoneNumber(c.getMobileNumber()) == true
				&& validateAmount(c.getAmount()) == true)
			b = true;
		if (!b)
			throw new BankException("Invalid details");
		return b;
	}

	@Override
	public boolean validateUserName(String name) throws NameException {

		if(name==null) 
			throw new NameException("Null value");
		Pattern p = Pattern.compile("[A-Z]{1}[a-z]{2,30}");
		Matcher mat = p.matcher(name);
		if(!mat.matches())
			System.out.println("Name Invalid");
		return mat.matches();
	}

	@Override
	public boolean validatePhoneNumber(String mobileNo) throws InvalidPhoneNumber {
		if(mobileNo==null) 
			throw new InvalidPhoneNumber("Null value");
		Pattern pat = Pattern.compile("[6-9]{1}[0-9]{9}");
		Matcher mat = pat.matcher(mobileNo);
		if(!mat.matches())
			System.out.println("Mobile Invalid");
		return mat.matches();
	}

	@Override
	public boolean validateAmount(double amt) throws InvalidAmount {

		Pattern pat = Pattern.compile("[1-9]{1}[0-9.]{0,9}");
		Matcher mat = pat.matcher(String.valueOf(amt));
		boolean b = mat.matches();

		if (!b)
			throw new InvalidAmount();

		return b;
	}

	@Override
	public boolean validateTargetMobNo(String targetMobNo) throws InvalidPhoneNumber {
		if(targetMobNo==null) 
			throw new InvalidPhoneNumber("Null value");
		Pattern pat = Pattern.compile("[6-9]{1}[0-9]{9}");
		Matcher mat = pat.matcher(targetMobNo);
		boolean b = mat.matches();

		if (!b)
			throw new InvalidPhoneNumber("target Mobile Invalid");

		return b;
	}

	@Override
	public Customer createAccount(Customer c) throws BankException {
		// TODO Auto-generated method stub
		Customer create = dao.createAccount(c);
		
		if(create == null)
			throw new BankException("Mobile number doesn't exist");
		return create;
	}

	@Override
	public double showBalance(String mobileno) throws BankException,
			BankException {
		// TODO Auto-generated method stub
		String tranType = "Check balance";
		Customer bal = dao.getAccount(mobileno);
		if (bal == null)
			throw new BankException("Mobile number doesn't exist");
		return bal.getAmount();
	}

	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo,
			double amount) throws BankException {
		// TODO Auto-generated method stub
		String tranType = "Transfer";
		Customer sbal = dao.getAccount(sourceMobileNo);
		Customer tbal = dao.getAccount(targetMobileNo);
		if (sbal == null)
			throw new BankException("Mobile number doesn't exist");
		if (tbal == null)
			throw new BankException("Mobile number doesn't exist");
		double tmp = (sbal.getAmount() - amount);
		if (tmp >= 0) {
			dao.setAccount(targetMobileNo, tbal.getAmount() + amount);
			dao.setAccount(sourceMobileNo, sbal.getAmount() - amount);
		} else {
			throw new BankException(
					"Minimum balance of Rupees greater than zero should be available...");
		}
//		System.out.println(dao.getAccount(sourceMobileNo));
		return dao.getAccount(sourceMobileNo);
	}

	@Override
	public Customer depositAmount(String mobileNo, double amount)
			throws InvalidPhoneNumber, InvalidAmount, BankException {
		// TODO Auto-generated method stub
		String tranType = "Deposit";
		Customer cDep1 = dao.getAccount(mobileNo);
		if (cDep1 == null)
			throw new BankException("Mobile number doesn't exist");
		boolean c = dao.setAccount(mobileNo, cDep1.getAmount() + amount);
//		System.out.println(c);
		Customer cDep = dao.getAccount(mobileNo);
		if (!c)
			throw new BankException("Unable to deposit");
		else
			return cDep;
	}

	@Override
	public Customer withdrawAmount(String mobileNo, double amount)
			throws BankException {
		// TODO Auto-generated method stub
		String tranType = "Withdraw";
		boolean c = false;
		Customer cDep1 = dao.getAccount(mobileNo);
		if (cDep1 == null)
			throw new BankException("Mobile number doesn't exist");
		if ((cDep1.getAmount() - amount) >= 0)
			c = dao.setAccount(mobileNo, cDep1.getAmount() - amount);
		Customer cDep = dao.getAccount(mobileNo);
		if (!c)
			throw new BankException("We are unable to withdraw the amount");
		else
			return cDep;
	}

}
