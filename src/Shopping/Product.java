package Shopping;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Product {
	private final static Map<String, HashSet> brandTable;
	private final static Map<String, HashSet> categoryTable;
	private final String code;
	private final int price;
	
	{
		brandTable = new HashMap<>();
		categoryTable = new HashMap<>();
	}
	
	public Product(String code, int price) {
		this.code = code;
		this.price = price;
	}
	
	public static Product[] defaultProducts() {
		Product[] products = null;
		
		return products;
	}
	
	public boolean hasBrand(String brand) {
		if(!brandTable.containsKey(code)) {
			return false;
		}
		
		if(brandTable.get(code).contains(brand)) {
			return true;
		}
		return false;
	}
	
	public boolean hasCategory(String category) {
		return false;
	}
}
