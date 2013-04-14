package posa;

/**
 * This class depicts an object, which is used by 
 * Ping, Pong and Doner to make a synchronized output to console
 * 
 */
public class PingPonger {
	/**
	 * pingsEnded - flags that Ping thread is finished
	 * pongsEnded - flags that Pong thread is finished
	 */ 
	boolean pingsEnded = false;
	boolean pongsEnded = false;
	/**
	 * this method does output to console, calls wait() on current thread, 
	 * then calls notifyAll(), to say to other threads, 
	 * that current thread has doen its job and the object is free to use
	 * 
	 * @say what to output to console
	*/
	public void hit(String say){
		System.out.println(say);
		notifyAll();
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * this method is dedicated to Ping thread
	 * 
	 * @times indicates how many times 
	 * @say indicates what to output to console
	 */
	public synchronized void Ping(int times, String say){
		for (int i = 0; i < times; i++) {
			hit(say);
		}
		this.pingsEnded = true;
		notifyAll();
	}
	/**
	 * this method is dedicated to Pong thread
	 * 
	 * @times indicates how many times 
	 * @say indicates what to output to console
	 */ 
	public synchronized void Pong(int times, String say){
		for (int i = 0; i < times; i++) {
			hit(say);
		}
		this.pongsEnded = true;
		notifyAll();
	}
	/**
	 * this method is dedicated to Done thread
	 * waits till pingsEnded and pongsEnded are true to output Done! to console
	 * 
	 * @say indicates what to output to console
	 */ 
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
