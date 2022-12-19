package Car_GSW_solution;

import java.util.Random;

public class Car implements CarInterface {
	private String name;
	private Random randomGenerator;

	Car(String name) throws Exception {
		if (name.length() > 5) {
			throw new Exception("Name too long: should be <6");
		}

		this.name = name;
		randomGenerator = new Random();
	}

	public String getName() {
		return this.name;
	}

	public boolean move() {
		return this.randomGenerator.nextInt(10) >= 4;
	}
}
