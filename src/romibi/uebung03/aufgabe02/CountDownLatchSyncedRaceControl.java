package romibi.uebung03.aufgabe02;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchSyncedRaceControl extends AbstractRaceControl {
	CountDownLatch carsReady = new CountDownLatch(NOF_RACE_CARS);
	CountDownLatch startSignal = new CountDownLatch(1);
	CountDownLatch raceOver = new CountDownLatch(1);
	CountDownLatch waitingForLapOfHonor = new CountDownLatch(NOF_RACE_CARS);

	@Override
	protected void waitForAllToBeReady() throws InterruptedException {
		carsReady.await();
	}

	@Override
	public void readyToStart() {
		carsReady.countDown();
	}

	@Override
	protected void giveStartSignal() {
		try {
			carsReady.await();
			startSignal.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void waitForStartSignal() throws InterruptedException {
		startSignal.await();
	}

	@Override
	protected void waitForFinishing() throws InterruptedException {
		raceOver.await();
	}

	@Override
	public boolean isOver() {
		return (raceOver.getCount()<1);
	}

	@Override
	public void passFinishLine() {
		raceOver.countDown();	
		waitingForLapOfHonor.countDown();
	}

	@Override
	public void waitForLapOfHonor() throws InterruptedException {
		waitingForLapOfHonor.await();
	}

}
