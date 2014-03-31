package romibi.uebung07.Aufgabe1.c;

import java.util.Arrays;
import java.util.Observable;

public class TheSupercomputer extends Observable {

	private enum StatusType {
		BORED, STARTING, CONFUSED, ERROR, RECOVERING_FROM_ERROR, OVERHEATED, CALCULATING
	}

	private static final int ITERATIONS = 5;
	StatusType status = StatusType.BORED;
	private int dotsCount = 0;
	
	private void updateStatus(StatusType s) {
		status = s;
		setChanged();
		notifyObservers(status.toString());
	}

	public String calculateUltimateAnswerToTheUltimateQuestionOfLifeTheUniverseAndEverything() {
		try {
			int firstPart = calculateFirstPart();
			int secondPart = calculateSecondPart();
			int modulo = calculateModulo();
			//status = StatusType.BORED;
			updateStatus(StatusType.BORED);
			//setChanged();
			//notifyObservers();
			return Integer.toString(firstPart * secondPart % modulo);

		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private int calculateModulo() throws InterruptedException {
		//status = StatusType.OVERHEATED;
		updateStatus(StatusType.OVERHEATED);
		doSleepLoop();
		//status = StatusType.CALCULATING;
		updateStatus(StatusType.CALCULATING);
		doSleepLoop();
		return 53;
	}

	private int calculateSecondPart() throws InterruptedException {
		//status = StatusType.CONFUSED;
		updateStatus(StatusType.CONFUSED);
		doSleepLoop();
		doSleepLoop();
		//status = StatusType.ERROR;
		updateStatus(StatusType.ERROR);
		doSleepLoop();
		doSleepLoop();
		//status = StatusType.RECOVERING_FROM_ERROR;
		updateStatus(StatusType.RECOVERING_FROM_ERROR);
		doSleepLoop();
		//status = StatusType.CALCULATING;
		updateStatus(StatusType.CALCULATING);
		doSleepLoop();
		return 41;
	}

	private int calculateFirstPart() throws InterruptedException {
		//status = StatusType.STARTING;
		updateStatus(StatusType.STARTING);
		doSleepLoop();
		//status = StatusType.CALCULATING;
		updateStatus(StatusType.CALCULATING);
		doSleepLoop();
		return 23;
	}

	private void doSleepLoop() throws InterruptedException {
		for (int i = 0; i < ITERATIONS; i++) {
			Thread.sleep(200);
			dotsCount %= ITERATIONS;
			dotsCount++;
			//setChanged();
			//notifyObservers();
		}
	}

	public String getStatus() {
		return status.name() + makeDotStr();
	}

	private String makeDotStr() {
		char[] chars = new char[dotsCount];
		Arrays.fill(chars, '.');
		return new String(chars);
	}
}
