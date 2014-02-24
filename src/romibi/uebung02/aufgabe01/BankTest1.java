package romibi.uebung02.aufgabe01;

public class BankTest1 {
	private final static int NOF_CUSTOMERS = 10;

	public static void main(String[] args) {
		BankAccount account = new BankAccount();
		for (int i = 0; i < NOF_CUSTOMERS; i++) {
			new BankCustomer(account).start();
		}
	}
}