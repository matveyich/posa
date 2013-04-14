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
	private int typeOfStick;
	
	/**
	 * 
	 * @param phil - Philosopher object, who frees the chopstick
	 * @param typeOfStick - indicates whether the current stick is left or right to the current Philosopher
	 * @throws InterruptedException
	 */
	public void use(Philosopher phil, int typeOfStick) throws InterruptedException {
		lock.lock();
		try {
			while(isInUse == true){
				freeToUse.await();
			}				
			inUse.signal();
			
			this.isInUse = true;
			this.typeOfStick = typeOfStick;
			
			System.out.println(phil.getName() + " picks up " + this.getStickType() + " stick");
		} finally {
			
		}
	}
	
	/**
	 * 
	 * @param phil - Philosopher object, who frees the chopstick
	 * @param typeOfStick - indicates whether the current stick is left or right to the current Philosopher
	 * @throws InterruptedException
	 */
	public void free(Philosopher phil) throws InterruptedException{
		
		try {		  
			System.out.println(phil.getName() + " puts down " + this.getStickType() + " stick");			
		} finally {
			this.isInUse = false;
			
			freeToUse.signalAll();
			lock.unlock();
		}
	}
	/**
	 * 
	 * @return - name of stick in relation to current philosopher
	 */
	private String getStickType() {
		String typeOfStick;
		switch (this.typeOfStick) {
		case 1:
			typeOfStick = "left";
			break;
		default: 
			typeOfStick = "right";
			break;
		}
		return typeOfStick;
	}
}
