package romibi.uebung01.aufgabe02;

public class Main {

	public static void main(String[] args) {
		Thread a = new ThreadTicker();
		Thread b = new Thread(new RunnableTicker());
		Thread c = new Thread() {
			public void run() {
				try {
					ConsoleTicker.periodTicker('c', 30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		a.setDaemon(true);
		b.setDaemon(true);
		c.setDaemon(true);		// Passiert nichts (bzw sofort beendet)
		//c.setDaemon(false);	// Wie Aufgabe 1 (a & b beenden erst wen c beendet)
		
		a.start();
		b.start();
		c.start();
	}
}
