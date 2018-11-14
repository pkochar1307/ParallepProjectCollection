package com.walletapp.test;

import static org.junit.Assert.assertEquals;
import junit.framework.Assert;

import org.junit.Test;

import com.walletapp.exception.InvalidAmount;
import com.walletapp.exception.InvalidPhoneNumber;
import com.walletapp.exception.NameException;
import com.walletapp.service.BankServiceImpl;

public class SBCBankTest {
	
	@Test
	public void ValidateName() throws NameException{
		BankServiceImpl bs = new BankServiceImpl();
		assertEquals(true, bs.validateUserName("Priya"));
	}
	
//	@Test (expected = InvalidNameFormat.class)
//	public void ValidateUserNameV1(){
//		BankServiceImpl bs = new BankServiceImpl();
		
//	}
	
	@Test
	public void ValidatePhonNumber() throws InvalidPhoneNumber{
		BankServiceImpl bs = new BankServiceImpl();
		assertEquals(true, bs.validatePhoneNumber("7038216458"));
	}
	
	@Test
	public void ValidatePhoneNumber() throws InvalidPhoneNumber{
		BankServiceImpl bs = new BankServiceImpl();
		  
			    
		        String mobNo="ABCD91828288";
		 
		        boolean result= bs.validatePhoneNumber(mobNo);
		        Assert.assertEquals(false,result);
	}
	
	@Test 
	public void ValidateNameV2() throws NameException{
		BankServiceImpl bs = new BankServiceImpl();
		String name="pooja123";
		 
        boolean result= bs.validateUserName(name);
        Assert.assertEquals(false,result);
	}
	
	@Test
	public void ValidateAmountTrue() throws InvalidAmount{
		BankServiceImpl bs = new BankServiceImpl();
		assertEquals(true, bs.validateAmount(100));
	}
	
	@Test (expected = InvalidAmount.class)
	public void ValidateAmount() throws InvalidAmount{
		BankServiceImpl bs = new BankServiceImpl();
		double amt= -400;
		 
        boolean result= bs.validateAmount(amt);
//        Assert.assertEquals(false,result);
		
	}

}
