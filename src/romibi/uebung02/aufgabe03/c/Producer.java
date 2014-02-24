package romibi.uebung02.aufgabe03.c;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

class Producer extends Thread {
	private final ArrayBlockingQueue<Long> buffer;
	private final int nofItems;

	public Producer(ArrayBlockingQueue<Long> buffer, int nofItems) {
		this.buffer = buffer;
		this.nofItems = nofItems;
	}

	public void run() {
		Random random = new Random();
		for (int i = 0; i < nofItems; i++) {
			try {
				buffer.put(random.nextLong());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Producer finished " + getName());
	}
}

