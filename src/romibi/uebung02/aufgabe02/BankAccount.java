package romibi.uebung02.aufgabe02;

class BankAccount {
	private int balance = 0;

	public synchronized void deposit(int amount) {
		balance += amount;
	}

	//public synchronized boolean withdraw(int amount) {
	public synchronized boolean withdraw(int amount, long timeOutMillis) throws InterruptedException {
		long validUntil = System.currentTimeMillis()+timeOutMillis;
		while (amount > balance) {
			wait(timeOutMillis);
		}
		
		if(System.currentTimeMillis()<validUntil) {
			balance -= amount;
			return true;
		} else {
			return false;
		}
	}

	public synchronized int getBalance() {
		return balance;
	}
}