package Enum;

import java.util.ArrayList;
import java.util.List;

public class BuyList {
	private final List<Order> buyList = new ArrayList<>();
	private final int MAX_SIZE = Chicken.values().length;
	
	BuyList(){
		for(Chicken chicken : Chicken.values()) {
			buyList.add(new Order(chicken, 0));
		}
	}
	
	BuyList(int[] arr) throws Exception{
		if(arr.length != MAX_SIZE) {
			throw new Exception("Can not create BuyList");
		}
		
		for(int i = 0; i < MAX_SIZE; i++ ) {
			buyList.add(new Order(Chicken.C1.sizeUp(i), arr[i]));
		}
	}
	
	public int totalPrice() {
		int totalPrice = 0;
		
		for(Order order : buyList) {
			totalPrice += order.orderPrice();
		}
		
		return totalPrice;
	}
}