package posa;

/**
 * This class depicts an object, which is used by 
 * Ping, Pong and Doner to make a synchronized output to console
 * 
 */
public class PingPonger {
	
	boolean pingsEnded = false;
	boolean pongsEnded = false;
	
	public void hit(String say){
		System.out.println(say);
		notifyAll();
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public synchronized void Ping(int times, String say){
		for (int i = 0; i < times; i++) {
			hit(say);
		}
		this.pingsEnded = true;
		notifyAll();
	}
	
	public synchronized void Pong(int times, String say){
		for (int i = 0; i < times; i++) {
			hit(say);
		}
		this.pongsEnded = true;
		notifyAll();
	}
	
	public synchronized void Done(String say){
		while (this.pingsEnded == false || this.pongsEnded == false){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(say);
		
	}
}
