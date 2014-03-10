package testate.testat1.aufgabe3b;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReadWriteLockTest extends ConcurrentTest {
	private static final int TIMEOUT = 1000;
	private UpgradeableReadWriteLock rwLock;

	@Test(timeout = TIMEOUT)
	public void testSingleLocks() throws InterruptedException {
		UpgradeableReadWriteLock rwLock = new UpgradeableReadWriteLock();
		rwLock.readLock();
		rwLock.readUnlock();
		rwLock.upgradeableReadLock();
		rwLock.upgradeableReadUnlock();
		rwLock.writeLock();
		rwLock.writeUnlock();
	}

	@Test(timeout = TIMEOUT)
	public void testReadWrite() throws InterruptedException {
		rwLock = new UpgradeableReadWriteLock();
		rwLock.readLock();
		Thread t2 = new Thread() {
			@Override
			public void run() {
				try {
					rwLock.writeLock();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		t2.start();
		t2.join(100);
		assertTrue("Write lock is not blocked by read lock", t2.isAlive());
		rwLock.readUnlock();
	}

	@Test(timeout = TIMEOUT)
	public void testWriteRead() throws InterruptedException {
		rwLock = new UpgradeableReadWriteLock();
		rwLock.writeLock();
		Thread t2 = new Thread() {
			@Override
			public void run() {
				try {
					rwLock.readLock();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		t2.start();
		t2.join(100);
		assertTrue("Write lock is not blocked by read lock", t2.isAlive());
		rwLock.writeUnlock();
	}

	@Test(timeout=TIMEOUT)
	public void testUpgradeableReadWrite() throws InterruptedException {
		rwLock = new UpgradeableReadWriteLock();
		rwLock.upgradeableReadLock();
		Thread t2 = new Thread(){
			@Override
			public void run() {
			try {
				rwLock.writeLock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
			};
		t2.start();
		t2.join(100);
		assertTrue("Read lock is not blocked by write lock", t2.isAlive());
		rwLock.upgradeableReadUnlock();;
	}

	@Test(timeout=TIMEOUT)
	public void testWriteUpgradeableRead() throws InterruptedException {
		rwLock = new UpgradeableReadWriteLock();
		rwLock.writeLock();
		Thread t2 = new Thread() {
			@Override
			public void run() {
			try {
				rwLock.upgradeableReadLock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}};
		t2.start();
		t2.join(100);
		assertTrue("Write lock is not blocked by upgradeable read lock", t2.isAlive());
		rwLock.writeUnlock();
	}

	@Test(timeout=TIMEOUT)
	public void testMultipleUpgradeableRead() throws InterruptedException {
		rwLock = new UpgradeableReadWriteLock();
		rwLock.upgradeableReadLock();
		Thread t2 = new Thread() {
			@Override
			public void run() {
			try {
				rwLock.upgradeableReadLock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}};
		t2.start();
		t2.join(100);
		assertTrue("Upgradeable read lock do not mutually block", t2.isAlive());
		rwLock.upgradeableReadUnlock();
	}

	@Test(timeout=TIMEOUT)
	public void testMultipleWrite() throws InterruptedException {
		rwLock = new UpgradeableReadWriteLock();
		rwLock.writeLock();
		Thread t2 = new Thread(){
			@Override
			public void run() {
			try {
				rwLock.writeLock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}};
		t2.start();
		t2.join(100);
		assertTrue("Write locks do not mutually block", t2.isAlive());
		rwLock.writeUnlock();;
	}

	@Test(timeout=TIMEOUT)
	public void testReadRead() throws InterruptedException {
		rwLock = new UpgradeableReadWriteLock();
		rwLock.readLock();
		for (int i = 0; i < 10; i++) {
			new Thread() {
				@Override
				public void run() {
				try {
					rwLock.readLock();
					Thread.sleep(10);
					rwLock.readUnlock();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}}.start();
		}
		Thread.sleep(100);
		rwLock.readUnlock();
		rwLock.writeLock();
		rwLock.writeUnlock();
	}

	@Test(timeout=TIMEOUT)
	public void testReadUpgradeableRead() throws InterruptedException {
		rwLock = new UpgradeableReadWriteLock();
		rwLock.upgradeableReadLock();;
		for (int i = 0; i < 10; i++) {
			new Thread() {
				@Override
				public void run() {
				try {
					rwLock.readLock();
					Thread.sleep(10);
					rwLock.readUnlock();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}}.start();
		}
		Thread.sleep(100);
		rwLock.upgradeableReadUnlock();
		rwLock.writeLock();
		rwLock.writeUnlock();
	}
}
