package romibi.uebung03.aufgabe01;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WarehouseWithLockCondition implements Warehouse {
	final int capacity;
	private int value = 0;
	private Lock monitor;
	private Condition nonFull;
	private Condition nonEmpty;
  
  public WarehouseWithLockCondition(int capacity, boolean fair) {
    this.capacity = capacity;
    monitor = new ReentrantLock(fair);
    nonFull = monitor.newCondition();
    nonEmpty = monitor.newCondition();
  }
  
  @Override
  public void put(int amount) throws InterruptedException {
	  monitor.lock();
	  try {
		  while(value+amount>capacity) { nonFull.await(); }
		  value+=amount;
		  nonEmpty.signalAll();										// könnten mehrere zufrieden sein
	  } finally {
		  monitor.unlock();
	  }
  }

  @Override
  public void get(int amount) throws InterruptedException {
	  monitor.lock();
	  try {
		  while(value<amount) { nonEmpty.await(); }
		  value-=amount;
		  nonFull.signalAll();										// könnten mehrerer zufrieden sein
	  } finally {
		  monitor.unlock();
	  }
  }
}