package skanon.uebung01.aufgabe01;
public class ConsoleTicker extends Thread {
	private char sign;
	private int intervallMillis;

	public ConsoleTicker(char sign, int intervallMillis) {
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

	public static void StartMyThread(final char sign, final int intervall) {
		new Thread() {
			@Override public void run() {
				while (true) {
		        System.out.print(sign);
		        try {
					Thread.sleep(intervall);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		      }
			}
		}.start();
	}


	public static void main(String[] args) throws InterruptedException {
		// new ConsoleTicker('.', 10).start();
		// new Thread(new RunnableTicker('a', 10)).start();
		StartMyThread('b', 10);
		StartMyThread('c', 20);
		
	}
}
