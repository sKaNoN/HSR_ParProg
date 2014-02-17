package skanon.uebung01.aufgabe02;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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


	public static void main(String[] args) throws InterruptedException, IOException {
		ConsoleTicker t1 = new ConsoleTicker('a', 10);
		t1.setDaemon(true);
		t1.start();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Press Enter To Exit");
        String s = br.readLine();
		
		
		
	}
}
