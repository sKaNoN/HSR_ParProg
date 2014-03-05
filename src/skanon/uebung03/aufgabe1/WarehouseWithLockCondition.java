package skanon.uebung03.aufgabe1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WarehouseWithLockCondition implements Warehouse {
	private Lock monitor;
	private int capacity;
	private int stored=0;
	private Condition nonFull;
	private Condition nonEmpty;

	public WarehouseWithLockCondition(int capacity, boolean fair) {
		this.capacity=capacity;
		monitor = new ReentrantLock(fair);
		nonFull = monitor.newCondition();
		nonEmpty = monitor.newCondition();
	}

	@Override
	public void put(int amount) throws InterruptedException {
		monitor.lock();
		try {
			while (stored + amount > capacity) {
				nonFull.await();
			}
			stored += amount;
//			System.out.println("LockCondition stored: " + stored);
			nonEmpty.signalAll();
		} finally {
			monitor.unlock();
		}
	}

	@Override
	public void get(int amount) throws InterruptedException {
		monitor.lock();
		try {
			while (amount > stored) {
				nonEmpty.await();
			}
			stored -= amount;
//			System.out.println("LockCondition stored: " + stored);
			nonFull.signalAll();
		} finally {
			monitor.unlock();
		}
	}
}
