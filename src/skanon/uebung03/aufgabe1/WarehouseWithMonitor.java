package skanon.uebung03.aufgabe1;

public class WarehouseWithMonitor implements Warehouse {
  private int capacity;
  private int stored = 0;
  
  public WarehouseWithMonitor(int capacity) {
    this.capacity = capacity;
  }
  
  @Override
  public synchronized void put(int amount) throws InterruptedException {
    while (stored+amount > capacity) {
    	wait();
    }
    stored += amount;
    notifyAll();
  }

  @Override
  public synchronized void get(int amount) throws InterruptedException {
   while (amount > stored) {
	   wait();
   }
   stored -= amount;
   notifyAll();
  }
}
