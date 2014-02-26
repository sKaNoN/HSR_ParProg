package skanon.uebung02.aufgabe02;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class BankAccount {
	private int balance = 0;

	public synchronized void deposit(int amount) {
		balance += amount;
		notifyAll();
	}

	public synchronized boolean withdraw(int amount, long timeOutMillis) throws InterruptedException {
		if (timeOutMillis < 0) 
			return false;
		long tBefore = System.currentTimeMillis();
		while (amount > balance) {
			wait(timeOutMillis);
		} 
		if ((System.currentTimeMillis() - tBefore) > timeOutMillis) {
			System.out.println("timed oud");
			return false;
		} else {
		balance -= amount;
			return true;
		}
	}

	public synchronized int getBalance() {
		return balance;
	}
}

class BankCreditCustomer extends Thread {
	private static final int NOF_TRANSACTIONS = 100;
	private final BankAccount account;
	private final int amount;

	public BankCreditCustomer(BankAccount account, int amount) {
		this.account = account;
		this.amount = amount;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < NOF_TRANSACTIONS; i++) {
			try {
				account.withdraw(amount, 1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Use credit " + amount + " by " + Thread.currentThread().getName());
			account.deposit(amount);
		}
	}
}

public class BankTest2 {
	private final static int NOF_CUSTOMERS = 10;
	private final static int START_BUDGET = 1000;

	public static void main(String[] args) throws InterruptedException {
		BankAccount account = new BankAccount();
		List<BankCreditCustomer> customers = new ArrayList<>();
		Random random = new Random(4711);
		for (int i = 0; i < NOF_CUSTOMERS; i++) {
			customers.add(new BankCreditCustomer(account, random.nextInt(1000)));
		}
		for (BankCreditCustomer customer: customers) {
			customer.start();
		}
		account.deposit(START_BUDGET);
		for (BankCreditCustomer customer: customers) {
			customer.join();
		}
	}
}
