package skanon.uebung01.aufgabe03;
public class PrimeCounter extends Thread{
	private long start;
	private long end;
	public long result; 
	
  public PrimeCounter(long start, long end) {
	  this.start = start;
	  this.end = end;
  }
	
	private static boolean isPrime(long number) {
    for (long factor = 2; factor * factor <= number; factor++) {
      if (number % factor == 0) { 
        return false; 
      }
    }
    return true;
  }
  
  
  @Override
  public void run() {
	  long count = 0;
	    for (long number = start; number < end; number++) {
	      if (isPrime(number)) { 
	        count++; 
	      }
	    }
	    result = count;
  }
  
  private static final long START = 1_000_000L;
  private static final long END = 10_000_000L;
  
  public static void main(String[] args) throws InterruptedException {
    System.out.println(System.currentTimeMillis());     
    PrimeCounter c1 = new PrimeCounter(START, 2_000_000L);
    PrimeCounter c2 = new PrimeCounter(2_000_000L, 3_000_000L);
    PrimeCounter c3 = new PrimeCounter(3_000_000L, 4_000_000L);
    PrimeCounter c4 = new PrimeCounter(4_000_000L, 5_000_000L);
    PrimeCounter c5 = new PrimeCounter(5_000_000L, 6_000_000L);
    PrimeCounter c6 = new PrimeCounter(6_000_000L, 7_000_000L);
    PrimeCounter c7 = new PrimeCounter(7_000_000L, 8_000_000L);
    PrimeCounter c8 = new PrimeCounter(8_000_000L, 9_000_000L);
    PrimeCounter c9 = new PrimeCounter(9_000_000L, 10_000_000L);
    
    
    c1.start();
    c2.start();
    c3.start();
    c4.start();
    c5.start();
    c6.start();
    c7.start();
    c8.start();
    c9.start();
    
    c1.join();
    c2.join();
    c3.join();
    c4.join();
    c5.join();
    c6.join();
    c7.join();
    c8.join();
    c9.join();
    
    long result = c1.result + c2.result + c3.result + c4.result + c5.result 
    		+ c6.result + c7.result + c8.result + c9.result;
    System.out.println(result);
    
  }
}
