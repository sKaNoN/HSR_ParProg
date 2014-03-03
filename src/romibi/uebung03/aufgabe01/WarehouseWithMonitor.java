package romibi.uebung03.aufgabe01;

public class WarehouseWithMonitor implements Warehouse {
	final int capacity;
	private int value;
  
  public WarehouseWithMonitor(int capacity) {
    this.capacity = capacity;
  }
  
  @Override
  public synchronized void put(int amount) throws InterruptedException {
	  while(value+amount>capacity) { wait(); }
	  value+=amount;
	  notifyAll();
  }

  @Override
  public synchronized void get(int amount) throws InterruptedException {
	  while(value<amount) { wait(); }
	  value-=amount;
	  notifyAll();
  }
}
