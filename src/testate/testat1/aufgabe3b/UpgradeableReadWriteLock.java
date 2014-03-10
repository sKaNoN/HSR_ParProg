package testate.testat1.aufgabe3b;

public class UpgradeableReadWriteLock {
	private int readCount = 0;
	private Thread upgradeableLockHolder = null;
	private boolean hasWriteLock = false;

	public synchronized void readLock() throws InterruptedException {
		while (hasWriteLock) {
			wait();
		}
		readCount++;
		notifyAll();
	}

	public synchronized void readUnlock() {
		readCount--;
		notifyAll();
	}

	public synchronized void upgradeableReadLock() throws InterruptedException {
		while (hasWriteLock || upgradeableLockHolder!=null) {
			wait();
		}
		upgradeableLockHolder = Thread.currentThread();
		notifyAll();
	}

	public synchronized void upgradeableReadUnlock() {
		upgradeableLockHolder = null;
		notifyAll();
	}

	public synchronized void writeLock() throws InterruptedException {
		if (readCount > 0 || hasWriteLock || (upgradeableLockHolder!=Thread.currentThread() && upgradeableLockHolder!=null)) {
			wait();
		}
		hasWriteLock = true;
		notifyAll();
		
	}

	public synchronized void writeUnlock() {
		hasWriteLock = false;
		notifyAll();
	}
}
