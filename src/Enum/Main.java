package Enum;

public class Main {
	public static void main(String[] args) throws Exception {
		Chicken.printAllKindChicken();
		System.out.println();
		
		System.out.println(Chicken.C1);
	
		System.out.println();
		
		int[] arr = {
				1, 2, 3, 4, 5
		};
		
		BuyList buyList = new BuyList(arr);
		
		System.out.println(buyList.toString() + "totalPrice = " + buyList.totalPrice() + "$");
	}
}
