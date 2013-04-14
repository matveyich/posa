package posa;

public class Pong implements Runnable {
	Thread myThread;
	PingPonger pingPonger;
	/**
	 * @pingPonger a synchronized object is passed to this parameter
	 */ 
	Pong (PingPonger pingPonger){
		this.pingPonger = pingPonger;
		myThread = new Thread(this);
		myThread.start();
	}
	
	@Override
	public void run() {
		this.pingPonger.Pong(3, "Pong!");
	}

}
