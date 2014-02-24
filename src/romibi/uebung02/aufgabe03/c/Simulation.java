package romibi.uebung02.aufgabe03.c;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class Simulation {
	private static final int NOF_PRODUCERS = 2;
	private static final int NOF_CONSUMERS = 4;
	private static final int BUFFER_CAPACITY = 1;
	// TotalElements must be a multiple of ElementsPerProducer and ElementsPerConsumer
	private static final int TOTAL_ELEMENTS = 1000000; 
	private static final int ELEMENTS_PER_PRODUCER = TOTAL_ELEMENTS / NOF_PRODUCERS;
	private static final int ELEMENTS_PER_CONSUMER = TOTAL_ELEMENTS / NOF_CONSUMERS;

	public static void main(String[] args) throws InterruptedException {
		List<Thread> threads = new ArrayList<Thread>();
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