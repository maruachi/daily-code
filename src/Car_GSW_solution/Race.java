package Car_GSW_solution;

import java.util.ArrayList;

public class Race implements RaceInterface {
	class CarRaceStatus {
		public CarInterface car;
		public int location;

		CarRaceStatus(CarInterface car) {
			this.car = car;
			this.location = 1; // location start from 1?
		}

		public void raceMove() {
			if (this.car.move()) {
				++location;
			}
		}

		public int getLocation() {
			return location;
		}

		public String getCarName() {
			return car.getName();
		}

		@Override
		public String toString() {
			StringBuilder stringBuilder = new StringBuilder();

			stringBuilder.append(car.getName());
			stringBuilder.append(" : ");
			for (int i = 0; i < location; ++i) {
				stringBuilder.append("-");
			}

			return stringBuilder.toString();
		}
	}

	ArrayList<CarRaceStatus> carsRaceStatus;
	final int moves;
	int move;

	Race(int moves) throws Exception {
		carsRaceStatus = new ArrayList<CarRaceStatus>();
		if (moves < 1) {
			throw new Exception("Moves is not positive");
		}
		this.moves = moves;
		this.move = 0;
	}

	public void registerCar(CarInterface car) {
		carsRaceStatus.add(new CarRaceStatus(car));
	}

	public void initRace() {
		move = 0;
	}

	public boolean processRace() {
		for (CarRaceStatus carRaceStatus: carsRaceStatus) {
			carRaceStatus.raceMove();
		}

		++move;
		return move < moves;
	}

	private ArrayList<String> getWinnersName() {
		int maxLocation = -1;
		ArrayList<String> winners = new ArrayList<String>();

		for (CarRaceStatus carRaceStatus: carsRaceStatus) {
			if (maxLocation < carRaceStatus.getLocation()) {
				maxLocation = carRaceStatus.getLocation();
			}
		}
		for (CarRaceStatus carRaceStatus: carsRaceStatus) {
			if (maxLocation == carRaceStatus.getLocation()) {
				winners.add(carRaceStatus.getCarName());
			}
		}

		return winners;
	}

	public void printState() {
		for (CarRaceStatus carRaceStatus: carsRaceStatus) {
			System.out.println(carRaceStatus);
		}
	}

	public void printWinners() {
		ArrayList <String> winners = getWinnersName();
		System.out.println("Winner is: " + String.join(", ", winners));
	}
}
