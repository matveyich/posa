package posa;

public class Philosopher implements Runnable{
	private Thread philThread;
	
	private Chopstick leftStick;				// philosopher's left stick 
	private Chopstick rightStick;				// philosopher's right stick
	
	private String Name;						// philosopher's name
	private final int timesToEat = 5;			// number of times a philosopher should eat
	private boolean leftStickAcquired = false;	// flag - philosopher got left stick
	private boolean rightStickAcquired = false;	// flag - philosopher got right stick
	private int sticksAcquired = 0;				// counter - number of sticks philosopher has
	public int timesAte = 0;					// counter - number of times philosopher ate
	
	Philosopher(String Name, Chopstick leftStick, Chopstick rightStick){
		this.rightStick = rightStick;
		this.leftStick = leftStick;
		this.Name = Name;
		
		philThread = new Thread(this);
		philThread.start();
	}
	
	public void eat(){
		try {
			while (this.sticksAcquired < 2){	// eat only when two sticks in hands
				this.sticksAcquired = 0;
								
				this.leftStick.use(this);
				this.leftStickAcquired = true;
				this.sticksAcquired++;
				outputUseStick("left");
				
				this.rightStick.use(this);
				this.rightStickAcquired = true;
				this.sticksAcquired++;
				outputUseStick("right");
			}
			
			System.out.println(this.Name + " eats");	
			
			this.leftStick.free(this);
			this.leftStickAcquired = false;
			this.sticksAcquired--;
			outputFreeStick("left");
			
			this.rightStick.free(this);
			this.rightStickAcquired = false;
			this.sticksAcquired--;
			outputFreeStick("right");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		for (int i = 0; i < this.timesToEat; i++) {
			this.eat();
			this.timesAte++;
		}	
		
	}
	/**
	 * 
	 * release one of the sticks to avoid deadlocks
	 * called when trying to obtain a stick, and it is already in use by another philosopher
	 */
	public void freeOneAcquiredStick() throws InterruptedException{
		if (this.leftStickAcquired == true){
			this.leftStick.free(this);
		}
		if (this.rightStickAcquired == true) {
			this.rightStick.free(this);
		}
	}
	
	private void outputFreeStick(String type){
		System.out.println(this.Name + " puts down " + type + " stick");
	}
	
	private void outputUseStick(String type){
		System.out.println(this.Name + " picks up " + type + " stick");
	}
	
}
