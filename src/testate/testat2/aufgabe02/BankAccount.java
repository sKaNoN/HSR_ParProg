package testate.testat2.aufgabe02;

import java.util.concurrent.atomic.AtomicInteger;

public class BankAccount {
	private AtomicInteger balance = new AtomicInteger(0);

	public void deposit(int amount) {
		balance.addAndGet(amount);
	}

	public boolean withdraw(int amount) {
		int currentBalance;	
		do {
			currentBalance = balance.get();
			if (currentBalance < amount) return false;
		} while (!balance.compareAndSet(currentBalance, currentBalance - amount));
		return true;
	}

	public int getBalance() {
		return balance.get();
	}
}
