package posa;

import java.util.ArrayList;

public class DiningTable {

	public static void main(String[] args) {
		int timesToEat = 25; // all philosophers should eat 25 times combined
		int timesAteAll = 0; // counter of times all philosophers ate combined
		
		Chopstick stick1 = new Chopstick();
		Chopstick stick2 = new Chopstick();
		Chopstick stick3 = new Chopstick();
		Chopstick stick4 = new Chopstick();
		Chopstick stick5 = new Chopstick();
		
		System.out.println("Dinner is starting!");
		
		ArrayList<Philosopher> philosophers = new ArrayList<Philosopher>();
		
		philosophers.add(new Philosopher("Philosopher 1", stick1, stick2));
		philosophers.add(new Philosopher("Philosopher 2", stick2, stick3));
		philosophers.add(new Philosopher("Philosopher 3", stick3, stick4));
		philosophers.add(new Philosopher("Philosopher 4", stick4, stick5));
		philosophers.add(new Philosopher("Philosopher 5", stick5, stick1));
		
		while (timesToEat > timesAteAll) {
			timesAteAll = 0;
			for (Philosopher philosopher : philosophers) {
				timesAteAll = timesAteAll + philosopher.timesAte;
			}
		}
		System.out.println("Dinner is over!");
		
	}

}
