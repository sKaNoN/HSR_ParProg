package skanon.uebung02.aufgabe03;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

class BoundedBuffer<T> {
	private Queue<T> queue = new LinkedList<>();
	private int limit;

	// TODO: Implement bounded buffer as monitor
	public BoundedBuffer(int capacity) {
		limit = capacity;
	}

	public synchronized void put(T item) throws InterruptedException {
		while (queue.size()==limit) {
			wait();
		}
		queue.add(item);
		notifyAll();
	}

	public synchronized T get() throws InterruptedException {
		while(queue.isEmpty()) {
			wait();
		}
		T x = queue.remove();
		notifyAll();
		return x;
		
		
	}
}

class Producer extends Thread {
	//private final BoundedBuffer<Long> buffer;
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Producer finished " + getName());
	}
}

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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Consumer finished " + getName());
	}
}

public class Simulation {
	private static final int NOF_PRODUCERS = 1;
	private static final int NOF_CONSUMERS = 1;
	private static final int BUFFER_CAPACITY = 1;
	// TotalElements must be a multiple of ElementsPerProducer and ElementsPerConsumer
	private static final int TOTAL_ELEMENTS = 1000000; 
	private static final int ELEMENTS_PER_PRODUCER = TOTAL_ELEMENTS / NOF_PRODUCERS;
	private static final int ELEMENTS_PER_CONSUMER = TOTAL_ELEMENTS / NOF_CONSUMERS;

	public static void main(String[] args) throws InterruptedException {
		List<Thread> threads = new ArrayList<Thread>();
		//BoundedBuffer<Long> buffer = new BoundedBuffer<Long>(BUFFER_CAPACITY);
		ArrayBlockingQueue<Long> buffer = new ArrayBlockingQueue<Long>(BUFFER_CAPACITY);
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < NOF_PRODUCERS; i++) {
			threads.add(new Producer(buffer, ELEMENTS_PER_PRODUCER));
		}
		for (int i = 0; i < NOF_CONSUMERS; i++) {
			threads.add(new Consumer(buffer, ELEMENTS_PER_CONSUMER));
		}
		for (Thread thread: threads) {
			thread.start();
		}
		for (Thread thread: threads) {
			thread.join();
		}
		long stopTime = System.currentTimeMillis();
		System.out.println("Producer-consumer simulation finished");
		System.out.println("Total time: " + (stopTime - startTime) + " ms");
	}
}
