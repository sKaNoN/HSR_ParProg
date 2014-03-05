package skanon.uebung03.aufgabe1;

import java.util.concurrent.Semaphore;

public class WarehouseWithSemaphore implements Warehouse {
	private Semaphore upperLimit;
	private Semaphore lowerLimit; 
	private Semaphore mutex = new Semaphore(1, true);
	private int stored = 0;
  
  public WarehouseWithSemaphore(int capacity, boolean fair) {
	 upperLimit = new Semaphore(capacity, fair);
	 lowerLimit = new Semaphore(0, fair);
  }
  
  @Override
  public void put(int amount) throws InterruptedException {
	  upperLimit.acquire(amount); 
	  mutex.acquire(); stored+=amount; mutex.release();
//	  System.out.println("Semaphore stored: "+stored);
	  lowerLimit.release(amount);
  }

  @Override
  public void get(int amount) throws InterruptedException {
    lowerLimit.acquire(amount);
    mutex.acquire(); stored-=amount; mutex.release();
//    System.out.println("Semaphore stored: "+stored);
    upperLimit.release(amount);
  }
}
