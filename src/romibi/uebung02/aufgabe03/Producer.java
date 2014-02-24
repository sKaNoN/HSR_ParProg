package romibi.uebung02.aufgabe03;

import java.util.Random;

class Producer extends Thread {
	private final BoundedBuffer<Long> buffer;
	private final int nofItems;

	public Producer(BoundedBuffer<Long> buffer, int nofItems) {
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

