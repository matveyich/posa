package posa;

public class Philosopher implements Runnable{
	private Thread philThread;
	private Chopstick leftStick;
	private Chopstick rightStick;
	private String Name;
	private final int timesToEat = 5;
	
	Philosopher(String Name, Chopstick leftStick, Chopstick rightStick){
		this.rightStick = rightStick;
		this.leftStick = leftStick;
		this.Name = Name;
		
		philThread = new Thread(this);
		philThread.start();
	}
	
	public void eat(){
		try {
			this.leftStick.use(this, 1);
			this.rightStick.use(this, 2);
			
			System.out.println(this.Name + " eats");	
		
			this.leftStick.free(this);
			this.rightStick.free(this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		for (int i = 0; i < this.timesToEat; i++) {
			this.eat();			
		}	
		
	}

	public String getName(){
		return this.Name;
	}
	
}
