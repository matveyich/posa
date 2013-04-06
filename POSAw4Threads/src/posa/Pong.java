package posa;

public class Pong implements Runnable {
	Thread myThread;
	PingPonger pingPonger;
	
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