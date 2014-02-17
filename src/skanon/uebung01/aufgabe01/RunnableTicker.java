package skanon.uebung01.aufgabe01;

public class RunnableTicker implements Runnable {
	private char sign;
	private int intervallMillis;
	
	public RunnableTicker(char sign, int intervallMillis) {
		this.sign = sign;
		this.intervallMillis = intervallMillis;
	}
	
	@Override
	public void run() {
		while (true) {
	        System.out.print(sign);
	        try {
				Thread.sleep(intervallMillis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	      }
	}

}
