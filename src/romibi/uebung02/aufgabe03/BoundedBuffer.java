package romibi.uebung02.aufgabe03;

import java.util.LinkedList;
import java.util.Queue;

class BoundedBuffer<T> {
	private Queue<T> queue = new LinkedList<>();
	int limit;
	
	public BoundedBuffer(int capacity) {

	}

	public synchronized void put(T item) throws InterruptedException {
		while(queue.size()==limit) {
			wait();
		}
		queue.add(item);
		notifyAll();
	}

	public synchronized T get() throws InterruptedException {
		while (queue.isEmpty()) {
			wait();
		}
		T x = queue.remove();
		notify();
		return x;
	}
}
