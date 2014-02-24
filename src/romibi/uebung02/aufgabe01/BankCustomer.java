package romibi.uebung02.aufgabe01;

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
			account.withdraw(100);
		}
	}
}