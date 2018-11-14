package com.wallet.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.walletapp.dto.Customer;
import com.walletapp.exception.BankException;
import com.walletapp.exception.InvalidAmount;
import com.walletapp.exception.InvalidPhoneNumber;
import com.walletapp.exception.NameException;
import com.walletapp.service.BankService;
import com.walletapp.service.BankServiceImpl;

public class MainMenu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc1 = new Scanner(System.in);
		BankService service = new BankServiceImpl();

		int option;
		do {

			System.out.println("1. Create new customer account...");
			System.out.println("2. Show user's balance...");
			System.out.println("3. Transfer Fund...");
			System.out.println("4. Deposit amount into your account...");
			System.out.println("5. Withdraw amount from your account...");
			System.out.println("6. Exit...");
			option = sc1.nextInt();

			switch (option) {
			case 1:

				String name;
				String mobNo;
				double amt;

				do {
					System.out.println("Enter the Customer Name: ");
					name = sc1.next();
					try {
						if (service.validateUserName(name))
							break;
					} catch (NameException e) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
					}
				} while (true);
				do {
					System.out.println("Enter the Mobile Number: ");
					mobNo = sc1.next();
					try {
						if (service.validatePhoneNumber(mobNo))
							break;
					} catch (InvalidPhoneNumber e) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
					}
				} while (true);
				do {
					System.out.println("Enter Initial Amount: ");
					amt = sc1.nextDouble();
					try {
						if (service.validateAmount(amt))
							break;
					} catch (InvalidAmount e) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
					}
				} while (true);

				Customer c = new Customer(name, mobNo, amt);
				Customer c1 = null;
				try {
					if (service.validateAll(c))
						c1 = service.createAccount(c);
					System.out.println("You have successfully created new account for "
							+ c1.getCustomerName() + " with "
							+ "Mobile Number " + c1.getMobileNumber());
				} catch (BankException | NameException | InvalidAmount
						| InvalidPhoneNumber e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
				}
				break;

			case 2:

				System.out.println("Enter an existing mobile number: ");
				String mobNoShow = sc1.next();

				double bal = 0;
				try {
					if (service.validatePhoneNumber(mobNoShow))
						bal = service.showBalance(mobNoShow);
					System.out.println("The balance available  for  mobile number "
									+ mobNoShow + " is " + bal);
				} catch (InvalidPhoneNumber | BankException e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
				}

				break;

			case 3:

				String sourceMobileNo;
				String targetMobileNo;
				double amount;
				Customer funds = null;

				do {
					System.out.println("Enter your mobile number: ");
					sourceMobileNo = sc1.next();
					try {
						if (service.validatePhoneNumber(sourceMobileNo))
							break;
					} catch (InvalidPhoneNumber e) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
					}
				} while (true);
				do {
					System.out.println("Enter recipient's mobile number: ");
					targetMobileNo = sc1.next();
					try {
						if (service.validatePhoneNumber(targetMobileNo))
							break;
					} catch (InvalidPhoneNumber e) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
					}
				} while (true);
				do {
					System.out
							.println("Enter the amount that needs to be transfered: ");
					amount = sc1.nextDouble();
					try {
						if (service.validateAmount(amount))
							break;
					} catch (InvalidAmount e) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
					}
				} while (true);

				try {
					if (service.validatePhoneNumber(sourceMobileNo)
							&& service.validateTargetMobNo(targetMobileNo)
							&& service.validateAmount(amount))
						funds = service.fundTransfer(sourceMobileNo,
								targetMobileNo, amount);

					System.out.println("Successfully transfered Rs." + amount
							+ " to " + targetMobileNo + ".\n"
							+ "Available balance is Rs. " + funds.getAmount());

				} catch (InvalidPhoneNumber | InvalidAmount | BankException e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
				}

				break;

			case 4:

				String mobNoDep;
				double amtDep;
				Customer cDep = null;

				do {
					System.out.println("Enter your mobile number: ");
					mobNoDep = sc1.next();
					try {
						if (service.validatePhoneNumber(mobNoDep))
							break;
					} catch (InvalidPhoneNumber e) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
					}
				} while (true);
				do {
					System.out.println("Enter amount that to be deposited: ");
					amtDep = sc1.nextDouble();
					try {
						if (service.validateAmount(amtDep))
							break;
					} catch (InvalidAmount e) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
					}
				} while (true);

				try {
					if (service.validatePhoneNumber(mobNoDep)
							&& service.validateAmount(amtDep))
						cDep = service.depositAmount(mobNoDep, amtDep);
					System.out.println("Your current balance is Rs."
							+ cDep.getAmount());
				} catch (InvalidAmount | InvalidPhoneNumber | BankException e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
				}
				break;

			case 5:

				String mobNoWiDraw;
				double amtWiDraw;

				do {
					System.out.println("Enter your mobile number: ");
					mobNoWiDraw = sc1.next();
					try {
						if (service.validatePhoneNumber(mobNoWiDraw))
							break;
					} catch (InvalidPhoneNumber e) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
					}
				} while (true);
				do {
					System.out.println("Enter amount that to be withdrawn: ");
					amtWiDraw = sc1.nextDouble();
					try {
						if (service.validateAmount(amtWiDraw))
							break;
					} catch (InvalidAmount e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} while (true);

				Customer cW= null;
				try {
					if (service.validatePhoneNumber(mobNoWiDraw)
							&& service.validateAmount(amtWiDraw))
						cW = service.withdrawAmount(mobNoWiDraw, amtWiDraw);
					System.out.println("Your current balance is Rs. "
							+ cW.getAmount());
				} catch (InvalidAmount | BankException | InvalidPhoneNumber e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
				}
				break;

			default:
			case 6:
				break;
			}

		} while (option != 6);

		sc1.close();
	}

}
