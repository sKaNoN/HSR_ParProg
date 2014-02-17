package romibi.uebung01.aufgabe02;

public class ThreadTicker extends Thread {
	public void run() {
		try {
			ConsoleTicker.periodTicker('a', 10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
