package Car_GSW_solution;

public interface RaceInterface {
	public void registerCar(CarInterface car);
	public boolean processRace();
	public void printState();
	public void printWinners();
}
