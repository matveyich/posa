package posa;

import java.util.concurrent.TimeUnit;
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
		lock.lock();
		try {
			while(isInUse == true){
				phil.freeOneAcquiredStick();			// release acquired sticks by current philosopher (if any) to avoid deadlocks
				freeToUse.await(1L, TimeUnit.SECONDS);	// wait to be notified when this stick is free
			}				
			inUse.signal();								// signal that this stick is in use by a philosopher
			
			this.isInUse = true;
			
		} finally {
			
		}
	}
	
	/**
	 * 
	 * @param phil - Philosopher object, who frees the chopstick
	 * @throws InterruptedException
	 */
	public void free(Philosopher phil) throws InterruptedException{
		
		try {		  
			this.isInUse = false;
						
		} finally {
			freeToUse.signalAll();	// notify another philosopher, that the stick is free
			lock.unlock();
		}
	}

}
