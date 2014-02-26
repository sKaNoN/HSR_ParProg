package skanon.uebung02.aufgabe01;

class BankAccount {
	private int balance = 0;

	public synchronized void deposit(int amount) {
		synchronized(this) {
			this.balance += amount;
		}
	}

	public synchronized boolean withdraw(int amount) {
		if (amount <= balance) {
			synchronized(this) {
				this.balance -= amount;
			}
			return true;
		} else {
			return false;
		}
	}

	public int getBalance() {
		return balance;
	}
}

class BankCustomer extends Thread {
	private final static int NOF_TRANSACTIONS = 10000000;
	private final BankAccount account;
	
	public BankCustomer(BankAccount account) {
		this.account = account;
	}
	
	@Override
	public void run() {
		for (int k = 0; k < NOF_TRANSACTIONS; k++) {
			account.deposit(100);
			System.out.println(account.getBalance());
			account.withdraw(100);
			System.out.println(account.getBalance());
		}
	}
}

public class BankTest1 {
	private final static int NOF_CUSTOMERS = 10;

	public static void main(String[] args) {
		BankAccount account = new BankAccount();
		for (int i = 0; i < NOF_CUSTOMERS; i++) {
			new BankCustomer(account).start();
		}
	}
}
