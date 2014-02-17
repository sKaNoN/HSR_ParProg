package skanon.uebung01.aufgabe04;

public class NeverEndingThread extends Thread {

	@Override
	public void run() {
		try {
			while (true) {
				Thread.sleep(1000000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		for (int i=0; i < 2035 ;i++) {
			new NeverEndingThread().start();
			System.out.println(i);
		}

	}

}

//2033
//Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
//	at java.lang.Thread.start0(Native Method)
//	at java.lang.Thread.start(Thread.java:691)
//	at skanon.uebung01.aufgabe04.NeverEndingThread.main(NeverEndingThread.java:18)
