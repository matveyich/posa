package posa;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {
	/**
	 * monitor object Lock 
	 */
	private final Lock lock = new ReentrantLock();

	private final Condition inUse = lock.newCondition();
	private final Condition freeToUse = lock.newCondition();
	
	private boolean isInUse = false;
	
	/**
	 * 
	 * @param phil - Philosopher object, who frees the chopstick
	 * @throws InterruptedException
	 */
	public void use(Philosopher phil) throws InterruptedException {
		this.lock.lock();
		try {
			while(isInUse == true){						
			phil.freeOneAcquiredStick();
				freeToUse.await();	
			}				
			inUse.signal();
			
			this.isInUse = true;
			
		} finally {
			this.lock.unlock();
		}
	}
	
	/**
	 * 
	 * @param phil - Philosopher object, who frees the chopstick
	 * @throws InterruptedException
	 */
	public void free(Philosopher phil) throws InterruptedException{
		this.lock.lock();
		try {		  
			this.isInUse = false;
						
		} finally {
			freeToUse.signalAll();	// notify another philosopher, that the stick is free
			this.lock.unlock();
		}
	}
	
	public boolean isInUse(){
		return this.isInUse;
	}
}
