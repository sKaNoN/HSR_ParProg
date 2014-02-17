package romibi.uebung01.aufgabe02;

public class ConsoleTicker {
	public static void periodTicker(char sign, int intervallMillis)
			throws InterruptedException {
		while (true) {
			System.out.print(sign);
			Thread.sleep(intervallMillis);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		periodTicker('.', 10);
		// TODO: Concurrent periodTicker('*', 20);
	}
}