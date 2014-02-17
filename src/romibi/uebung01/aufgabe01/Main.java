package romibi.uebung01.aufgabe01;

public class Main {

	public static void main(String[] args) {
		new ThreadTicker().start();
		new Thread(new RunnableTicker()).start();
		new Thread() {
			public void run() {
				try {
					ConsoleTicker.periodTicker('c', 20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}
