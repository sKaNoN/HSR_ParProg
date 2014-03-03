package romibi.uebung01.aufgabe01;

public class RunnableTicker implements Runnable {
	public void run(){
		try {
			ConsoleTicker.periodTicker('b', 20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
