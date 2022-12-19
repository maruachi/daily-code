package Car_GSW_solution;

import java.util.ArrayList;

public class Main {
	private static void doRace() {
	}

	public static void main(String[] args) {
		try {
			// init race
			RaceInterface myrace = new Race(5);

			myrace.registerCar(new Car("pobi"));
			myrace.registerCar(new Car("crong"));
			myrace.registerCar(new Car("honux"));

			// do race
			myrace.printState();
			System.out.println("");

			while (myrace.processRace()) {
				myrace.printState();
				System.out.println("");
			}

			myrace.printState();
			System.out.println("");

			myrace.printWinners();

		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
