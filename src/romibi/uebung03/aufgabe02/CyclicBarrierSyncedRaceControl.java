package romibi.uebung03.aufgabe02;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierSyncedRaceControl extends AbstractRaceControl {
	CyclicBarrier carsReady = new CyclicBarrier(NOF_RACE_CARS);
	CountDownLatch raceOver = new CountDownLatch(1);
	CyclicBarrier waitingForLapOfHonor = new CyclicBarrier(NOF_RACE_CARS);

	@Override
	protected void waitForAllToBeReady() throws InterruptedException {
	}

	@Override
	public void readyToStart() {
	}

	@Override
	protected void giveStartSignal() {
	}

	@Override
	public void waitForStartSignal() throws InterruptedException {
		try {
			carsReady.await();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
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
	}

	@Override
	public void waitForLapOfHonor() throws InterruptedException {
		try {
			waitingForLapOfHonor.await();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

}
