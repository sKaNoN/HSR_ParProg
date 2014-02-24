package romibi.uebung02.aufgabe03.c;

import java.util.concurrent.ArrayBlockingQueue;


class Consumer extends Thread {
	private final ArrayBlockingQueue<Long> buffer;
	private final int nofItems;

	public Consumer(ArrayBlockingQueue<Long> buffer, int nofItems) {
		this.buffer = buffer;
		this.nofItems = nofItems;
	}

	public void run() {
		for (int i = 0; i < nofItems; i++) {
			try {
				buffer.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Consumer finished " + getName());
	}
}

