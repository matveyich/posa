package posa;

public class Ping implements Runnable {
	Thread myThread;
	PingPonger pingPonger;
	
	Ping (PingPonger pingPonger){
		this.pingPonger = pingPonger;
		myThread = new Thread(this);
		myThread.start();
	}
	
	@Override
	public void run() {
		this.pingPonger.Ping(3, "Ping!");
	}

}
