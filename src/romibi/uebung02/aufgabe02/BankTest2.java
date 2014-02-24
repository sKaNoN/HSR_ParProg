package romibi.uebung02.aufgabe02;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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