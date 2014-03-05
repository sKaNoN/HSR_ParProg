package skanon.uebung03.aufgabe2;

import java.util.concurrent.CountDownLatch;

public class LatchesSyncedRaceControl extends AbstractRaceControl {
	CountDownLatch carsReady = new CountDownLatch(10);
	CountDownLatch startSignal = new CountDownLatch(1);
	CountDownLatch carsFinished = new CountDownLatch(NOF_RACE_CARS);
	CountDownLatch raceEnded = new CountDownLatch(1);

	// Called by race control
	protected void waitForAllToBeReady() throws InterruptedException {
		carsReady.await();
		giveStartSignal();	
	}

	// Called by race cars
	public void readyToStart() {
		carsReady.countDown();
	}

	// Called by race control
	protected  void giveStartSignal() {
		startSignal.countDown();
	}

	// Called by race cars
	public void waitForStartSignal() throws InterruptedException {
		startSignal.await();
	}
	

	protected synchronized void waitForFinishing() throws InterruptedException {
		carsFinished.await();
		raceEnded.countDown();
		
	}

	public synchronized boolean isOver() {
		return (raceEnded.getCount() == 0);
	}

	public synchronized void passFinishLine() {
		carsFinished.countDown();
	}

	public synchronized void waitForLapOfHonor() throws InterruptedException {
		raceEnded.await();
	}
}