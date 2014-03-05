package skanon.uebung03.aufgabe2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BarrierSyncedRaceControl extends AbstractRaceControl {
	
	private CyclicBarrier waitForCars = new CyclicBarrier(NOF_RACE_CARS);
	private boolean over = false;

	// Called by race control
	protected void waitForAllToBeReady() throws InterruptedException {

	}

	// Called by race cars
	public void readyToStart() {
	}

	// Called by race control
	protected  void giveStartSignal() {
		
	}

	// Called by race cars
	public void waitForStartSignal() throws InterruptedException {
		try {
			waitForCars.await();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	protected synchronized void waitForFinishing() throws InterruptedException {
		
	}

	public synchronized boolean isOver() {
		return over;
	}

	public synchronized void passFinishLine() {
			over = true;
	}

	public synchronized void waitForLapOfHonor() throws InterruptedException {
		try {
			waitForCars.await();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}