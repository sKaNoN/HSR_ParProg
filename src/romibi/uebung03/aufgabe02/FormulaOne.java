package romibi.uebung03.aufgabe02;

public class FormulaOne {
	public static void main(String[] args) throws InterruptedException {
		//new MonitorSyncedRaceControl().runRace();
		//new CountDownLatchSyncedRaceControl().runRace();
		new CyclicBarrierSyncedRaceControl().runRace();
	}
}
