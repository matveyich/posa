package posa;

public class DiningTable {

	public static void main(String[] args) {
		Chopstick stick1 = new Chopstick();
		Chopstick stick2 = new Chopstick();
		Chopstick stick3 = new Chopstick();
		Chopstick stick4 = new Chopstick();
		Chopstick stick5 = new Chopstick();
		
		Philosopher phil1 = new Philosopher("Philosopher 1", stick1, stick2);
		Philosopher phil2 = new Philosopher("Philosopher 2", stick2, stick3);
		Philosopher phil3 = new Philosopher("Philosopher 3", stick3, stick4);
		Philosopher phil4 = new Philosopher("Philosopher 4", stick4, stick5);
		Philosopher phil5 = new Philosopher("Philosopher 5", stick5, stick1);
				
	}

}
