package posa;

public class Doner implements Runnable{
	Thread myThread;
	PingPonger pingPonger;
	/**
	 * @pingPonger a synchronized object is passed to this parameter
	 */ 
	Doner (PingPonger pingPonger){
		this.pingPonger = pingPonger;
		myThread = new Thread(this);
		myThread.start();
	}
	
	@Override
	public void run() {
		this.pingPonger.Done("Done!");
	}
}
