package posa;

public class PingPong {

	private static Ping ping;
	private static Pong pong;
	private static Doner doner;

	public static void main(String[] args) {

		PingPonger pingPonger = new PingPonger();
		
		System.out.println("Ready… Set… Go!");
				
		ping = new Ping(pingPonger);		
		pong = new Pong(pingPonger);		
		doner = new Doner(pingPonger);	
	}
}