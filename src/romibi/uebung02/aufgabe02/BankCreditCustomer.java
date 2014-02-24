package romibi.uebung02.aufgabe02;

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
			while (account.getBalance() < amount) {
				Thread.yield();
			}
			try {
				if(account.withdraw(amount, 1)) {
					System.out.println("Use credit " + amount + " by " + Thread.currentThread().getName());
				} else {
					System.out.println("Credit Failed...");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			account.deposit(amount);
		}
	}
}

