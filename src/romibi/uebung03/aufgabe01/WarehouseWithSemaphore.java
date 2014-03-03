package romibi.uebung03.aufgabe01;

import java.util.concurrent.Semaphore;

public class WarehouseWithSemaphore implements Warehouse {
	private int value = 0;												//optional falls ohne getValue
	private Semaphore upperLimit;
	private Semaphore lowerLimit;
	private Semaphore access;											//optional falls ohne getValue

	public WarehouseWithSemaphore(int capacity, boolean fair) {
		upperLimit = new Semaphore(capacity, fair);
		lowerLimit = new Semaphore(0, fair);
		access = new Semaphore(1, fair);								//optional falls ohne getValue
	}

	@Override
	public void put(int amount) throws InterruptedException {
		upperLimit.acquire(amount);
		access.acquire();												//optional falls ohne getValue
		value += amount;												//optional falls ohne getValue
		access.release();												//optional falls ohne getValue
		lowerLimit.release(amount);
	}

	@Override
	public void get(int amount) throws InterruptedException {
		lowerLimit.acquire(amount);
		access.acquire();												//optional falls ohne getValue
		value -= amount;												//optional falls ohne getValue
		access.release();												//optional falls ohne getValue
		upperLimit.release(amount);
	}

	public int getValue() throws InterruptedException {					//optional
		access.acquire();
		int retval = value;
		access.release();
		return retval;
	}
}
