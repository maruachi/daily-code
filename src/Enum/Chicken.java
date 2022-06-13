package Enum;

import java.text.MessageFormat;

public enum Chicken {
	C1(1){
		int weight() {
			return 250;
		}
		int price() {
			return (int)(this.weight()*PRICE_PER_GRAM);
		}
	}, 
	C2(2){
		int weight() {
			return 320;
		}
		int price() {
			return (int)(this.weight()*PRICE_PER_GRAM);
		}
	}, 
	C3(3){
		int weight() {
			return 480;
		}
		int price() {
			return (int)(this.weight()*PRICE_PER_GRAM);
		}
	}, 
	C4(4){
		int weight() {
			return 600;
		}
		int price() {
			return (int)(this.weight()*PRICE_PER_GRAM);
		}
	}, 
	C5(5){
		int weight() {
			return 750;
		}
		int price() {
			return (int)(this.weight()*PRICE_PER_GRAM);
		}
	};
	
	private static final Chicken[] CHICKENS = Chicken.values();
	final float PRICE_PER_GRAM = 4.5f;
	private final int NUM;
	
	abstract int weight();
	abstract int price();
	
	private Chicken(int num) {
		NUM = num;
	}
	
	public String toString() {
		return MessageFormat.format("#{0} chicken: weight {1}g price {2}$", 
					name(), weight(), price());
	}
	
	public static void printAllKindChicken() {
		for(Chicken chicken : Chicken.values()) {
			System.out.println(chicken);
		}
	}
	
	public Chicken sizeUp(int num) {
		num = num % 4;
		return CHICKENS[NUM+num-1];
	}
}
