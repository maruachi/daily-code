package Enum;

public class Order{
	private final Chicken chicken;
	private final int ea;
	
	public Order(Chicken chicken, int ea) {
		this.chicken = chicken;
		this.ea = ea;
	}
	
	public int orderPrice() {
		return this.chicken.price() * this.ea;
	}
}